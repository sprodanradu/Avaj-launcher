package avaj_launchControl.aircraftControl;

import java.util.HashMap;
import java.util.Map;

import avaj_launchControl.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ")"
				+ " registered to weather tower.");
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		Map<String, String> mapMess = new HashMap<String, String>();
		mapMess.put("SUN", "Getting a tan");
		mapMess.put("FOG", "Blind as fuck!!!");
		mapMess.put("SNOW", "Soooo much cocaineeee");
		mapMess.put("RAIN", "Because you left me..");
		
		System.out.println("Helicopter#" + name + "(" + id + "): " + mapMess.get(weather));

		if (weather.equals("SUN")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 10, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 2);
		} else if (weather.equals("RAIN")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 5, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 0);
		} else if (weather.equals("FOG")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 1, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 0);
		} else if (weather.equals("SNOW")) {
			coordinates = new Coordinates(
					coordinates.getLongitude() + 0, 
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 12);
		}
		if (coordinates.getHeight() == 0) {
			System.out.println("Helicopter#" + name + "(" + id + ") landing.");
			weatherTower.unregister(this);
			System.out.println("Tower says: Helicopter#" + name + "(" + id + ")" + " unregistered from weather tower.");
		}
			
	}

}
