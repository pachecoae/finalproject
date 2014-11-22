package finalproject;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CavernFinder extends JFrame {
	private Cell[][] theCave;
	private ArrayList<Robot> robots;
	private int currentRobot;
	private HashMap<Character, String> cells;
	private static final String MAP_NAME = "caveMap.csv";
	private static final String LEGEND_NAME = "legend.txt";
	private int numX;
	private int numY;
	private static final int MAX_SIZE = 30;
	private JMenuBar menuBar;
	private DrawCave drawCave;
	private DrawGUI drawGUI;

	public CavernFinder() {
		this.robots = new ArrayList<Robot>();
		this.currentRobot = 0;
		this.cells = new HashMap<Character, String>();
		this.drawCave = new DrawCave(this);
	}

	public void loadConfigFiles() {
		try {
			loadLegend();
			loadCellConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUpGUI() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cavern Explorer");
		add(drawCave, BorderLayout.CENTER);
		setSize(955, 850);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createMenuBar());

		drawGUI = new DrawGUI();
		add(drawGUI, BorderLayout.SOUTH);
		
		setSize(956, 900);
	}

	public JMenuItem createMenuBar() {
		JMenu menu;
		menu = new JMenu("Menu");
		JMenuItem exitAction = new JMenuItem("Exit");
		exitAction.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}
		});

		// Adds the objects to the menu
		menu.addSeparator();
		menu.add(exitAction);

		return menu;
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

	public static void main(String[] args) {
		CavernFinder cF = new CavernFinder();
		cF.loadConfigFiles();
		cF.setUpGUI();
	}
}
