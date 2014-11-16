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
		switch (direction) {
		case NORTH:
			yCoord ++;
			break;
		case SOUTH:
			yCoord --;
			break;
			
		case EAST:
			xCoord ++;
			break;
			
		case WEST:
			xCoord --;
			break;

		default:
			break;
		}
	}
	
	public void askRobot(Robot robot, char cavern) {
		ArrayList<Route> learnRoutes = robot.getRoutesKnown();
		for (Route r : learnRoutes) {
			routesKnown.add(r);
		}
	}
	
	public void returnHome() {
		xCoord = 28;
		yCoord = 28;
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
