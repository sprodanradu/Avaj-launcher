package avaj_launchControl.aircraftControl;

import java.util.HashMap;
import java.util.Map;

import avaj_launchControl.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
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
		mapMess.put("SUN", "Such a beautiful sunjet");
		mapMess.put("FOG", "I always seem to fogjet..");
		mapMess.put("SNOW", "Snowjet");
		mapMess.put("RAIN", "I will take a rainjet on that");

		System.out.println("JetPlane#" + name + "(" + id + "): " + mapMess.get(weather));
		
		if (weather.equals("SUN")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 10, 
					coordinates.getLatitude() + 2,
					coordinates.getHeight() + 0);
		} else if (weather.equals("RAIN")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 5,
					coordinates.getHeight() + 0);
		} else if (weather.equals("FOG")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 1,
					coordinates.getHeight() + 0);
		} else if (weather.equals("SNOW")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 7);
		}
		if (coordinates.getHeight() == 0) {
			System.out.println("JetPlane#" + name + "(" + id + ") landing.");
			weatherTower.unregister(this);
			System.out.println("Tower says: JetPlane#" + name + "(" + id + ")" + " unregistered from weather tower.");
		}
	}
}
