package finalproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CavernFinder {
	private Cell[][] theCave;
	private ArrayList<Robot> robots;
	private int currentRobot;
	private String mapName;
	private String legendName;
	
	public CavernFinder(String mapName, String legendName) {
		super();
	}

	public void loadConfigFiles() {
		
	}
	
	public Cell getCellAt(int x, int y) {
		return theCave[x][y];
	}
	
	// Getters and setters
	public Cell[][] getTheCave() {
		return theCave;
	}
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	public int getCurrentRobot() {
		return currentRobot;
	}
}
