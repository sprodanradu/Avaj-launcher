package avaj_launchControl.aircraftControl;

import java.util.HashMap;
import java.util.Map;

import avaj_launchControl.WeatherTower;

public class Baloon extends Aircraft implements Flyable{
	private WeatherTower weatherTower;
	
	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}
	
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ")" + " registered to weather tower.");
	}
	
	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		Map<String, String> mapMess = new HashMap<String, String>();
		mapMess.put("SUN", "Bikini time!");
		mapMess.put("FOG", "Fuck you fog");
		mapMess.put("SNOW", "Snif snif");
		mapMess.put("RAIN", "Got shampoo?");

		System.out.println("Baloon#" + name + "(" + id + "): " + mapMess.get(weather));
		
		if (weather.equals("SUN")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 2, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 4);
		} else if (weather.equals("RAIN")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 5);
		} else if (weather.equals("FOG")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 3);
		} else if (weather.equals("SNOW")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 15);
		}
		if (coordinates.getHeight() == 0) {
			System.out.println("Balon#" + name + "(" + id + ") landing.");
			weatherTower.unregister(this);
			System.out.println("Tower says: Baloon#" + name + "(" + id + ")" + " unregistered from weather tower.");
		}
		
	}

	
}
