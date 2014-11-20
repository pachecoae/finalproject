package finalproject;

public class Cell {
	private char cellName;
	private CellType cellType;
	private int x;
	private int y;

	public Cell(int newX, int newY, char newCellName) throws Exception {
		x = newX;
		y = newY;
		cellName = newCellName;

		if (cellName == 'P') {
			cellType = CellType.PATH;
		} else if (cellName == 'W') {
			cellType = CellType.WALL;
		} else if (cellName == '~') {
			cellType = CellType.PATH;
		} else if (cellName == 'A' || cellName == 'B' || cellName == 'C'
				|| cellName == 'D' || cellName == 'E' || cellName == 'F'
				|| cellName == 'G' || cellName == 'H') {
			cellType = CellType.CAVE;
		} else {
			throw new Exception("Invalid cell type found");
		}
	}

	public enum CellType {
		CAVE, PATH, WALL;
	}

	// Getters and Setters
	public char getCellName() {
		return cellName;
	}

	public CellType getCellType() {
		return cellType;
	}
}
