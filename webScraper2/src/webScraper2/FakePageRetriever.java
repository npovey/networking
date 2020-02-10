package webScraper2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FakePageRetriever implements PageRetriever {

	@Override
	public String downloadPage(String url) {
		Path path = null;
		if(url.equals("uw.edu")) {
			path = FileSystems.getDefault().getPath("uw_page.html");
		} else if (url.equals("http://www.facebook.com/UofWA")) {
			path = FileSystems.getDefault().getPath("facebook.com_UofWA.htm");
		} else {
			return null;
		}
	    
	    try {
	    	String s = new String(Files.readAllBytes(path));
	    	return s;
	    } catch(IOException e) {
	    	System.out.println("IO exception");
	    	return null;
	    }
	}
}
