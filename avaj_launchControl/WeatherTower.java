package avaj_launchControl;

import avaj_launchControl.aircraftControl.Coordinates;
import weatherSim.WeatherProvider;

public class WeatherTower extends Tower{
	
	void changeWeather() {
		conditionsChanged();
	}
	
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}
}
