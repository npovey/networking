package webScraper2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * read and write map
 * 
 * 
 *
 *
 **/
// next time to save searchIndex to a file

public class SearchIndex {

	// use the abstract data type on the left and the concrete class on the right
	// cars: cars.com. toyota.com
	Map<String, Set<String>> map = new HashMap<String, Set<String>>();

	// First constructor takes a file name and extracts info
	public SearchIndex(File keaywordLinks) throws FileNotFoundException {
		Scanner scanner = new Scanner(keaywordLinks);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] splittedArray = line.split(":", 2);

			// splittedArray[0] -> is a keyword
			// splittedArray[1] -> links associated with that keyword
			String[] URLs = splittedArray[1].split(" ");

			// add URLs into a set
			Set<String> setOfLinkes = new HashSet<String>(Arrays.asList(URLs));

			// add keyword and links to the map
			map.put(splittedArray[0], setOfLinkes);
		}
		scanner.close();

	}

	// empty constructor
	public SearchIndex() {

	}

	public Set<String> takeKeywordGiveBackLinks(String keyword) {
		return map.get(keyword);
	}

	// adds one element at the time to the map
	public void add(String keyword, String url) {
		Set<String> set = map.get(keyword);

		if (set == null) {
			set = new HashSet<String>();
			map.put(keyword, set);
			// we can change the set after adding it to the map
			// because the map hold reference to the set
			// instead of the copy.
		}
		set.add(url);
	}
}
