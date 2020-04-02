package com.searchengine.WebSearchEngine;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;

public class WebSearchEngine {
	
	
	static ArrayList<String> key = new ArrayList<String>();
	static Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
	static int n = 1;
	static Scanner sc = new Scanner(System.in);
	static int R;
	static int[] right; 
	
	public WebSearchEngine(){
		String urlToCrawl = "http://geeksforgeeks.org/";
		Crawler.spider(urlToCrawl);		
	}
	
	//Finding pattern using Boyer Moore method.
	public static int search1(String pat, String txt) {
		BoyerMoore b = new BoyerMoore(pat);
		int offset = b.search(txt);
		return offset;
	}

	
	
		public static void searchEngine(){
			
			WebSearchEngine w = new WebSearchEngine();			
			
			Hashtable<String,Integer> occurrs = new Hashtable<String,Integer>();
			Scanner scan = new Scanner (System.in);
			System.out.println("Enter your search: ");
			String p= scan.nextLine();
			long fileNumber = 0;
			int occur=0;
			int pg=0;
			
			try 
			{				
				File dir = new File(System.getProperty("user.dir")+"\\textFiles\\");
				
				File[] fileArray = dir.listFiles();
				for(int i=0;i<fileArray.length;i++){
					// Searching the word given as an input.
					occur=SearchWord.wordSearch(fileArray[i], p);
					occurrs.put(fileArray[i].getName(), occur);
					if(occur!=0) 
						pg++;		
					fileNumber++;
				}
					
				if(pg==0) {					
					System.out.println("\n\n\n\n\n\n---------------------------------------------------");
					System.out.println("Given word not found");
					System.out.println("Searching in web for similar words.....");
					/*using regex to find similar strings to pattern */
					SearchWord.altWord(p);
				} 
				else {
					//Ranking of Web Pages using merge sort 
					//Collections.sort by default uses merge sort
					Sorting.pageSort(occurrs,pg);
				}
							
			}
			catch (Exception e) {
				System.out.println("Exception:"+e);
			}
		}
	
	// MAIN METHOD.........
		
	public static void main(String[] args) {
		
		WebSearchEngine.searchEngine();
		
		}
	}
	
