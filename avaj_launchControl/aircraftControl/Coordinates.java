package avaj_launchControl.aircraftControl;

public class Coordinates {
	private int longitude = 0;
	private int latitude = 0;
	private int height = 0;
	
	Coordinates(int longi, int lat, int heig) {
		if (longi < 0) {
			longi = 0;
		}
		if (lat < 0) {
			lat = 0;
		}
		if (heig < 0) {
			heig = 0;
		}
		if (lat > 90) {
			heig = 90;
		}
		if (longi > 180) {
			longi = 180;
		}
		if (heig > 100) {
			heig = 100;
		}
		longitude = longi;
		latitude =  lat;
		height = heig;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getHeight() {
		return height;
	}
	
}
