
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	 static HashSet<String> h = new HashSet<String>(); 
		
	
	public static String  webCrawl(String link)
	{
		String crawlLink=link;
		try {
			
			 Document doc = Jsoup.connect(link).get();

			 Elements linksOnPage = doc.select("a[href]");
			 for (Element page : linksOnPage) {
				 System.out.println("link: "+page.attr("abs:href")+" "+Pattern.matches(link+"*", page.attr("abs:href")));
				 if(h.contains(page.attr("abs:href")))
				 {
					 continue;
				 }
				 else {
					 crawlLink=crawlLink+ " "+page.attr("abs:href");
					 h.add(page.attr("abs:href"));
				 }
				
					 }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return crawlLink;		
	}
	
	public static void htmlToText(String[] htmlFile)
	{
		try {
			String txt;
			for(int i=0;i<htmlFile.length;i++)
			{
				 Document doc = Jsoup.connect(htmlFile[i]).get();
				 txt=doc.text();
				 //System.out.println(htmlFile[i]);
				 String fileName = doc.title().replaceAll("[^a-zA-Z0-9_-]", "")+".txt";
				// System.out.println(fileName);
				 BufferedWriter out = new BufferedWriter( 
		                 new FileWriter("C:\\Users\\ASUS\\Desktop\\Files\\"+fileName, true)); 
		          out.write(htmlFile[i]+" "+txt); 
		          out.close(); 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public static void filesFinder()
	{
		String crawl=webCrawl("https://www.geeksforgeeks.org/");
		 String str="https://www.geeksforgeeks.org/";

		 int i=1;
		 
			 String[] w=crawl.split(" ");

				 if(!str.contains(w[1]))
				 {	

					i++;
					 str=str+" "+w[1];
					 crawl=crawl+" "+webCrawl(w[1]);
				 }
			 
				 System.out.println(crawl);
		 String[] sa=crawl.split(" ");
		 System.out.println(sa.length);
		 String[] files=new String[sa.length];
		 for(int p=0;p<sa.length;p++) {
			files[p]=sa[p];
			
		 }
		 htmlToText(files);
	}
	
	public static void main(String[] args) {
		Crawler.filesFinder();
	}

}
