package dkeep.test;

import static org.junit.Assert.*;

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
}
