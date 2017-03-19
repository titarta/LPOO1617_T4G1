package dkeep.logic;

import java.util.HashSet;
import java.util.Random;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Game {
	
	private GameMap gameMap;
	private Hero hero;
	
	
	public Game(char[][] map) {
		this.gameMap = new GameMap(map);
		hero = gameMap.getHero();
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
		return gameMap.getEnemies();
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
				gameMap.removeEntityFromCoord(ent,ent.getCoordinate());
				ent.move(d);
				gameMap.addEntityToCoord(ent,nextCoord);
				if (gameMap.coordHasKey(ent.getCoordinate()) != null) {
					((Ogre)ent).goOverKey(true);
				}
				if (((Ogre)ent).isArmed()) {
					while (moveClub(((Ogre)ent), Direction.values()[rnd.nextInt(4)])) {}
				}
				return true;
			}
		}
	}
	
	public boolean moveClub(Ogre ogre, Direction dir) {
		Coordinate nextCoord = new Coordinate(ogre.getCoordinate().getX(), ogre.getCoordinate().getY());
		nextCoord.update(dir);
		if (!gameMap.coordBlocksMovement(nextCoord)) {
			gameMap.setEntityCoord(ogre.getClub(), nextCoord);
			ogre.getClub().use(nextCoord);
			if (gameMap.coordHasKey(nextCoord) != null) {
				ogre.getClub().goesToKey();
			}
			return false;
		} else {
			return true;
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
			((Guard)ent).updateWalkPathPos();
			return false;
		}
	}
	
	public boolean moveHero(Direction dir) throws GameEndException {
		Coordinate coord = new Coordinate(hero.getCoordinate().getX(), hero.getCoordinate().getY());
		coord.update(dir);
		if (gameMap.coordBlocksMovement(coord)) {
			if (gameMap.coordHasDoor(coord) != null && hero.hasKey()) {
				((Door)(gameMap.coordHasDoor(coord))).toggle();
			}
		} else {
			gameMap.moveEntity(hero, dir); 
		}
		if (gameMap.coordIsWinningCoord(hero.getCoordinate())) {
			throw new GameEndException(true);
		}
		if (gameMap.coordHasKey(hero.getCoordinate()) != null) { //se encontrar uma chave
			hero.catchKey();
			gameMap.removeEntityFromCoord(gameMap.coordHasKey(hero.getCoordinate()), hero.getCoordinate());
		}
		if (gameMap.coordHasLever(hero.getCoordinate()) != null) { //se encontrar uma alavanca
			for (Door d : ((Lever)(gameMap.coordHasLever(coord))).getDoors()) {
				d.toggle();
			}
		}
		for (Entity e : gameMap.getEnemies()) {
			if (gameMap.checkAdjacency(hero, e)) {
				if (e instanceof Drunken) {
					if (((Drunken)e).isSleeping()) {
						continue;
					}
				}
				throw new GameEndException(false);
			}
		}
		return true;
	}
	
	public boolean addEntity(Entity ent) {
		if (gameMap.coordBlocksMovement(ent.getCoordinate())) {
			return false;
		}
		if (ent instanceof Guard || ent instanceof Ogre) {
			gameMap.addEnemy(ent);
		}
		gameMap.addEntityToCoord(ent, ent.getCoordinate());
		return true;
	}
	
	public Entity getHero() {
		return hero;
	}
	
	
	public GameMap getGameMap() {
		return gameMap;
	}

	public void updateGame(Direction dir) throws GameEndException {
		for(Entity e : gameMap.getEnemies()) {
			if (e instanceof Guard) {
				moveGuard(e);
			} else if (e instanceof Ogre) {
				moveOgre(e);
			}
		}
		moveHero(dir);
	}

	public void addWinningCoords (Coordinate[] coords) {
		for (Coordinate c : coords) {
			gameMap.addWinningCoord(c);
		}
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < gameMap.getX(); i++) {
			for(int j = 0; j < gameMap.getY(); j++) {
				Coordinate coord = new Coordinate(j, i);
				if (gameMap.getEntity(coord) == null) {
					ret += "  ";
				} else {
					ret += gameMap.getEntity(coord).getEntityChar();
					ret += " ";
				}
			}
			ret += "\n";
		}
		
		return ret;
	}
	
	
	
	
}
