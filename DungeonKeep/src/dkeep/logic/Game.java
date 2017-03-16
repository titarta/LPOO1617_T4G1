package dkeep.logic;

import java.util.HashSet;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Game {
	
	private GameMap gameMap;
	private Hero hero;
	private HashSet<Entity> enemies;
	private HashSet<Coordinate> winningCoords;
	
	
	public Game(GameMap gameMap) {
		this.gameMap = gameMap;
		hero = gameMap.getHero();
		enemies = gameMap.getEnemies();
		winningCoords = gameMap.getWinningCoords();
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
	
	public boolean moveEntity(Entity ent, Direction dir) {
		if (ent instanceof Hero) {
			return false;
		}
		Coordinate coord = ent.getCoordinate();
		coord.update(dir);
		if (gameMap.coordBlocksMovement(coord)) {
			return false;
		} else {
			ent.move(dir);
			gameMap.moveEntity(ent, dir);
			return true;
		}
	}
	
	public boolean moveHero(Direction dir) throws GameEndException {
		Coordinate coord = hero.getCoordinate();
		coord.update(dir);
		if (gameMap.coordBlocksMovement(coord)) {
			if (gameMap.coordHasDoor(coord) != null) {
				((Door)(gameMap.coordHasDoor(coord))).toggle();
			}
			return false;
		} else {
			gameMap.moveEntity(hero, dir);
			hero.move(dir);
			if (gameMap.coordIsWinningCoord(coord)) {
				throw new GameEndException(true);
			}
			if (gameMap.coordHasKey(coord) != null) { //se encontrar uma chave
				hero.catchKey();
				gameMap.removeEntityFromCoord(gameMap.coordHasKey(coord), coord);
			}
			if (gameMap.coordHasLever(coord) != null) { //se encontrar uma alavanca
				for (Door d : ((Lever)(gameMap.coordHasLever(coord))).getDoors()) {
					d.toggle();
				}
			}
			for (Entity e : enemies) {
				if (gameMap.checkAdjacency(hero, e)) {
					throw new GameEndException(false);
				}
			}
			return true;
		}
		
	}
	
	public Entity getHero() {
		return hero;
	}
}
