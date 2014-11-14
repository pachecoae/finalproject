package finalproject;

import java.util.ArrayList;
import java.util.Stack;

import finalproject.Route.Direction;

public class Robot {
	private int xCoord;
	private int yCoord;
	private ArrayList<Route> routesKnown;
	private Stack<Direction> currentPath;
	private ArrayList<Cell> traveledTo;
	
	public Route findNewCave() {
		//TODO: Add route to known route.
		return new Route();
	}
	
	public void goToCave(char cellName) {
		
	}
	
	public void moveOneCell(Direction direction) {
		
	}
	
	public Route askRobot(Robot robot, char cavern) {
		
		return new Route();
	}
	
	public void returnHome() {
		
	}
	
	// Getters and Setters
	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public ArrayList<Route> getRoutesKnown() {
		return routesKnown;
	}

	public Stack<Direction> getCurrentPath() {
		return currentPath;
	}

	public ArrayList<Cell> getTraveledTo() {
		return traveledTo;
	}
	
	
}
