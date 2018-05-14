package avaj_launchControl;

import java.util.ArrayList;
import java.util.List;

import avaj_launchControl.aircraftControl.Flyable;

public class Tower {
	private List<Flyable> observers = new ArrayList<Flyable>();
	
	protected void conditionsChanged() {
		for(int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}
	
	public void register(Flyable flyable) {
		if (observers.contains(flyable))
			return;
		observers.add(flyable);
	}
	
	public void unregister(Flyable flyable) {
		observers.remove(flyable);
	}
}

