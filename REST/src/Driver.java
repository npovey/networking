// let url = 'https://api.openweathermap.org/data/2.5/weather?q=Seattle,US&units=imperial&APPID=d3211cf3c037f0525443f77fff7fa280';
// https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
//https://newsapi.org/v2/everything?q=bitcoin&from=2019-12-28&sortBy=publishedAt&apiKey=1cf10b946e71457b8f68038c80a77e83
// temperature: https://api.openweathermap.org/data/2.5/weather?q=Seattle&units=imperial&APPID=d3211cf3c037f0525443f77fff7fa280
// String pattern =  "\"(title)\":\"([^\"]*)\"";
//"https://newsapi.org/v2/everything?q=Seattle+News&sortBy=content&apiKey=1cf10b946e71457b8f68038c80a77e83";
// https://api.openweathermap.org/data/2.5/weather?q=Sammamish&units=imperial&APPID=d3211cf3c037f0525443f77fff7fa280
// 	string url = "https://httpstat.us/"

/**
 * <b>Weather</b> is the main program to run. User enters a city through command line
 * and gets 1) weather 2)news about the city
 * @version 1.0
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.*;

public class Driver {
	public static final String URL_WEATHER1 = "https://api.openweathermap.org/data/2.5/weather?q=";
	public static final String URL_WEATHER2 = "&units=imperial&APPID=d3211cf3c037f0525443f77fff7fa280";

	public static final String URL_NEWS1 = "https://newsapi.org/v2/everything?q=";
	public static final String URL_NEWS2 = "+News&sortBy=content&apiKey=1cf10b946e71457b8f68038c80a77e83";
	public static final int MAX_RETRIES = 3;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Must enter a city name!");
			System.out.println("Example: java -cp .:bin:gson-2.8.6.jar Driver Seattle");
			System.out.println("Example: java -cp .:bin:gson-2.8.6.jar Driver Redmond");
			return;
		}

		String city = args[0];
		for (int i = 1; i < args.length; i++) {
			city += " " + args[i];
		}

		// printTemp(city);
		printNews(city);
	}

	/**
	 * @param queryCity The city name that needs to be looked up.
	 * @effects Prints a temperature at the given city
	 */
	public static void printTemp(String queryCity) {
		// Turn the string into a URL object
		String jsonString = "";
		try {
			String encodedCity = URLEncoder.encode(queryCity, "UTF-8");
			String queryURL = URL_WEATHER1 + encodedCity + URL_WEATHER2;
			jsonString = getConnectionToAPI(queryURL);
		} catch (IOException ioe) {
			System.out.println("Can't get temperature for the given City");
		}

		Gson g = new Gson();
		Result res = g.fromJson(jsonString, Result.class);
		Main main = res.getMain();
		System.out.println("Temperature in " + queryCity + " is " + main.getTemp() + " F");
		System.out.println();
	}

	/**
	 * @param queryCity The city name that needs to be looked up.
	 * @effects Prints a news about the given city
	 */
	public static void printNews(String queryCity) {
		System.out.println("Popular NEWS from " + queryCity);
		String jsonString = "";
		try {
			String encodedCity = URLEncoder.encode(queryCity, "UTF-8");
			String queryURL = URL_NEWS1 + encodedCity + URL_NEWS2;
			jsonString = getConnectionToAPI(queryURL);
			if (jsonString == null) {
				return;
			}

		} catch (SocketTimeoutException ex) {
			System.out.println("Error: timedout");
			return;
		} catch (IOException ioex) {
			System.out.println("Error: can't get news from NEWSAPI");
			return;
		}

		NewsResult res = null;
		try {
			Gson g = new Gson();
			res = g.fromJson(jsonString, NewsResult.class);
		} catch (Exception ex) {
			System.out.println("Gson failed to read");
			return;
		}
		ArrayList<Article> article = (ArrayList<Article>) res.getArticles();
		if (article.size() == 0) {
			System.out.println("No news articles found with given city search");
		}
		for (int i = 0; i < article.size(); i++) {
			System.out.println(article.get(i).getTitle());
			System.out.println(article.get(i).getDescription());
			System.out.println(article.get(i).getUrl());
			System.out.println();
			if (i == 3) {
				return;
			}
		}
	}

	/**
	 * @param encodedCity The city name must be encoded as it becomes part of URL.
	 * @effects Connects to a given URL and fetches the JSON string
	 * @return string with JSON file
	 * @throws IOException
	 */
	public static String getConnectionToAPI(String queryURL) throws IOException {
		String env = "testingUrl";
		String value = System.getenv(env);
		if (value != null) {
			queryURL = value;
		}

		HttpURLConnection urlConn = null;
		int responseCode = 0;
		URL url = null;
		int numTries = 0;
		while (responseCode != 200 && numTries != MAX_RETRIES) {
			url = new URL(queryURL);
			urlConn = (HttpURLConnection) url.openConnection();

			if (urlConn != null) {
				// retry and delay
				urlConn.setReadTimeout(1000 * (numTries + 1) * (numTries + 1));
			}
			urlConn.setRequestMethod("GET");
			urlConn.connect();

			responseCode = urlConn.getResponseCode();
			if (responseCode == 200) {
				break;
			}

			if (responseCode >= 500) {
				numTries++;
				continue;
			} else {
				if (responseCode >= 300 && responseCode < 400) { // 300 errors redirection
					System.out.println("Redirection");
				} else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) { // 401
					System.out.println("Authorization error. Check API key ");
					return null;
				} else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) { // 403
					System.out.println("Forbidden error. Not having necessary permission");
					return null;
				} else {
					System.out.println("400 ERROR: Check input URL");
					return null;
				}
			}
		} // end of while loop
		String jsonString = scan(urlConn);
		return jsonString;
	}

	/**
	 * @param url The URL object that we can read from.
	 * @effects Scans the connection
	 * @return string with JSON file
	 * @throws IOException
	 */
	public static String scan(HttpURLConnection urlConn) throws IOException {
		String jsonText = "";
		Scanner sc = new Scanner(urlConn.getInputStream());
		while (sc.hasNext()) {
			jsonText += sc.nextLine();
		}
		sc.close();
		return jsonText;
	}
}
