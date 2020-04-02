package com.searchengine.WebSearchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class Sorting {
	
	public static void pageSort(Hashtable<?, Integer> t,int occur)
	{
		 //Transfer as List and sort it
	       ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
	       Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
	            return o1.getValue().compareTo(o2.getValue());
	        }});
	      
	       Collections.reverse(l);
	       
	      
	       if(occur!=0) {
		       System.out.println("\n------Web Page Ranking-----\n");
		       
		       int n = 5;
		       int j = 1;
				while (l.size() > j && n>0){
					System.out.println("("+j+") "+l.get(j)+" times ");
					j++;
					n--;
				}
	       }
	}

}
