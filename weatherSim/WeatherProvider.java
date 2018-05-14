package weatherSim;

import avaj_launchControl.aircraftControl.Coordinates;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
	
	private WeatherProvider() {}
	
	public static WeatherProvider getProvider() {
		if(weatherProvider == null) {
			weatherProvider = new WeatherProvider();
		}
		return weatherProvider;
	}
	
	public String getCurrentWeather(Coordinates coordinates) {
		int lat = coordinates.getLatitude();
		int longi = coordinates.getLongitude();
		int height = coordinates.getHeight();
		
		double sum = ((lat + longi + height) / 9.0) * Math.random();
		int decimal = (int)sum;
		int index = (int)((sum - decimal) * 4);
		
		return weather[index];
	}
	
}
