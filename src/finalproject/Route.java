package finalproject;

import java.util.ArrayList;

public class Route {
	private String cavern;
	private ArrayList<Direction> theRoute;
	
	public enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
	
	public String getCavern() {
		return cavern;
	}
}
