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
	
	public boolean moveOgre(Ogre ogre) {
		Random rnd = new Random();

		Coordinate nextCoord = new Coordinate(ogre.getCoordinate().getX(), ogre.getCoordinate().getY());
		Direction d;
		do {
			nextCoord.set(ogre.getCoordinate().getX(), ogre.getCoordinate().getY());
			d = Direction.values()[rnd.nextInt(4)];
		} while (gameMap.coordBlocksMovement(nextCoord.update(d)));

		gameMap.removeEntityFromCoord(ogre, ogre.getCoordinate());
		ogre.move(d);
		if (gameMap.coordHasKey(ogre.getCoordinate()) != null) {
			((Ogre) ogre).goOverKey(true);
		}
		if (ogre.isArmed()) {
			placeClub(ogre);
		}
		gameMap.addEntityToCoord(ogre, nextCoord);
		return true;
	}
	
	public void placeClub(Ogre ogre) {
		gameMap.removeEntityFromCoord(ogre.getClub(),ogre.getClub().getCoordinate());
		Coordinate nextCoord = new Coordinate(ogre.getCoordinate().getX(), ogre.getCoordinate().getY());
		Random rnd = new Random();
		Direction d;
		do {
			nextCoord.set(ogre.getCoordinate().getX(), ogre.getCoordinate().getY());
			d = Direction.values()[rnd.nextInt(4)];
		} while (gameMap.coordBlocksMovement(nextCoord.update(d)));
		ogre.getClub().use(nextCoord);
		gameMap.addEntityToCoord(ogre.getClub(), nextCoord);
		if (gameMap.coordHasKey(nextCoord) != null) {
				ogre.getClub().goesToKey();
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
				hero.releaseKey();
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
			for (Door d : ((Lever)(gameMap.coordHasLever(hero.getCoordinate()))).getDoors()) {
				d.toggle();
			}
		}
		if (gameMap.coordHasWeapon(hero.getCoordinate()) != null) {
			gameMap.removeEntityFromCoord(gameMap.coordHasWeapon(hero.getCoordinate()), hero.getCoordinate());
			hero.catchWeapon();
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
				moveOgre((Ogre)e);
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
