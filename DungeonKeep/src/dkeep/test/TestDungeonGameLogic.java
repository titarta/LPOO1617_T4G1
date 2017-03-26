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
		Game game = new Game(map);
		assertEquals(new Coordinate(1,1), game.getHero().getCoordinate());
		game.moveHero(Direction.DOWN);
		assertEquals(new Coordinate(2,1), game.getHero().getCoordinate());
	}
	
	@Test
	public void testMoveHeroIntoWall() throws GameEndException {
		Game game = new Game(map);
		assertEquals(new Coordinate(1,1), game.getHero().getCoordinate());
		game.moveHero(Direction.UP);
		assertEquals(new Coordinate(1,1), game.getHero().getCoordinate());
	}
	
	@Test
	public void testHeroIsCaptureByGuard() {
		Game game = new Game(map);
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
		Game game = new Game(map);
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.LEFT);
		assertEquals(new Coordinate(2,1), game.getHero().getCoordinate());
	}
	
	@Test
	public void testDoorsOpen() throws GameEndException {
		Game game = new Game(map);
		HashSet<Door> doors = new HashSet<Door>();
		doors.add((Door) game.getGameMap().getEntity(new Coordinate(2,0)));
		doors.add((Door) game.getGameMap().getEntity(new Coordinate(3,0)));
		((Lever)game.getGameMap().getEntity(new Coordinate(3,1))).setDoors(doors);
		game.moveHero(Direction.DOWN);
		game.moveHero(Direction.DOWN);
		assertTrue(((Door)game.getGameMap().getEntity(new Coordinate(3,0))).isOpen());
		assertTrue(((Door)game.getGameMap().getEntity(new Coordinate(2,0))).isOpen());
	}
	
	@Test
	public void testHeroWinsMap() throws GameEndException {
		Game game = new Game(map);
		HashSet<Door> doors = new HashSet<Door>();
		doors.add((Door) game.getGameMap().getEntity(new Coordinate(2,0)));
		doors.add((Door) game.getGameMap().getEntity(new Coordinate(3,0)));
		((Lever)game.getGameMap().getEntity(new Coordinate(3,1))).setDoors(doors);
		game.getGameMap().addWinningCoord(new Coordinate(2,0));
		game.getGameMap().addWinningCoord(new Coordinate(3,0));
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
		Game game = new Game(map2);
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
		Game game = new Game(map2);
		Coordinate coord = new Coordinate(1,8);
		game.getGameMap().removeEntityFromCoord(game.getGameMap().getEntity(coord), coord);
		Key key = new Key(coord);
		game.getGameMap().addEntityToCoord(key, coord);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.RIGHT);
			game.moveHero(Direction.UP);
		}
		assertEquals(game.getHero().getEntityChar(), 'K');
	}
	
	@Test
	public void testHeroCantOpenDoor() throws GameEndException {
		Game game = new Game(map2);
		Coordinate coord = new Coordinate(8,1);
		game.getGameMap().removeEntityFromCoord(game.getGameMap().getEntity(coord), coord);
		Key key = new Key(coord);
		game.getGameMap().addEntityToCoord(key, coord);
		for (int i = 0; i < 7; i++) {
			game.moveHero(Direction.UP);
		}
		game.moveHero(Direction.LEFT);
		assertEquals(game.getHero().getCoordinate(), (new Coordinate(1,1)));
	}
	
	@Test
	public void testHeroOpenDoor() throws GameEndException {
		Game game = new Game(map2);
		Coordinate coord = new Coordinate(1,8);
		game.getGameMap().removeEntityFromCoord(game.getGameMap().getEntity(coord), coord);
		Key key = new Key(coord);
		game.getGameMap().addEntityToCoord(key, coord);
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
		assertTrue(((Door)game.getGameMap().getEntity(new Coordinate(1, 0))).isOpen());
	}
	
	@Test
	public void testHeroWinsKeep() throws GameEndException {
		Game game = new Game(map2);
		Coordinate coord = new Coordinate(1,8);
		game.getGameMap().removeEntityFromCoord(game.getGameMap().getEntity(coord), coord);
		Key key = new Key(coord);
		game.getGameMap().addEntityToCoord(key, coord);
		game.getGameMap().addWinningCoord(new Coordinate(1, 0));
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
	
	@Test(timeout=1000)
	public void testSomeRandomBehaviour() {
		Game game = new Game(map2);
		Ogre ogre = (Ogre) game.getGameMap().getEntity(new Coordinate(1,5));
		game.getGameMap().removeEntityFromCoord(game.getHero(), game.getHero().getCoordinate());
		boolean o1 = false, o2 = false, o3 = false, o4 = false;
		while (!o1 || !o2 || !o3 || !o4) {
			Coordinate oldC = new Coordinate(ogre.getCoordinate().getX(), ogre.getCoordinate().getY());
			game.moveOgre(ogre);
			if (ogre.getCoordinate().getY() == oldC.getY() && ogre.getCoordinate().getX() == oldC.getX() - 1) {
				o1 = true;
			} else if (ogre.getCoordinate().getY() == oldC.getY() && ogre.getCoordinate().getX() == oldC.getX() + 1) {
				o2 = true;
			} else if (ogre.getCoordinate().getY() == oldC.getY() - 1 && ogre.getCoordinate().getX() == oldC.getX()) {
				o3 = true;
			} else if (ogre.getCoordinate().getY() == oldC.getY() + 1 && ogre.getCoordinate().getX() == oldC.getX()) {
				o4 = true;
			}
		}
	}
	
	char [][] map3 = {{'X','X','X','X','X'},
			{'X',' ',' ',' ','X'},
			{'I',' ',' ',' ','X'},
			{'I',' ',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	@Test
	public void testGuards() {
		Game game = new Game(map3);
		Rookie rookie = new Rookie(new Coordinate(1,1));
		Suspicious suspicious = new Suspicious(new Coordinate(2,1));
		Drunken drunk = new Drunken(new Coordinate(3,1));
		Direction[] walkpath= {Direction.DOWN,Direction.DOWN,Direction.UP,Direction.UP};
		rookie.setWalkPath(walkpath);
		suspicious.setWalkPath(walkpath);
		drunk.setWalkPath(walkpath);
		game.getGameMap().addEntityToCoord(rookie, rookie.getCoordinate());
		game.getGameMap().addEntityToCoord(suspicious, suspicious.getCoordinate());
		game.getGameMap().addEntityToCoord(drunk, drunk.getCoordinate());
		for(int i = 0; i < 40; i++) {
			game.moveGuard(rookie);
			game.moveGuard(drunk);
			game.moveGuard(suspicious);
			game.printMap();
		}
		assertEquals(rookie.getCoordinate(), new Coordinate(1,1));
	}
}
