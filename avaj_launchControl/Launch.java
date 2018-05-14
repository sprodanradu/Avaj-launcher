package avaj_launchControl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import avaj_launchControl.aircraftControl.AircraftFactory;

@SuppressWarnings("serial") 
class NegativeNbException extends Exception {
	NegativeNbException(String s) {
		super(s);
	}
}

@SuppressWarnings("serial") 
class InvalidTypeId extends Exception {
	InvalidTypeId(String s) {
		super(s);
	}
}

@SuppressWarnings("serial") 
class InvalidLine extends Exception {
	InvalidLine(String s) {
		super(s);
	}
}

public class Launch {
	public static int simNb = 0;

	static void checkNb(int nb) throws NegativeNbException {
		if (nb < 0) {
			throw new NegativeNbException("Negative value");
		}
	}
	static void checkLine(String[] s) throws InvalidLine {
		if (s.length != 5) {
			throw new InvalidLine("Invalid line");
		}
	}
	static void checkTypeId(String type, String id) throws InvalidTypeId {
		String[] types = {"Helicopter", "JetPlane", "Baloon"};
		String ids = "BHJ";
		if (!(Arrays.asList(types).contains(type))) {
			throw new InvalidTypeId("Invalid type");
		}
		if (ids.indexOf(id.charAt(0)) == -1) {
			throw new InvalidTypeId("Invalid id");
		}
		if (Integer.parseInt(id.substring(1)) < 0) {
			throw new InvalidTypeId("Invalid id number");
		}
	}

	public static void main(String[] args) {
		String[] strArray;
		String line = new String();
		WeatherTower weatherTower = new WeatherTower();
		AircraftFactory aircraftF = new AircraftFactory();
		line = null;
		
		try {
            PrintStream out = new PrintStream(new FileOutputStream(new File("simulation.txt")));
            System.setOut(out);
        } catch (Exception ex) {ex.printStackTrace();}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));

			try {
					line = reader.readLine();
					simNb = Integer.parseInt(line);
					try {
						checkNb(simNb);
					} catch (NegativeNbException e) {
						e.printStackTrace();
						System.exit(1);
					}
					while ((line = reader.readLine()) != null) {
						strArray = line.split(" ");
						try {
							checkLine(strArray);
						} catch (InvalidLine e1) {
							e1.printStackTrace();
							System.exit(1);
						}
						try {
							checkNb(Integer.parseInt(strArray[2]));
							checkNb(Integer.parseInt(strArray[3]));
							checkNb(Integer.parseInt(strArray[4]));
						} catch (NegativeNbException e) {
							e.printStackTrace();
							System.exit(1);
						}
						try {
							checkTypeId(strArray[0], strArray[1]);
						} catch (InvalidTypeId e) {
							e.printStackTrace();
							System.exit(1);
						}
						aircraftF.newAircraft(
								strArray[0], 
								strArray[1], 
								Integer.parseInt(strArray[2]),
								Integer.parseInt(strArray[3]), 
								Integer.parseInt(strArray[4])).registerTower(weatherTower);
					}
					for (int i = 0; i < 3; i++) {
						weatherTower.changeWeather();
					}
				
			} catch (IOException e1) {
				System.out.println("Could not read first line");
			}

			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Could not close file");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}

}
