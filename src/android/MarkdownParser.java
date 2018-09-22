package com.rkm.cordovaplugins.markdownparser;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedHashMap;

import android.util.Pair;

public class MarkdownParser extends CordovaPlugin {

    CallbackContext _callbackContext = null;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        _callbackContext = callbackContext;

        if (action.equals("parse"))
        {
            cordova.getThreadPool().execute(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            String markdownText = data.getString(0);
                            parse(markdownText);
                        }
                        catch (JSONException ex){}
                    }
                }
            );
            return true;
        }
        else {
            return false;
        }
    }

    public void parse(String markdownText) {

        // Get string text from Array input
        String html = markdownText;

        // Declare associative arrays for regular expressions
        LinkedHashMap<String, Pair<String,String>> markdownRegexp = new LinkedHashMap<String, Pair<String,String>>();
        
        markdownRegexp.put("h1",new Pair<String,String>("(^[#]{1})([^#\n]+)$","<h1 class=\"markdown-h1\">$2</h1>"));
        markdownRegexp.put("h2", new Pair<String,String>("(^[#]{2})([^#\n]+)$","<h2 class=\"markdown-h2\">$2</h2>"));
        markdownRegexp.put("h3", new Pair<String,String>("(^[#]{3})([^#\n]+)$","<h3 class=\"markdown-h3\">$2</h3>"));
        markdownRegexp.put("h4", new Pair<String,String>("(^[#]{4})([^#\n]+)$","<h4 class=\"markdown-h4\">$2</h4>"));
        markdownRegexp.put("h5", new Pair<String,String>("(^[#]{5})([^#\n]+)$","<h5 class=\"markdown-h5\">$2</h5>"));
        markdownRegexp.put("h6", new Pair<String,String>("(^[#]{6})([^#\n]+)$","<h6 class=\"markdown-h6\">$2</h6>"));
        markdownRegexp.put("bold", new Pair<String,String>("([*]{2})([^*\n]+)([*]{2})","<b>$2</b>"));
        markdownRegexp.put("italic", new Pair<String,String>("([*])([^*\n]+)([*])","<i>$2</i>"));
        markdownRegexp.put("linebreak", new Pair<String,String>("([-]{3}[-]*)","<br>"));
        markdownRegexp.put("link", new Pair<String,String>("(([\\[]){1})(.*)([\\]])([(])(.*)([)])","<a href=\"$6\" target=\"_blank\">$3</a>"));
        markdownRegexp.put("quote", new Pair<String,String>("(^[>]{1})(.*)$","<div class=\"markdown-quote\">$2</div>"));
        markdownRegexp.put("ul", new Pair<String,String>("((^[*]+[\\s]+)(.*)(\n|$)*)+","<ul>$0</ul>"));
        markdownRegexp.put("ul_li", new Pair<String,String>("([*][ ])([^\\*\\`\n$]+)","<li>$2</li>"));
        markdownRegexp.put("ol", new Pair<String,String>("((^[0-9]+[.][ ])(.*)(\n|$)*)+","<ol>$0</ol>"));
        markdownRegexp.put("ol_li", new Pair<String,String>("([0-9]+[.][ ])(.*)","<li>$2</li>"));
        markdownRegexp.put("code-block", new Pair<String,String>("(^([ ]{4})(.+)(\n|$)*)+","<div class=\"markdown-code-block\">$0</div>"));
        markdownRegexp.put("code-line-break", new Pair<String,String>("([ ]{4})(.+)","$2<br>"));
        markdownRegexp.put("inline-code", new Pair<String,String>("([`])([^*\n]+)([`])","<span class=\"markdown-inline-code\">$2</span"));

        // Replace markdown expression by HTML correspondance
        for (String key : markdownRegexp.keySet()) {
            String find = markdownRegexp.get(key).first;
            String replace = markdownRegexp.get(key).second;
            html = html.replaceAll("(?m)"+find,replace);
        }

        _callbackContext.success(html);

    }

}
