package finalproject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CavernFinder {
	private Cell[][] theCave;
	private ArrayList<Robot> robots;
	private int currentRobot;
	private HashMap<Character, String> cells;
	private String mapName;
	private String legendName;
	
	public CavernFinder(String mapName, String legendName) {
		super();
	}
	
	public void loadConfigFiles() {
		
	}

	public void loadLegend() throws Exception {
		FileReader reader = new FileReader(legendName);
		Scanner fileIn = new Scanner(reader);
		while (fileIn.hasNextLine()) {
			String newLine = fileIn.nextLine();
			String[] parts = newLine.split(",");

			// Should have 2 parts per line
			if (parts.length != 2) {
				fileIn.close();
				throw new Exception("Legend has too many arguments on one line.");
			}

			// Make sure first part is a char
			if (parts[0].length() != 1) {
				fileIn.close();
				throw new Exception("Cell name is not 1 letter.");
			}

			// Remove spaces from parts[1]
			parts[1] = parts[1].trim();
			this.cells.put(parts[0].charAt(0), parts[1]);
		}
	}
	
//	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
//		FileReader reader = new FileReader(legendFile);
//		Scanner fileIn = new Scanner(reader);
//		// Parse Line by line
//		while (fileIn.hasNextLine()) {
//			String newLine = fileIn.nextLine();
//			String[] parts = newLine.split(",");
//
//			// Should have 2 parts per line
//			if (parts.length != 2) {
//				fileIn.close();
//				throw new BadConfigFormatException("Room config has too many arguments on one line!");
//			}
//
//			// Make sure first part is a char
//			if (parts[0].length() != 1) {
//				fileIn.close();
//				throw new BadConfigFormatException("Room initial is not 1 letter!");
//			}
//
//			// Remove spaces from parts[1]
//			parts[1] = parts[1].trim();
//			this.rooms.put(parts[0].charAt(0), parts[1]);
//		}
//		board.setRooms(rooms);
//		fileIn.close();
//	}
	
	public Cell getCellAt(int x, int y) {
		return theCave[x][y];
	}
	
	// Getters and setters
	public Cell[][] getTheCave() {
		return theCave;
	}
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	public int getCurrentRobot() {
		return currentRobot;
	}
}
