package webScraper2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class SearchIndexTest {

	@Test
	void test() throws FileNotFoundException {
//		fail("Not yet implemented");
		File file = new File("keyword_links.txt");
		SearchIndex si  = new SearchIndex(file);
		Set<String> set = si.takeKeywordGiveBackLinks("cars");
		Set<String> expectedSet = new HashSet<String>();
		expectedSet.add("cars.com");
		expectedSet.add("toyota.com");
		assertEquals(expectedSet, set);
	}
}
