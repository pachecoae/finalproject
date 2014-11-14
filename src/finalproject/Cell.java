package finalproject;

public class Cell {
	private char cellName;
	private CellType cellType;

	public enum CellType {
		CAVE, WALKWAY, WALL;
	}
	
	// Getters and Setters
	public char getCellName() {
		return cellName;
	}
	
	public CellType getCellType() {
		return cellType;
	}
}
