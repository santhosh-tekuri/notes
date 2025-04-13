import { marked } from "https://cdn.jsdelivr.net/npm/marked/lib/marked.esm.js";

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

const walkTokens = (token) => {
    if (token.type != 'link') {
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
marked.use({walkTokens})

window.onload = loadPage;
window.onhashchange = loadPage;
