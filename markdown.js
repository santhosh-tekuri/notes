import { marked } from 'https://cdn.jsdelivr.net/npm/marked@15.0.8/+esm'

// fixHRefs ---

const fixHRefs = {
    name: "fixHRefs",
    walkTokens(token) {
        if (token.type != 'link' && token.type != 'image') {
            return
        }
        if(token.href.includes("://")) {
            return
        }
        if (token.href.endsWith(".md")) {
            var url = URL.parse(window.location.href)
            url = new URL(token.href, "http://dummy.com/"+url.hash.substring(1))
            token.href = "#"+url.pathname.substring(1)
        } else {
            var url = URL.parse(window.location.href)
            let hash = url.hash.substring(1)
            url.hash = ""
            url = new URL(hash, url.toString())
            url = new URL(token.href, url.toString())
            token.href = url.toString()
        }
    }
}
marked.use(fixHRefs)

// highlight code blocks ---

import { markedHighlight } from "https://cdn.jsdelivr.net/npm/marked-highlight@2.2.1/+esm";
import { HighlightJS } from "https://cdn.jsdelivr.net/npm/highlight.js@11.11.1/+esm";
marked.use(markedHighlight({
    emptyLangClass: 'hljs',
    langPrefix: 'hljs language-',
    highlight(code, lang, info) {
      const language = HighlightJS.getLanguage(lang) ? lang : 'plaintext';
      return HighlightJS.highlight(code, { language }).value;
    }
}))

// render math using katex ---
import katex from 'https://cdn.jsdelivr.net/npm/katex@0.16.22/+esm'

// copied from https://github.com/KaTeX/KaTeX/blob/main/contrib/auto-render/splitAtDelimiters.js
const findEndOfMath = function(delimiter, text, startIndex) {
    // Adapted from
    // https://github.com/Khan/perseus/blob/master/src/perseus-markdown.jsx
    let index = startIndex;
    let braceLevel = 0;

    const delimLength = delimiter.length;

    while (index < text.length) {
        const character = text[index];

        if (braceLevel <= 0 &&
            text.slice(index, index + delimLength) === delimiter) {
            return index;
        } else if (character === "\\") {
            index++;
        } else if (character === "{") {
            braceLevel++;
        } else if (character === "}") {
            braceLevel--;
        }

        index++;
    }

    return -1;
};

const katexExt = {
    name: "katex",
    level: "inline",
    start(src) {
        let pos = 0;
        while (src) {
            pos = src.indexOf('$', pos)
            if (pos==-1) {
                return;
            }
            if (pos==0 || src.charAt(pos-1)!='\\') {
                return pos
            }
            pos += 1
        }
    },
    tokenizer(src, tokens) {
        if (!src.startsWith("$")) {
            return;
        }
        let delimiter = "$";
        if (src.startsWith("$$")) {
            delimiter = "$$";
        }
        let i = findEndOfMath(delimiter, src, delimiter.length)
        if (i==-1) {
            return;
        }
        return {
            "type": "katex",
            "raw": src.substring(0, i+delimiter.length),
            "text": src.substring(delimiter.length, i),
            "displayMode": delimiter.length==2,
        }
    },
    renderer(token) {
        return katex.renderToString(token.text, {
            displayMode: token.displayMode,
            throwOnError: false,
        })
    }
}
marked.use({extensions: [katexExt]})

// embed youtube video ---

var youtubePrefix = "https://www.youtube.com/watch?v=";
const embedYoutube = {
    walkTokens(token) {
        if (token.type!="image")
            return
        if (!token.href.startsWith(youtubePrefix))
            return
        Object.assign(token, {
            type: "youtube",
            raw: token.raw,
            text: token.text,
            href: token.href.substring(youtubePrefix.length)

        })
    },
    extensions: [{
        name: "youtube",
        level: "inline",
        renderer(token){
            return `<iframe 
                width=600 height=300
                src="https://www.youtube.com/embed/${token.href}?rel=0"
                frameborder=0 allowfullscreen=true>
            </iframe>`
        }
    }]
}
marked.use(embedYoutube)

// page loading ---

async function loadPage() {
    let content = document.getElementById('content');

    let url = URL.parse(window.location.href)
    if (url.hash == "") {
        content.innerHTML = marked.parse("# no markdown file specified")
        document.title = "error"
        return
    }
    var hash = url.hash.substring(1)
    url.hash = ""
    url = new URL(hash, url.toString())

    try {
        const resp = await fetch(url)
        if (!resp.ok) {
            content.innerHTML = marked.parse("# "+resp.status+" "+resp.statusText)
            return
        }
        content.innerHTML = marked.parse(await resp.text())
        let h1Elements = document.body.getElementsByTagName('h1');
        document.title = h1Elements.length>0 ? h1Elements[0].innerText : "untitled";
    } catch (err) {
        content.innerHTML = marked.parse("# "+err)
        document.title = "error"
    }
}

window.onload = loadPage;
window.onhashchange = loadPage;
