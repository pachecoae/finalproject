package finalproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CavernFinder {
	private Cell[][] theCave;
	private ArrayList<Robot> robots;
	private int currentRobot;
	private HashMap<Character, String> cells;
	private static final String MAP_NAME = "caveMap.csv";
	private static final String LEGEND_NAME = "legend.txt";
	private int numX;
	private int numY;
	private static final int MAX_SIZE = 30;
	
//	this.board = new Board(this);
//	this.rooms = new HashMap<Character, String>();
//	this.players = new ArrayList<>();
//	this.mapFile = map;
//	this.legendFile = legend;
//	this.playerFile = players;
//	this.deckFile = deck;
//	this.deck = new ArrayList<>();
//	this.solution = new Solution();
//	this.turn = 5;

	public CavernFinder() {
		this.robots = new ArrayList<Robot>();
		this.currentRobot = 0;
		this.cells = new HashMap<Character, String>();
	}

	public void loadConfigFiles() {
		try {
			loadLegend();
			loadCellConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadLegend() throws Exception {
		FileReader reader = new FileReader(LEGEND_NAME);
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
		fileIn.close();
	}

	public void loadCellConfig() throws FileNotFoundException, Exception {
		theCave = new Cell[MAX_SIZE][MAX_SIZE];
		FileReader reader = new FileReader(MAP_NAME);
		Scanner fileIn = new Scanner(reader);
		numX = 0;
		numY = 0;

		// Read board configuration file line by line

		while (fileIn.hasNextLine()) {
			// Read the first line
			String newLine = fileIn.nextLine();
			// Split the row into string "cells"
			String[] parts = newLine.split(",");
			// Set the numCols initially, or check to make sure it's the same
			if (numX == 0)
				numX = parts.length;
			else if (parts.length != numX) {
				fileIn.close();
				throw new Exception("Column length mismatch!");
			}
			// Loop through the parts array to initialize cells
			for (int i = 0; i < parts.length; i++) {
					theCave[numY][i] = new Cell(i, numY, parts[i].charAt(0));
			}
			// Update rows
			numY++;
		}
		fileIn.close();
	}

	public Cell getCellAt(int x, int y) {
		return theCave[y][x];
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
