package com.searchengine.WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchWord {

	public static int wordSearch(File filePath, String p1)
	{
		int counter=0;
		String data="";
		try
    	{
    		BufferedReader Object = new BufferedReader(new FileReader(filePath));
    		String line = null;
    		
              while ((line = Object.readLine()) != null){
            	  data= data+line;
             }
              Object.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("Exception:"+e);
    	}
		// Finding the position of the word...............
		String txt = data;
			
		int offset1a = 0;

		for (int loc = 0; loc <= txt.length(); loc += offset1a + p1.length()) 
		{

			offset1a = WebSearchEngine.search1(p1, txt.substring(loc)); 
			if ((offset1a + loc) < txt.length()) {
				counter++;
				System.out.println(p1 + " at position " + (offset1a + loc));  //printing position of word
			}
		}
		if(counter!=0)	{			
			System.out.println("\nFound in "+filePath.getName()); // Founded from which text file..			
		}
		return counter;
	}
	
	//finds strings with similar pattern and calls edit distance() on those strings
	public static void findData(File _sourceFile,int fileNumber,Matcher _m3,String p1) throws FileNotFoundException,ArrayIndexOutOfBoundsException{
    	EditDistance.findWord(_sourceFile, fileNumber, _m3, p1);
    }
	
	public static void altWord(String p1)
	{

	      String line = " ";
	      String pattern3 = "[a-zA-Z0-9]+";
	      
	     
	      // Create a Pattern object
	      Pattern r3 = Pattern.compile(pattern3);
	      // Now create matcher object.
	      Matcher m3 = r3.matcher(line);
	      int _fileNumber=0;

				File _directory = new File(System.getProperty("user.dir")+"\\textFiles\\");
				File[] _fileArray = _directory.listFiles();
				for(int i=0;i<100;i++)
				{
					try
					{
						findData(_fileArray[i],_fileNumber,m3,p1);
						_fileNumber++;
					} 
					catch(FileNotFoundException e) {
						System.out.println(e);
					}
				}
		
		        Integer value =1;
		        
		        System.out.println(WebSearchEngine.numbers.toString());
		        System.out.println("Did you mean? ");
		        for(Map.Entry entry: WebSearchEngine.numbers.entrySet()){
		        	if(value == entry.getValue()) {
		        		
		        		 System.out.print(entry.getKey()+"  ");
			           
		            }

		        }	        
	}
}
