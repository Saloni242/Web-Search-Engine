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

	public WebSearchEngine() {

		System.out.println("***************************************************");
		System.out.println("****************WEB SEARCH ENGINE******************");
		System.out.println("***************************************************");
		System.out.println("**************TEAM MEMBERS(GROUP 25)***************");
		System.out.println("\n		ARPIT PATEL					   ");
		System.out.println("		DHAIRYA PATEL 				   ");
		System.out.println("		DIPAL MODI		    		   ");
		System.out.println("		HIMAXI PATEL		    	   ");
		System.out.println("		SALONI GOYAL	    		   ");
		System.out.println("\n***************************************************");
	}

	// Finding pattern using Boyer Moore method.
	public static int search1(String pat, String txt) {
		BoyerMoore b = new BoyerMoore(pat);
		int offset = b.search(txt);
		return offset;
	}

	public static void searchEngine() {

		WebSearchEngine w = new WebSearchEngine();
		System.out.println("\n*****************CRAWLING STARTED******************");
		String urlToCrawl = "http://geeksforgeeks.org/";
		Crawler.spider(urlToCrawl);
		System.out.println("\n*****************CRAWLING STOPPED******************");
		Hashtable<String, Integer> occurrs = new Hashtable<String, Integer>();
		Scanner scan = new Scanner(System.in);
		char choice = 'y';


			System.out.println("\n***************************************************");
			System.out.println("\nENTER THE SEARCH WORD: ");
			String p = scan.nextLine();
			System.out.println("***************************************************");
			long fileNumber = 0;
			int occur = 0;
			int pg = 0;

			try {
				File dir = new File(System.getProperty("user.dir") + "\\textFiles\\");

				File[] fileArray = dir.listFiles();
				for (int i = 0; i < fileArray.length; i++) {
					// Searching the word given as an input.
					occur = SearchWord.wordSearch(fileArray[i], p);
					occurrs.put(fileArray[i].getName(), occur);
					if (occur != 0)
						pg++;
					fileNumber++;
				}

				if (pg == 0) {
					System.out.println("\n\n\n\n\n\n---------------------------------------------------");
					System.out.println("Given word not found!!");
					System.out.println("Searching in web for similar words.....");
					/* using regex to find similar strings to pattern */
					SearchWord.altWord(p);
				} else {

					Sorting.pageSort(occurrs, pg);
				}

			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}

			
	}

	// MAIN METHOD.........

	public static void main(String[] args) {

		WebSearchEngine.searchEngine();

	}
}
