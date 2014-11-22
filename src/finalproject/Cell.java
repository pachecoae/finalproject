package finalproject;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private char cellName;
	private CellType cellType;
	private int x;
	private int pixelX;
	private int y;
	private int pixelY;
	private static final int XYDIM = 20;

	public Cell(int newX, int newY, char newCellName) throws Exception {
		x = newX;
		pixelX = x * XYDIM;
		y = newY;
		pixelY = y * XYDIM;
		cellName = newCellName;

		if (cellName == 'P') {
			cellType = CellType.PATH;
		} else if (cellName == 'W') {
			cellType = CellType.WALL;
		} else if (cellName == '~') {
			cellType = CellType.PATH;
		} else if (cellName == 'A' || cellName == 'B' || cellName == 'C' || cellName == 'D' || cellName == 'E'
				|| cellName == 'F' || cellName == 'G' || cellName == 'H') {
			cellType = CellType.CAVE;
		} else {
			throw new Exception("Invalid cell type found");
		}
	}

	void draw(Graphics g, CavernFinder c) {
		if (this.getCellType() == CellType.PATH) {
			if (this.getCellName() == '~') {
				g.setColor(Color.BLACK);
				g.fillRect(pixelX, pixelY, XYDIM, XYDIM);
				g.setColor(Color.BLACK);
				g.drawRect(pixelX, pixelY, XYDIM, XYDIM);
				g.setColor(Color.WHITE);
				g.drawString("~", pixelX + XYDIM/2, pixelY + XYDIM/2);
			} else {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(pixelX, pixelY, XYDIM, XYDIM);
				g.setColor(Color.BLACK);
				g.drawRect(pixelX, pixelY, XYDIM, XYDIM);
			}
		}
		if (this.getCellType() == CellType.WALL) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(pixelX, pixelY, XYDIM, XYDIM);
			g.setColor(Color.BLACK);
			g.drawRect(pixelX, pixelY, XYDIM, XYDIM);
		}
		if (this.getCellType() == CellType.CAVE) {
			g.setColor(Color.GRAY);
			g.fillRect(pixelX, pixelY, XYDIM, XYDIM);
			g.setColor(Color.BLACK);
			g.drawRect(pixelX, pixelY, XYDIM, XYDIM);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(cellName), pixelX + XYDIM / 3, pixelY + XYDIM / 2 + XYDIM / 4);
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
