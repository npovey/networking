// job to extract links
// regex taken from: https://urlregex.com/
package webScraper2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper2 {

	public static void main(String args[]) {
		System.out.println("Static method");
		SearchIndex si = new SearchIndex();
		String linkToVisit = "https://www.washington.edu/";
		PageRetriever pr = new NetworkPageRetriever();
		MainLogic(pr, si, linkToVisit);
//		si.toFile(); // in the future
	}

	public static void MainLogic(PageRetriever pr, SearchIndex si, String linkToVisit) {
		Set<String> linksToVisit = new HashSet<String>();
		linksToVisit.add(linkToVisit);
		
		int limit = 5;
		int count = 0;
		while (!linksToVisit.isEmpty() && count < limit) {
			// remove link from the container
			Iterator<String> it = linksToVisit.iterator();
			linkToVisit = it.next();
			it.remove();

			// Download a webpage
			String page = pr.downloadPage(linkToVisit);
			
			if (page == null) {
				continue;
			}

			// extract keywords and add them to the set
			// WORK HERE
			Set<String> wordsFound = new HashSet<String>();
			String[] arrayOfWord = page.split(" ");
			for (String word : arrayOfWord) {
				wordsFound.add(word);
			}

			for (String keyword : arrayOfWord) {
				si.add(keyword, linkToVisit);
			}

			// extract links
			Set<String> extractedLinks = extractLinks(page);

			// add extracted links to links to visit
			for (String link : extractedLinks) {
				linksToVisit.add(link);
			}
			count++;
		}
		
	}

	public static void extractKeyWords(String webpage) {
		// collect keywords from that page
		// add them to text file

	}

	public static Set<String> extractLinks(String webpage) {
		Set<String> extractedLinks = new HashSet<String>();
		String pattern = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(webpage);

		while (m.find()) {
			extractedLinks.add(m.group(0));
			System.out.println(m.group(0));
		}

//		String [] arrayOfWord = webpage.split(" ");
//		for (String word : arrayOfWord) {
//			// if a link
//			extractedLinks.add(word);
//		}
		
		System.out.println(extractedLinks.size());
		return extractedLinks;
	}
}
