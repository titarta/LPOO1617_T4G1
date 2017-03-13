package dkeep.logic;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Game {
	GameMap gameMap;
	
	
	public Game(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	public void printMap() {
		for(int i = 0; i < gameMap.getX(); i++) {
			for(int j = 0; j < gameMap.getY(); j++) {
				Coordinate coord = new Coordinate(i, j);
				if (gameMap.getEntity(coord) == null) {
					System.out.print(' ');
				} else {
					System.out.print(gameMap.getEntity(coord).getEntityChar());
				}
			}
			System.out.println();
		}
	}
	
	public void moveEntity(Entity ent, Direction dir) {
		gameMap.moveEntity(ent, dir);
	}
	
	public Entity getHero() {
		return gameMap.getHero();
	}
}
