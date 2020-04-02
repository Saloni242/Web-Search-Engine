package com.searchengine.WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;

/**
 * Uses Edit distance to compare nearest distance between keyword and similar patterns obtained from regex.
 * */
public class EditDistance {

	public static int findEditDistance(String word1, String word2)
	{
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
		
	}
	
	public static int editDistance(String word1, String word2) {
		return EditDistance.findEditDistance(word1,word2);
	}
	
	public static void findWord(File _sourceFile,int fileNumber,Matcher _m3,String p1)
	{
		try
    	{
    		BufferedReader _rederObject = new BufferedReader(new FileReader(_sourceFile));
    		String line = null;

              while ((line = _rederObject.readLine()) != null){
              _m3.reset(line);
              while (_m3.find()) {
            	  WebSearchEngine.key.add(_m3.group());
                }
             }
              _rederObject.close();
              for(int p = 0; p<WebSearchEngine.key.size(); p++){ 
            	  WebSearchEngine.numbers.put(WebSearchEngine.key.get(p), editDistance(p1.toLowerCase(),WebSearchEngine.key.get(p).toLowerCase()));
                }
    	}     
    	catch(Exception e)
    	{
    		System.out.println("Exception:"+e);
    	}
    	
	}
}
