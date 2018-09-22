
var markdownParser = {
   
    parse: function(text,successCallback,errorCallback) {
        cordova.exec(
            successCallback,    // Success callback from the plugin
            errorCallback,      // Error callback from the plugin
            'MarkdownParser',   // Tell cordova to run "MarkdownParser" Plugin
            'parse', 		     // Tell plugin, which action we want to perform
            [text] 	        // Passing list of args to the plugin
        );
    }

}

module.exports = markdownParser;