import org.json.JSONArray;
import org.json.JSONObject;

public class Data {
	private String temperature, description, windLevel, search, locationName;
	private int valid;
	JSONObject response;
	public Data() {
		temperature = "";
		description = "";
		windLevel = "";
		search = "";
		locationName = "";
		valid = 0;
	}
	public String getTemperature() {
		return temperature;
	}

	public String getDescription() {
		return description;
	}

	public String getWindLevel() {
		return windLevel;
	}

	public String getSearch() {
		return search;
	}

	public String getLocationName() {
		return locationName;
	}
	public int getValid() {
		return valid;
	}

	public void setTemperature(String s) {
		temperature = s;
	}

	public void setDescription(String s) {
		description = s;
	}

	public void setWindLevel(String s) {
		windLevel = s;
	}

	public void setSearch(String s) {
		search = s;
	}

	public void setLocationName(String s) {
		locationName = s;
	}
	public void setValid(int i) {
		valid = i;
	}

	public void onSearched(String s) {
		if (!s.isEmpty()) {
			try {
				Send_HTTP_Request req = new Send_HTTP_Request();
				// Find numbers tagged along the phrase "weather"
				String zipCode = s.replaceAll("[^0-9]", "");

				// If user didn't enter a valid zip code, check for city name
				if (zipCode.equals("")) {
					String cityName = s;
					String instruction = "C" + cityName;
					
					try {
						response = req.trySend(instruction);
						parseJSONData();
						valid = 1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						valid = 0;
					}
				} else {
					String instruction = "Z" + zipCode;
					
					try {
						response = req.trySend(instruction);
						parseJSONData();
						valid = 1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						valid = 0;
					}
				}
			} catch (Exception e) {
				
			}
		}
	}

	public void parseJSONData() throws Exception {
		// Parsing based on the structure of the JSON
		JSONArray weatherData = response.getJSONArray("weather");
		String clouds = weatherData.getJSONObject(0).getString("description");
		char ch = Character.toUpperCase(clouds.charAt(0));
		description = ch + clouds.substring(1);
		
		JSONObject mainData = response.getJSONObject("main");
		temperature = mainData.getString("temp");
		int index = temperature.indexOf('.');
		if(index != -1) {
			temperature = temperature.substring(0,index);
		}

		JSONObject windData = response.getJSONObject("wind");
		windLevel = windData.getString("speed");
		int index2 = windLevel.indexOf('.');
		if(index2 != -1) {
			windLevel = windLevel.substring(0,index2);
		}
		
		JSONObject sys = response.getJSONObject("sys");
		String country = sys.getString("country");
		String cityName = response.getString("name");
		
		locationName = cityName + ", " + country;
	}
}
