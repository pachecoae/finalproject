package finalproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import finalproject.Cell.CellType;
import finalproject.Route.Direction;

public class Robot {
	private int xCoord;
	private int yCoord;
	private ArrayList<Route> routesKnown;
	private ArrayList<Direction> currentPath;
	private ArrayList<Cell> traveledTo;
	private CavernFinder cavernFinder;
	private Color color;
	private int pixelX;
	private int pixelY;
	private static final int XYDIM = 20;
	
	public Robot(int x, int y, CavernFinder cf) {
		xCoord = x;
		pixelX = x * XYDIM;
		yCoord = y;
		pixelY = y * XYDIM;
		Random rgb = new Random();
		int r = rgb.nextInt(256);
		int g = rgb.nextInt(256);
		int b = rgb.nextInt(256);
		color = new Color(r, g, b);
		cavernFinder = cf;
		routesKnown = new ArrayList<Route>();
	}
	

	//TODO: If we find a cavern we aren't looking for, we should still add that route to our list of known routes
	public Route goToCave(char cellName) {
		//Refresh all of the route finding stuff
		traveledTo = new ArrayList<Cell>();
		currentPath = new ArrayList<Direction>();
		boolean discovered = false;
		
		//TODO: Ask the robot behind us if it knows the route
		
		//Need to first check if we already know the route
		for (Route r : routesKnown) {
			if (r.getCellName() == cellName) {
				//If we already know the route, just travel through its directions and then return home
				discovered = true;
				ArrayList<Direction> temp = r.getTheRoute();
				for (Direction d : temp) {
					moveOneCell(d);
				}
				returnHome();
				break;
			}
		}
		
		
		while (!discovered) {
			//If we are at the target cave, we can break
			if (cellName == cavernFinder.getCellAt(xCoord, yCoord).getCellName()) {
				break;
			}
			
			//Check the square we're at for any cave
			if (cavernFinder.getCellAt(xCoord, yCoord).getCellType() == CellType.CAVE) {
				//Check to see if we know the route to this cave
				boolean alreadyKnown = false;
				for (Route r : routesKnown) {
					if (r.getCellName() == cavernFinder.getCellAt(xCoord, yCoord).getCellName()) {
						alreadyKnown = true;
						break;
					}
				}
				//If we haven't seen the cave before, optimize the route and add this to our routes known list
				if (!alreadyKnown) {
					optimizeRoute();
					routesKnown.add(new Route(cavernFinder.getCellAt(xCoord, yCoord).getCellName(), currentPath));
				}
			}

			//Add the current cell to the traveledTo ArrayList
			traveledTo.add(cavernFinder.getCellAt(xCoord, yCoord));
			
			//Otherwise, look in all 4 directions for a valid space to move to (west, east, north, south)
			if (cavernFinder.getCellAt(xCoord - 1, yCoord).getCellType() != CellType.WALL && !traveledTo.contains(cavernFinder.getCellAt(xCoord - 1, yCoord).getCellType())) {
				currentPath.add(Direction.WEST);
				moveOneCell(Direction.WEST);
			}
			else if (cavernFinder.getCellAt(xCoord + 1, yCoord).getCellType() != CellType.WALL && !traveledTo.contains(cavernFinder.getCellAt(xCoord + 1, yCoord).getCellType())) {
				currentPath.add(Direction.EAST);
				moveOneCell(Direction.EAST);
			}
			else if (cavernFinder.getCellAt(xCoord, yCoord - 1).getCellType() != CellType.WALL && !traveledTo.contains(cavernFinder.getCellAt(xCoord, yCoord - 1).getCellType())) {
				currentPath.add(Direction.NORTH);
				moveOneCell(Direction.NORTH);
			}
			else if (cavernFinder.getCellAt(xCoord, yCoord + 1).getCellType() != CellType.WALL && !traveledTo.contains(cavernFinder.getCellAt(xCoord, yCoord + 1).getCellType())) {
				currentPath.add(Direction.SOUTH);
				moveOneCell(Direction.SOUTH);
			}
			
			//If we can't go to any new cells, we should start backtracking by moving backward and updating the current path
			else {
				Direction dir = currentPath.get(currentPath.size() - 1);
				switch (dir) {
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
				currentPath.remove(currentPath.size() - 1);
			}
		}
		returnHome();
		optimizeRoute();
		Route temp = new Route(cellName, currentPath);
		routesKnown.add(temp);
		return temp;
	}
	
	public void moveOneCell(Direction direction) {
		switch (direction) {
		case NORTH:
			yCoord --;
			break;
		case SOUTH:
			yCoord ++;
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
	
	public Route askRobot(Robot robot, char cavern) {
		ArrayList<Route> temp = robot.getRoutesKnown();
		for (Route r : temp) {
			if (cavern == r.getCellName()) return r;
		}
		return null;
	}
	
	public void returnHome() {
		for (int i = currentPath.size() - 1; i > -1; i--) {			
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
	
	public void optimizeRoute() {
		/*This function checks every triple of directions in the current path
		If we have a triple of directions where we move one direction, then orthogonal
		to it, then opposite to it, we can ignore the first and third directions since 
		they cancel each other out and focus solely on the middle direction */
		for (int i = 0; i < currentPath.size() - 2; i++) {
			switch (currentPath.get(i)) {
			case WEST:
				if ((currentPath.get(i+1) == Direction.SOUTH || currentPath.get(i+1) == Direction.NORTH) && currentPath.get(i+2) == Direction.EAST) {
					currentPath.remove(i+2);
					currentPath.remove(i);
					i = i - 2;
				}
				break;
			case EAST:
				if ((currentPath.get(i+1) == Direction.SOUTH || currentPath.get(i+1) == Direction.NORTH) && currentPath.get(i+2) == Direction.WEST) {
					currentPath.remove(i+2);
					currentPath.remove(i);
					i = i - 2;
				}
				break;
			case SOUTH:
				if ((currentPath.get(i+1) == Direction.EAST || currentPath.get(i+1) == Direction.WEST) && currentPath.get(i+2) == Direction.NORTH) {
					currentPath.remove(i+2);
					currentPath.remove(i);
					i = i - 2;
				}
				break;
			case NORTH:
				if ((currentPath.get(i+1) == Direction.EAST || currentPath.get(i+1) == Direction.WEST) && currentPath.get(i+2) == Direction.SOUTH) {
					currentPath.remove(i+2);
					currentPath.remove(i);
					i = i - 2;
				}
				break;
			}
			if (i < 0) i = -1;
		}
	}
	
	// Drawing function for Robots.
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(pixelY, pixelX, XYDIM, XYDIM);
		g.setColor(Color.BLACK);
		g.drawOval(pixelY, pixelX, XYDIM, XYDIM);
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
	
	//For testing purposes
	public void giveDirections(ArrayList<Direction> dir) {
		currentPath = dir;
	}
}
