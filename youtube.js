// this script embeds youtube video links

function embedYoutube() {
    var prefix = "https://www.youtube.com/watch?v=";
    var links = document.getElementsByTagName('a');
    var i=0;
    while(i<links.length) {
        var link = links[i];
        var href = link.getAttribute("href");
        if(href.startsWith(prefix)) {
            var iframe = document.createElement("iframe");
            iframe.setAttribute("width", 560);
            iframe.setAttribute("height", 315);
            iframe.setAttribute("src", "https://www.youtube.com/embed/"+href.substring(prefix.length)+"?rel=0");
            iframe.setAttribute("frameborder", "0");
            iframe.setAttribute("allowfullscreen", "true");
            link.parentNode.replaceChild(iframe, link);
        } else
           i++;
    }
}
