package avaj_launchControl.aircraftControl;

public class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 0;
	
	protected Aircraft(String name, Coordinates coordinates) {
		id = nextId();
		this.name = name;
		this.coordinates = coordinates;
	}
	
	private static long nextId() {
		idCounter++;
		return idCounter;
	}
}
