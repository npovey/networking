package webScraper2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkPageRetriever implements PageRetriever {

	@Override
	public String downloadPage(String url) {
		// TODO Auto-generated method stub
		String result;
		URL urlForGetRequest;
		String readLine = null;
		HttpURLConnection conection;
		int responseCode;

		try {
			urlForGetRequest = new URL(url);
			conection = (HttpURLConnection) urlForGetRequest.openConnection();
			conection.setRequestMethod("GET");
			
			// will give us 1000 millisecond to read
			// stops the program from blocking after 1000ms
			conection.setReadTimeout(1000); // retry after delay
			responseCode = conection.getResponseCode();
			System.out.println("responseCode" + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
				StringBuffer response = new StringBuffer();
				while ((readLine = in.readLine()) != null) {
					response.append(readLine);
				}
				in.close();
				result = response.toString();
				System.out.println("JSON String Result " + result);

			} else {
				System.out.println("GET NOT WORKED " + url);
				return null;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return result;

	}

}
