package finalproject;

public class Cell {
	private String cave;
	private CellType cellType;

	public enum CellType {
		CAVE, WALKWAY, WALL;
	}
	
	public String getCave() {
		return cave;
	}
}
