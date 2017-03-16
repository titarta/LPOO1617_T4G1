package dkeep.test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import dkeep.logic.*;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class TestDungeonGameLogic {
	
	char [][] map = {{'X','X','X','X','X'},
			{'X','H',' ','G','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	@Test
	public void testMoveHeroIntoToFreeCell() throws GameEndException {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertEquals(new Coordinate(1,1), game.getHero().getCoordinate());
		game.moveHero(Direction.DOWN);
		assertEquals(new Coordinate(1,2), game.getHero().getCoordinate());
	}
	
	@Test
	public void testMoveHeroIntoWall() throws GameEndException {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertEquals(new Coordinate(1,1), game.getHero().getCoordinate());
		game.moveHero(Direction.UP);
		assertEquals(new Coordinate(1,1), game.getHero().getCoordinate());
	}
	
	@Test
	public void testHeroIsCaptureByGuard() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		try {
			game.moveHero(Direction.RIGHT);
		} catch (GameEndException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	
	@Test
	public void testHeroCantPassDoor() throws GameEndException {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.LEFT);
		assertEquals(new Coordinate(1,2), game.getHero().getCoordinate());
	}
	
	@Test
	public void testDoorsOpen() throws GameEndException {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		HashSet<Door> doors = new HashSet<Door>();
		doors.add((Door) gameMap.getEntity(new Coordinate(0,2)));
		doors.add((Door) gameMap.getEntity(new Coordinate(0,3)));
		((Lever)gameMap.getEntity(new Coordinate(1,3))).setDoors(doors);
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.DOWN);
		assertTrue(((Door)gameMap.getEntity(new Coordinate(0,3))).isOpen());
		assertTrue(((Door)gameMap.getEntity(new Coordinate(0,2))).isOpen());
	}
	
	@Test
	public void testHeroWinsMap() throws GameEndException {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		HashSet<Door> doors = new HashSet<Door>();
		doors.add((Door) gameMap.getEntity(new Coordinate(0,2)));
		doors.add((Door) gameMap.getEntity(new Coordinate(0,3)));
		((Lever)gameMap.getEntity(new Coordinate(1,3))).setDoors(doors);
		gameMap.addWinningCoord(new Coordinate(0,2));
		gameMap.addWinningCoord(new Coordinate(0,3));
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.DOWN);
		try {
			game.moveHero(Direction.LEFT);
		} catch (GameEndException e) {
			assertTrue(e.getResult());
			return;
		}
		assertTrue(false);
	}
}
