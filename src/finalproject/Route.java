package finalproject;

import java.util.ArrayList;

import finalproject.Route.Direction;

public class Route {
	private char cellName;
	private ArrayList<Direction> theRoute;
	
	public enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
	
	public char getCellName() {
		return cellName;
	}
	
	public ArrayList<Direction> getTheRoute() {
		return theRoute;
	}
}
