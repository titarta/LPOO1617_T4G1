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
	
	char map2[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
					{ 'I', ' ', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	
	@Test
	public void testHeroDiesToOgre() throws GameEndException {
		GameMap gameMap = new GameMap(map2);
		Game game = new Game(gameMap);
		game.moveHero(Direction.RIGHT);
		game.moveHero(Direction.RIGHT);
		game.moveHero(Direction.RIGHT);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		try {
			game.moveHero(Direction.UP);
		} catch (GameEndException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	
	@Test
	public void testHeroCatchKey() throws GameEndException {
		GameMap gameMap = new GameMap(map2);
		Game game = new Game(gameMap);
		Coordinate coord = new Coordinate(8,1);
		gameMap.removeEntityFromCoord(gameMap.getEntity(coord), coord);
		Key key = new Key(coord);
		gameMap.addEntityToCoord(key, coord);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.RIGHT);
			game.moveHero(Direction.UP);
		}
		assertEquals(game.getHero().getEntityChar(), 'K');
	}
	
	@Test
	public void testHeroCantOpenDoor() throws GameEndException {
		GameMap gameMap = new GameMap(map2);
		Game game = new Game(gameMap);
		Coordinate coord = new Coordinate(8,1);
		gameMap.removeEntityFromCoord(gameMap.getEntity(coord), coord);
		Key key = new Key(coord);
		gameMap.addEntityToCoord(key, coord);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.UP);
		}
		game.moveHero(Direction.LEFT);
		assertEquals(game.getHero().getCoordinate(), (new Coordinate(1,1)));
	}
	
	@Test
	public void testHeroOpenDoor() throws GameEndException {
		GameMap gameMap = new GameMap(map2);
		Game game = new Game(gameMap);
		Coordinate coord = new Coordinate(8,1);
		gameMap.removeEntityFromCoord(gameMap.getEntity(coord), coord);
		Key key = new Key(coord);
		gameMap.addEntityToCoord(key, coord);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.RIGHT);
			game.moveHero(Direction.UP);
		}
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.DOWN);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.LEFT);
		}
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.LEFT);
		assertTrue(((Door)gameMap.getEntity(new Coordinate(0, 1))).isOpen());
	}
	
	@Test
	public void testHeroWinsKeep() throws GameEndException {
		GameMap gameMap = new GameMap(map2);
		Game game = new Game(gameMap);
		Coordinate coord = new Coordinate(8,1);
		gameMap.removeEntityFromCoord(gameMap.getEntity(coord), coord);
		Key key = new Key(coord);
		gameMap.addEntityToCoord(key, coord);
		gameMap.addWinningCoord(new Coordinate(0, 1));
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.RIGHT);
			game.moveHero(Direction.UP);
		}
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.DOWN);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.LEFT);
		}
		game.moveHero(Direction.UP);
		game.moveHero(Direction.UP);
		game.moveHero(Direction.LEFT);
		try {
			game.moveHero(Direction.LEFT);
		} catch (GameEndException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
}
