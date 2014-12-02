package tests;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import finalproject.CavernFinder;
import finalproject.Robot;
import finalproject.Route;
import finalproject.Route.Direction;

public class Tests {
	private CavernFinder cavernFinder;
	private Robot robot1;
	private Robot robot2;
	private Robot robot3;
	private Route route;
	private ArrayList<Direction> dir;
	private ArrayList<Direction> homeTest;
	
	@Before
	public void setup() {
		dir = new ArrayList<Direction>();
		homeTest = new ArrayList<Direction>();
		cavernFinder = new CavernFinder();
		cavernFinder.loadConfigFiles();
		robot1 = new Robot(28, 28, cavernFinder);
		robot2 = new Robot(28, 28, cavernFinder);
		robot3 = new Robot(25, 25, cavernFinder);
	
		//cavernFinder.getRobots().add(robot1);
		//cavernFinder.getRobots().add(robot2);

		// Create route to nearest cave.
		
		//LEFT, RIGHT, UP, DOWN
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.WEST);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.NORTH);
//		route.addDirection(Direction.WEST);

		//for optimize test
		dir.add(Direction.WEST);
		dir.add(Direction.WEST);
		dir.add(Direction.WEST);
		dir.add(Direction.NORTH);
		dir.add(Direction.EAST);
		dir.add(Direction.EAST);
		dir.add(Direction.EAST);
		
		homeTest.add(Direction.WEST);
		homeTest.add(Direction.WEST);
		homeTest.add(Direction.WEST);
		homeTest.add(Direction.NORTH);
		homeTest.add(Direction.NORTH);
		homeTest.add(Direction.NORTH);
	}

	// File I/O
	@Test
	public void testMap() {
		Assert.assertEquals('~', cavernFinder.getCellAt(28, 28).getCellName());
		Assert.assertEquals('B', cavernFinder.getCellAt(1, 1).getCellName());
		Assert.assertEquals('W', cavernFinder.getCellAt(23, 19).getCellName());
		Assert.assertEquals('P', cavernFinder.getCellAt(28, 1).getCellName());
		Assert.assertEquals('F', cavernFinder.getCellAt(11, 17).getCellName());
	}

	// Test Robot functions
	@Test
	public void testAskRobot() throws InterruptedException {
		Assert.assertNull(robot1.askRobot(robot1, 'F'));
		robot1.goToCave('F');
		robot2.askRobot(robot1, 'F');
		Assert.assertNotNull(robot2.askRobot(robot2, 'F'));	
	}

	@Test
	public void testMoveOneCell() throws InterruptedException {
		robot1.moveOneCell(Direction.NORTH);
		Assert.assertEquals(28, robot1.getxCoord());
		Assert.assertEquals(27, robot1.getyCoord());

		robot1.moveOneCell(Direction.SOUTH);
		robot1.moveOneCell(Direction.WEST);
		Assert.assertEquals(27, robot1.getxCoord());
		Assert.assertEquals(28, robot1.getyCoord());
	}

	// I didn't think this was needed. It seems to be tested for in testAskRobot and testFindCave.
	// @Test
	// public void testRobotRoute() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testTravelledTo() throws InterruptedException {
		Assert.assertNull(robot1.askRobot(robot1, 'C'));
		robot1.goToCave('C');
		Assert.assertNotNull(robot1.askRobot(robot1, 'C'));
	}
	
	@Test
	public void testOptimize() {
		robot1.giveDirections(dir);
		robot1.optimizeRoute();
		Assert.assertEquals(1, dir.size());;
		Assert.assertEquals(dir.get(0), Direction.NORTH);;
	}
	
	@Test
	public void testReturnHome() throws InterruptedException {
		robot3.giveDirections(homeTest);
		robot3.returnHome();
		Assert.assertEquals(robot3.getxCoord(), 28);
		Assert.assertEquals(robot3.getyCoord(), 28);
	}

}
