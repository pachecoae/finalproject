package finalproject;

import java.util.ArrayList;

import finalproject.Route.Direction;

public class Route {
	private char cellName;
<<<<<<< HEAD
	private ArrayList<Direction> theRoute;
	
	public Route() {
		cellName = ' ';
		theRoute = null;
=======
	public ArrayList<Direction> theRoute;
	
	public Route() {
		cellName = ' ';
		this.theRoute = new ArrayList<Direction>();
>>>>>>> 9eeb907ef26878b5e77642f2f8b5265c6f59e8d9
	}
	
	public Route(char c, ArrayList<Direction> someRoute) {
		theRoute = someRoute;
		cellName = c;
	}
	
	public enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
	
	public char getCellName() {
		return cellName;
	}
	
	public ArrayList<Direction> getTheRoute() {
		return theRoute;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 9eeb907ef26878b5e77642f2f8b5265c6f59e8d9
