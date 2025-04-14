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

import markedKatex from "https://cdn.jsdelivr.net/npm/marked-katex-extension@5.1.4/+esm";
const options = {
  throwOnError: false
};
marked.use(markedKatex(options));

// page loading ---

async function loadPage() {
    let content = document.getElementById('content');

    let url = URL.parse(window.location.href)
    if (url.hash == "") {
        content.innerHTML = marked.parse("# no markdown file specified")
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
    } catch (err) {
        content.innerHTML = marked.parse("# "+e)
    }
}

window.onload = loadPage;
window.onhashchange = loadPage;
