package tests;

import static org.junit.Assert.fail;

import java.util.Stack;

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
	private Route route;

	@Before
	public void setup() {
		cavernFinder = new CavernFinder("caveMap.csv", "legend.txt");
		cavernFinder.loadConfigFiles();
		cavernFinder.getRobots().add(robot1);
		cavernFinder.getRobots().add(robot2);

		// Create route to nearest cave.
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);

		route.getTheRoute().add(Direction.WEST);
		route.getTheRoute().add(Direction.WEST);
		route.getTheRoute().add(Direction.WEST);
		route.getTheRoute().add(Direction.WEST);
		route.getTheRoute().add(Direction.WEST);
		route.getTheRoute().add(Direction.WEST);
		route.getTheRoute().add(Direction.WEST);

		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);
		route.getTheRoute().add(Direction.NORTH);

		route.getTheRoute().add(Direction.WEST);
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

	@Test
	public void testFindCave() {
		robot2.getRoutesKnown().add(route);
		robot1.askRobot(robot2, 'E');
		Route newRoute = robot1.findNewCave();
		Assert.assertNotSame(robot2.getRoutesKnown().get(0), newRoute);
		Assert.assertNotSame('E', newRoute.getCellName());
	}

	// Test Robot functions
	@Test
	public void testAskRobot() {
		robot2.getRoutesKnown().add(route);
		robot1.askRobot(robot2, 'E');
		Assert.assertEquals(route, robot1.getRoutesKnown().get(0));
	}

	@Test
	public void testMoveOneCell() {
		robot1.returnHome();
		robot1.moveOneCell(Direction.NORTH);
		Assert.assertEquals(28, robot1.getxCoord());
		Assert.assertEquals(27, robot1.getyCoord());

		robot1.returnHome();
		robot1.moveOneCell(Direction.WEST);
		Assert.assertEquals(27, robot1.getxCoord());
		Assert.assertEquals(28, robot1.getyCoord());
	}

	@Test
	public void testReturnHome() {
		robot1.returnHome();
		Assert.assertEquals(28, robot1.getxCoord());
		Assert.assertEquals(28, robot1.getyCoord());
	}

	// I didn't think this was needed. It seems to be tested for in testAskRobot and testFindCave.
	// @Test
	// public void testRobotRoute() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testCurrentPath() {
		robot1.getRoutesKnown().add(route);
		robot1.goToCave('E');

		// Check that the top of the stack is equal to the last elements in the route and
		// that the bottom of the stack is equal to the first element in the route. I wasn't
		// sure if you could directly compare a stack to an array list.
		Assert.assertEquals(route.getTheRoute().get(0), robot1.getCurrentPath().get(robot1.getCurrentPath().size()));
		Assert.assertEquals(route.getTheRoute().get(route.getTheRoute().size()), robot1.getCurrentPath().get(0));
	}

	@Test
	public void testTravelledTo() {
		robot1.getRoutesKnown().add(route);
		Assert.assertEquals('E', robot1.getTraveledTo().get(0));;
	}

}
