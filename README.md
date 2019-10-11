# Cordova markdown parser plugin

Plugin developed for one of my projects. Converts markdown syntax into HTML. (**PLEASE NOTE:** It's still in development and not yet completed).

## Install
    cordova plugin add https://github.com/RoxaneKM/cordova-plugin-markdown-parser.git

## Uninstall
    cordova plugin rm cordova-plugin-markdown-parser

## How to use

    markdownParser.parse(text,showPreview,parseError);

    function showPreview(text) {
       // Display markdown preview in div element
       var previewDiv = document.getElementById('markdownPreview');
       previewDiv.innerHTML = text;
    }

    function parseError(err) {
        alert('Error parsing text to markdown: '+err);
    }


## Possible issues
This plugin is still in development so there may still be some parsing issues.

## Supported platoforms
* Android
* Browser

## Future developements
* iOS platform support

## Author
* [rkmarechal](https://github.com/rkmarechal)


