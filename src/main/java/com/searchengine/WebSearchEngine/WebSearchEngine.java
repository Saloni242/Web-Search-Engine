package com.searchengine.WebSearchEngine;

public class WebSearchEngine 
{
    public static void main( String[] args )
    {
    	String urlToCrawl = "https://www.geeksforgeeks.org/";
    	Crawler.filesFinder(urlToCrawl);
    }
}
