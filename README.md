# Cordova markdown parser plugin

Plugin developed for one of my projects. Converts markdown syntax into HTML. 

## Installation
    cordova plugin add https://github.com/RoxaneKM/cordova-plugin-markdown-parser.git

## Uninstallation 
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
There may still be some uncaught markdown syntaxes.

## Supported platoforms
* Android
* Browser
* (*iOS comming soon*)

## Future developements
* iOS platform support

## Authors
* [RoxaneKM](https://github.com/RoxaneKM)


