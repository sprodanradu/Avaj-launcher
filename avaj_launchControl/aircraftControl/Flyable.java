package avaj_launchControl.aircraftControl;

import avaj_launchControl.WeatherTower;

public interface Flyable {
	void updateConditions();

	void registerTower(WeatherTower weatherTower);
	
}
