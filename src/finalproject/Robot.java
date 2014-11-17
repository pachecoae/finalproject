package finalproject;

import java.util.ArrayList;
import java.util.Stack;

import finalproject.Route.Direction;

public class Robot {
	private int xCoord;
	private int yCoord;
	private ArrayList<Route> routesKnown;
	private ArrayList<Direction> currentPath;
	private ArrayList<Cell> traveledTo;
	private CavernFinder cavernFinder;
	
	public Robot(int x, int y, CavernFinder cf) {
		xCoord = x;
		yCoord = y;
		cavernFinder = cf;
		routesKnown = new ArrayList<Route>();
		currentPath = new ArrayList<Direction>();
		traveledTo = new ArrayList<Cell>();
	}
	
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
	
	public Route askRobot(char cavern) {
		for (Route r : routesKnown) {
			if (cavern == r.getCellName()) return r;
		}
		Route temp = new Route();
		return temp;
	}
	
	public void returnHome() {
		for (int i = currentPath.size(); i > 0; i--) {			
			switch (currentPath.get(i)) {
			case NORTH:
				moveOneCell(Direction.SOUTH);
				break;
			case SOUTH:
				moveOneCell(Direction.NORTH);
				break;
			case EAST:
				moveOneCell(Direction.WEST);
				break;
			case WEST:
				moveOneCell(Direction.EAST);
				break;
			}
		}
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

	public ArrayList<Direction> getCurrentPath() {
		return currentPath;
	}

	public ArrayList<Cell> getTraveledTo() {
		return traveledTo;
	}
}
