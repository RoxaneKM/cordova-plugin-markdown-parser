module.exports = {
    parse: function(successCallback, errorCallback, text) {

    	text = text[0];	// Array passed by markdownparser.js

        // Define find expressions
        var jsonMarkdownRegexpFind = 
            [{
                "h1" : /(^[#]{1})([^#\n]+)$/gm,
                "h2" : /(^[#]{2})([^#\n]+)$/gm,
                "h3" : /(^[#]{3})([^#\n]+)$/gm,
                "h4" : /(^[#]{4})([^#\n]+)$/gm,
                "h5" : /(^[#]{5})([^#\n]+)$/gm,
                "h6" : /(^[#]{6})([^#\n]+)$/gm,
                "bold" : /([*]{2})([^*\n]+)([*]{2})/gm,
                "italic" : /([*])([^*\n]+)([*])/gm,
                "linebreak": /([-]{3}[-]*)/gm,
                "link" : /(([\[]){1})(.*)([\]])([(])(.*)([)])/gm,
                "quote" : /(^[>]{1})(.*)$/gm,
                "ul" : /((^[*]+[\s]+)(.*)(\n|$)*)+/gm,
                "ul_li" : /([*][ ])([^\*\n$]+)/gm,
                "ol" :/((^[0-9]+[.][ ])(.*)(\n|$)*)+/gm,
                "ol_li" : /([0-9]+[.][ ])(.*)/gm,
                "code-block" : /(^([ ]{4})(.+)(\n|$)*)+/gm,
                "code-line-break" : /([ ]{4})(.+)/gm,
                "inline-code" : /([`])([^*\n]+)([`])/gm
            }];
        var markdownRegexpFind = jsonMarkdownRegexpFind[0];

        // Define HTML replacement
        var jsonMarkdownRegexpReplace = 
            [{
                "h1" : '<h1 class="markdown-h1">$2</h1>',
                "h2" : '<h2 class="markdown-h2">$2</h2>',
                "h3" : '<h3 class="markdown-h3">$2</h3>',
                "h4" : '<h4 class="markdown-h4">$2</h4>',
                "h5" : '<h5 class="markdown-h5">$2</h5>',
                "h6" : '<h6 class="markdown-h6">$2</h6>',
                "bold" : '<b>$2</b>',
                "italic" : '<i>$2</i>',
                "linebreak" : '<br>',
                "link" : '<a href="$6" target="_blank">$3</a>',
                "quote" : '<div class="markdown-quote">$2</div>',
                "ul" : '<ul>$&</ul>',
                "ul_li" : '<li>$2</li>',
                "ol" : '<ol>$&</ol>',
                "ol_li" : '<li>$2</li>',
                "code-block" : '<div class="markdown-code-block">$&</div>',
                "code-line-break": '$2<br>',
                "inline-code" : '<span class="markdown-inline-code">$2</span>'
            }];
        var markdownRegexpReplace = jsonMarkdownRegexpReplace[0];

        for (var key in markdownRegexpFind) {
            text = text.replace(markdownRegexpFind[key],markdownRegexpReplace[key]);
        }

        // Send result to successCallback
        if (successCallback != null) {
            successCallback(text);
        }
    }
};

require("cordova/exec/proxy").add("MarkdownParser", module.exports);
