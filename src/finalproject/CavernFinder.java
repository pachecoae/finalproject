package finalproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CavernFinder {
	private Cell[][] theCave;
	private ArrayList<Robot> robots;
	private int currentRobot;
	
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
