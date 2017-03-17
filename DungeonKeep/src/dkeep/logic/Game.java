package dkeep.logic;

import java.util.HashSet;
import java.util.Random;

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
				Coordinate coord = new Coordinate(j, i);
				if (gameMap.getEntity(coord) == null) {
					System.out.print(' ');
				} else {
					System.out.print(gameMap.getEntity(coord).getEntityChar());
				}
			}
			System.out.println();
		}
	}
	
	public HashSet<Entity> getEnemies() {
		return enemies;
	}
	
	public boolean moveEntity(Entity ent, Direction dir) {
		if (ent instanceof Hero || ent instanceof Guard) {
			return false;
		}
		Coordinate nextCoord = ent.getCoordinate();
		nextCoord.update(dir);
		if (gameMap.coordBlocksMovement(nextCoord)) {
			return false;
		} else {
			gameMap.moveEntity(ent, dir);
			return true;
		}
	}
	
	public boolean moveOgre(Entity ent) {
		Random rnd = new Random();
		
		while (true) {
			Coordinate nextCoord = new Coordinate(ent.getCoordinate().getX(), ent.getCoordinate().getY());
			Direction d = Direction.values()[rnd.nextInt(4)];
			nextCoord.update(d);
			if (gameMap.coordBlocksMovement(nextCoord)) {
				continue;
			} else {
				((Ogre)ent).setOverKey(false);
				gameMap.moveEntity(ent, d); 
				if (gameMap.coordHasKey(ent.getCoordinate()) != null) {
					((Ogre)ent).setOverKey(true);
				}
				return true;
			}
		}
	}
	
	public boolean moveGuard(Entity ent) {
		if (!(ent instanceof Guard)) {
			return false;
		}
		Coordinate nextCoord = new Coordinate(ent.getCoordinate().getX(), ent.getCoordinate().getY());
		nextCoord.update(((Guard)ent).getGuardDirection());
		if (!gameMap.coordBlocksMovement(nextCoord)) {
			gameMap.removeEntityFromCoord(ent,ent.getCoordinate());
			gameMap.addEntityToCoord(ent, ((Guard)ent).updateGuard());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveHero(Direction dir) throws GameEndException {
		Coordinate coord = new Coordinate(hero.getCoordinate().getX(), hero.getCoordinate().getY());
		coord.update(dir);
		if (gameMap.coordBlocksMovement(coord)) {
			if (gameMap.coordHasDoor(coord) != null) {
				((Door)(gameMap.coordHasDoor(coord))).toggle();
			}
			return false;
		} else {
			gameMap.moveEntity(hero, dir);
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
