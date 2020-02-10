package webScraper2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class WebScraper2Test {

	@Test
	void testSearchIndex() {
		SearchIndex si = new SearchIndex();
		String linkToVisit = "uw.edu";
		PageRetriever pr = new FakePageRetriever();
		WebScraper2.MainLogic(pr, si, linkToVisit);
		Set<String> urls = new HashSet<String>();
		urls.add("uw.edu");
		urls.add("http://www.facebook.com/UofWA");
		assertEquals(urls, si.takeKeywordGiveBackLinks("Washington"));
	}
	
	@Test
	void tesExtractingLinks() {
		PageRetriever pr = new FakePageRetriever();
		String one ="http://www.facebook.com/UofWA" ;
		String linkToVisit = "uw.edu";
		String page = pr.downloadPage(linkToVisit);
		Set<String> setLinks = WebScraper2.extractLinks(page);
		assertTrue(setLinks.contains(one));
	}
	
	@Test
	void testNetworkPageRetriever() {
		SearchIndex si = new SearchIndex();
		String linkToVisit = "https://www.washington.edu/";
		PageRetriever pr = new NetworkPageRetriever();
		WebScraper2.MainLogic(pr, si, linkToVisit);
		Set<String> urls = new HashSet<String>();
		urls.add("https://www.washington.edu/");
		urls.add("http://www.facebook.com/UofWA");
		Set<String> foundLinks = si.takeKeywordGiveBackLinks("Washington");
		assertTrue(foundLinks.contains("https://www.washington.edu/"));
//		assertTrue(foundLinks.contains("https://finance.uw.edu/sfs/tuition/?utm_source=whitebar&amp;utm_medium=click&amp;utm_campaign=apply&amp;utm_term=tuitionandfees"));		
	}
}
