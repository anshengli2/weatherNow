import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Send_HTTP_Request {

	public JSONObject trySend(String s) {
		// Check what code is attached (C for city name or Z for zip code)
		char code = s.charAt(0);
		try {
			if (code == 'C') {
				String city = s.substring(1);
				String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city
						+ ",us&units=imperial&appid=61719917b2148a6c44bafc21c1e2ce64";
				return callWeather(url, city);
			} else {
				String zip = s.substring(1);
				String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + zip
						+ ",us&units=imperial&appid=61719917b2148a6c44bafc21c1e2ce64";
				return callWeather(url, zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject callWeather(String s, String code) throws Exception {
		String url = s;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("Content-Type", "application/json");

		int responseCode = con.getResponseCode();

		// Not a valid zip code
		if (responseCode == 200) {

			// Create a reader
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			// Read in all data
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Read JSON response and print
			JSONObject myResponse = new JSONObject(response.toString());

			return myResponse;

		}
		else {
			return null;
		}
	}

}
