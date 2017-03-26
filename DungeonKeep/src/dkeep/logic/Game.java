package dkeep.logic;

import java.util.HashSet;
import java.util.Random;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;
/**
 * class responsible for managing all the events in one level of the game.
 * @author Tiago
 *
 */
public class Game implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Stores all entities position.
	 */
	private GameMap gameMap;
	/**
	 * Reference to the hero, easier to move him.
	 */
	private Hero hero;
	
	/**
	 * Constructor of class Game - this one is used for the levelEditor.
	 * @param map
	 */
	public Game(GameMap map) {
		gameMap = map;
		hero = gameMap.getHero();
	}
	
	/**
	 * Main constructor of class Game, it creates a game using a multidimensional array (Might need some additions in case of overlaping enemies).
	 * @param map
	 */
	public Game(char[][] map) {
		this.gameMap = new GameMap(map);
		hero = gameMap.getHero();
	}
	
	/**
	 * Creates a Game with only walls in the sides (used for levelEditor).
	 * @param x
	 * @param y
	 */
	public Game(int x, int y) {
		gameMap = new GameMap(x, y);
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if (i == 0 || i == y-1 || j == 0 || j == x-1) {
					gameMap.addEntityToCoord(new Wall(new Coordinate(i,j)), new Coordinate(i,j));
				}
			}
		}
		hero = null;
	}

	/**
	 * Setter for the variable hero.
	 * @param hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	/**
	 * Used by command line interface - it prints the Game in the console.
	 */
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
	
	/**
	 * Getter that uses a gameMap getter - its is used in some cases, so it is avoided the get of the map.
	 * It returns all the enemies in the game (ogres and guards);
	 * @return HashSet<Entity>
	 */
	public HashSet<Entity> getEnemies() {
		return gameMap.getEnemies();
	}
	
	/**
	 * Function that moves the Ogre randomly and also its club.
	 * Calls placeClub.
	 * @param ogre
	 * @return boolean
	 */
	public boolean moveOgre(Ogre ogre) {
		if (ogre.isStunned()) {
			ogre.updateStunCounter();
			return false;
		}
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
	
	/**
	 * Used by moveOgre, moves the club randomly by using ogre's coordinate.
	 * @param ogre
	 */
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
	
	/**
	 * Moves a guard using it's walkpath. Also updates the guard position in the walkpath.
	 * Gives the guard the chance of deploying their events (sleep, and go backwards for example). Walkpath is always conveniently updated.
	 * @param ent
	 * @return boolean
	 */
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
	
	/**
	 * Moves the hero. Also checks all collisions between the hero and the other entities.
	 * If the hero loses or wins the game in the movement, an exception is thrown with a boolean of the game ending (true if win, false if loss).
	 * @param dir
	 * @return boolean
	 * @throws GameEndException
	 */
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
			if (e instanceof Ogre) {
				checkHeroColisionWithOgre((Ogre)e);
				continue;
			}
			if (!gameMap.checkAdjacency(hero, e)) {
				continue;
			}
			if (e instanceof Drunken) {
				if (((Drunken)e).isSleeping()) {
					continue;
				}
			}
			throw new GameEndException(false);
		}
		return true;
	}
	
	/**
	 * Checks if the hero colides with ogre (it is a complement of moveHero function).
	 * @param ogre
	 * @throws GameEndException
	 */
	public void checkHeroColisionWithOgre (Ogre ogre) throws GameEndException {
		if (gameMap.checkAdjacency(hero, ogre.getClub())) {
			throw new GameEndException(false);
		}
		if (gameMap.checkAdjacency(hero, ogre) && !hero.isArmed()) {
			throw new GameEndException(false);
		}
		if (gameMap.checkAdjacency(hero, ogre) && hero.isArmed()) {
			ogre.getStunned();
		}
	}
	
	/**
	 * Adds the entity to the game.
	 * @param ent
	 * @return boolean
	 */
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
	
	/**
	 * Getter for hero.
	 * @return hero
	 */
	public Entity getHero() {
		return hero;
	}
	
	/**
	 * Getter for GameMap.
	 * @return gameMap
	 */
	public GameMap getGameMap() {
		return gameMap;
	}

	/**
	 * Function responsible for advance the game, it moves both the enemies both the hero.
	 * It throws GameEndException in case of ending the Game
	 * @param dir
	 * @throws GameEndException
	 */
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

	/**
	 * Adds an array of coordinates to the list of winning coordinates (coordinates which, when stepped, wins the game).
	 * @param coords
	 */
	public void addWinningCoords (Coordinate[] coords) {
		for (Coordinate c : coords) {
			gameMap.addWinningCoord(c);
		}
	}
	
	/**
	 * toString function.
	 */
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < gameMap.getY(); i++) {
			for(int j = 0; j < gameMap.getX(); j++) {
				Coordinate coord = new Coordinate(i, j);
				if (gameMap.getEntity(coord) == null) {
					ret += " ";
				} else {
					ret += gameMap.getEntity(coord).getEntityChar();
				}
			}
		}
		
		return ret;
	}
	
	
	
	
}
