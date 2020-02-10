package webScraper2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

/** input keywords get hyper links back */
public class WebSearch {
	public static void main(String args[]) {
		System.out.println("Static method");
		// ask reader to input the search word
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a keyword: ");
		String keyword = reader.next();
		System.out.println("keyword: " + keyword);
		reader.close();

		try {
			File file = new File("keyword_links.txt");
			SearchIndex si = new SearchIndex(file);
			Set<String> set = si.takeKeywordGiveBackLinks(keyword);
			// user have to see links associated with that keyword
			if (set == null) {
				System.out.println("keyword is not in the set");
				return;
			}
			for (String s : set) {
				System.out.println(s);
			}
		} catch (FileNotFoundException ex) {
			System.err.println("File not found");
		}
	}
}
