import { marked } from 'https://cdn.jsdelivr.net/npm/marked@15.0.8/+esm'

// page loading ---

async function fetchURL() {
    let url = URL.parse(window.location.href)
    if (url.hash == "") {
        return "# no markdown file specified"
    }
    var hash = url.hash.substring(1)
    url.hash = ""
    url = new URL(hash, url.toString())

    const resp = await fetch(url)
    if (!resp.ok) {
        return "# "+resp.status+" "+resp.statusText
    } else {
        return resp.text()
    }
}

async function loadPage() {
    var text = await fetchURL()
    document.body.innerHTML = marked.parse(text);
}

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

window.onload = loadPage;
window.onhashchange = loadPage;
