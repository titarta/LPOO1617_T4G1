package dkeep.logic;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import dkeep.logic.Generic.*;
import dkeep.logic.Generic.Generic.Direction;

public class GameMap implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * HashMap of Coordinate - Entity that stores all the entities in the map (game).
	 */
	private HashMap<Coordinate,HashSet<Entity>> coordToEntityMap;
	/**
	 * Size y of the map.
	 */
	private int y;
	/**
	 * Size x of the map.
	 */
	private int x;
	/**
	 * Reference to hero - makes it more easy to work with it.
	 */
	private Entity hero;
	/**
	 * HashSet that stores all the enemies (ogres and guards).
	 */
	private HashSet<Entity> enemies;
	/**
	 * HashSet of coordinates that store the coordinates which when stepped win the game.
	 */
	private HashSet<Coordinate> winningCoords;
	
	/**
	 * Adds the Entity to the coordinate in the HashMap.
	 * @param ent
	 * @param coord
	 */
	public void addEntityToCoord(Entity ent, Coordinate coord) {
		if (coordToEntityMap.get(coord) == null) {
			HashSet<Entity> aux = new HashSet<Entity>();
			aux.add(ent);
			coordToEntityMap.put(coord, aux);
		} else {
			coordToEntityMap.get(coord).add(ent);
		}
	}
	
	/**
	 * Removes the Entity from the coordinate in the HashMap.
	 * @param ent
	 * @param coord
	 */
	public void removeEntityFromCoord(Entity ent, Coordinate coord) {
		if (!coordToEntityMap.containsKey(coord)) {
			return;
		}
		if (coordToEntityMap.get(coord).size() <= 1) {
			coordToEntityMap.remove(coord);
			return;
		}
		coordToEntityMap.get(coord).remove(ent);
	}
	
	/**
	 * Creates an empty map. Works like a default constructor.
	 * @param x
	 * @param y
	 */
	public GameMap(int x, int y) {
		coordToEntityMap = new HashMap<Coordinate, HashSet<Entity>>();
		winningCoords = new HashSet<Coordinate>();
		enemies = new HashSet<Entity>();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Main constructor - Creates the map using a multidimensional array.
	 * @param map
	 */
	public GameMap(char[][] map) {
		coordToEntityMap = new HashMap<Coordinate, HashSet<Entity>>();
		winningCoords = new HashSet<Coordinate>();
		enemies = new HashSet<Entity>();
		y = map.length;
		x = map[0].length;
		for(int j = 0; j < y; j++) {
			for(int i = 0; i < x; i++) {
				if (map[j][i] != ' ') {
					if (map[j][i] == 'X') {
						Coordinate newCoord = new Coordinate(j,i);
						Wall wall = new Wall(newCoord);
						addEntityToCoord(wall,newCoord);
					} else if (map[j][i] == 'H') {
						Coordinate newCoord = new Coordinate(j,i);
						Hero hero = new Hero(newCoord);
						addEntityToCoord(hero,newCoord);
						this.hero = hero;
					} else if (map[j][i] == 'I') {
						Coordinate newCoord = new Coordinate(j,i);
						Door door = new Door(newCoord);
						addEntityToCoord(door,newCoord);
					} else if (map[j][i] == 'G') {
						Coordinate newCoord = new Coordinate(j,i);
						Guard guard = new Guard(newCoord);
						addEntityToCoord(guard,newCoord);
						enemies.add(guard);
					} else if (map[j][i] == 'k') {
						Coordinate newCoord = new Coordinate(j,i);
						Lever lever = new Lever(newCoord, new HashSet<Door>());
						addEntityToCoord(lever,newCoord);
					} else if (map[j][i] == 'O') {
						Coordinate newCoord = new Coordinate(j,i);
						Ogre ogre = new Ogre(newCoord, true);
						addEntityToCoord(ogre,newCoord);
						addEntityToCoord(ogre.getClub(), ogre.getClub().getCoordinate());
						enemies.add(ogre);
					} else if (map[j][i] == 'w') {
						Coordinate newCoord = new Coordinate(j,i);
						Weapon weapon = new Weapon(newCoord);
						addEntityToCoord(weapon,newCoord);
					}
				}
			}
		}
	}

	/**
	 * Constructor able to deep copy a map. Used to start the game again without erasing created levels.
	 * @param hashMap
	 * @param winCoords
	 * @param hero
	 * @param x
	 * @param y
	 * @throws CloneNotSupportedException
	 */
	public GameMap(HashMap<Coordinate, HashSet<Entity>> hashMap ,HashSet<Coordinate> winCoords, Hero hero, int x, int y) throws CloneNotSupportedException {
		coordToEntityMap = new HashMap<Coordinate, HashSet<Entity>>();
		winningCoords = new HashSet<Coordinate>();
		enemies = new HashSet<Entity>();
		for(Entry<Coordinate, HashSet<Entity>> c : hashMap.entrySet()) {
			HashSet<Entity> newSet = new HashSet<Entity>();
			for (Entity e : c.getValue()) {
				Entity newEntity = (Entity) e.clone();
				if (newEntity.isOgre()) {
					enemies.add(newEntity);
				}
				newSet.add(newEntity);
			}
			coordToEntityMap.put((Coordinate)c.getKey().clone(), newSet);
		}
		this.hero = (Entity) hero.clone();
		this.winningCoords = new HashSet<Coordinate>(winCoords);
		this.x = x;
		this.y = y;
	} 

	/**
	 * Getter for the HashMap
	 * @return coordToEntityMap
	 */
	public HashMap<Coordinate, HashSet<Entity>> getCoordToEntityMap() {
		return coordToEntityMap;
	}

	/**
	 * Removes and adds a certain entity to a certain coordinate (used by the club).
	 * @param ent
	 * @param coord
	 */
	public void setEntityCoord(Entity ent, Coordinate coord) {
		removeEntityFromCoord(ent, ent.getCoordinate());
		addEntityToCoord(ent, coord);
	}
	
	/**
	 * The same as setEntityCoord but using a direction (used by everyone)
	 * @param ent
	 * @param dir
	 */
	public void moveEntity(Entity ent, Direction dir) {
		removeEntityFromCoord(ent,ent.getCoordinate());
		addEntityToCoord(ent,ent.getCoordinate().update(dir));
	}
	
	/**
	 * Function responsible to tell either a entity can or not go to that position.
	 * @param coord
	 * @return either that coordinate has or not a entity that blocks the movement.
	 */
	public boolean coordBlocksMovement(Coordinate coord) {
		if (coordToEntityMap.get(coord) != null){
			return coordToEntityMap.get(coord).iterator().next().blocksMovement();
		}
		return false;
	}
	
	/**
	 * Check if a coordinate has an entity of a determinate class, and returns it.
	 * @param coord
	 * @param c
	 * @return
	 */
	public Entity coordHas(Coordinate coord, Class<? extends Entity> c) {
		if (coordToEntityMap.get(coord) == null){
			return null;
		}
		for (Entity e : coordToEntityMap.get(coord)) {
			if (c.isInstance(e)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Removes a coordinate for the HashMap.
	 * @param coord
	 */
	public void removeCoord(Coordinate coord) {
		coordToEntityMap.remove(coord);
	}

	/**
	 * Checks if the coordinate has a weapon.
	 * @param coord
	 * @return Weapon or null
	 */
	public Entity coordHasWeapon(Coordinate coord) {
		return coordHas(coord, Weapon.class);
	}
	
	/**
	 * Checks if the coordinate has a Key.
	 * @param coord
	 * @return Key or null
	 */
	public Entity coordHasKey(Coordinate coord) {
		return coordHas(coord, Key.class);
	}
	
	/**
	 * Checks if the coordinate has a Lever.
	 * @param coord
	 * @return Lever or null
	 */
	public Entity coordHasLever(Coordinate coord) {
		return coordHas(coord, Lever.class);
	}
	
	/**
	 * Checks if the coordinate has a Door.
	 * @param coord
	 * @return Door or null
	 */
	public Entity coordHasDoor(Coordinate coord) {
		return coordHas(coord, Door.class);
	}
	
	/**
	 * Checks if the coordinate has any entity or not.
	 * @param coord
	 * @return boolean
	 */
	public boolean coordHasSomething(Coordinate coord) {
		return coordToEntityMap.containsKey(coord);
	}
	
	/**
	 * Checks if a coordinate is a winning coordinate.
	 * @param coord
	 * @return boolean
	 */
	public boolean coordIsWinningCoord(Coordinate coord) {
		return winningCoords.contains(coord);
	}
	
	/**
	 * Adds a coordinate to the HashSet of winning coordinates
	 * @param coord
	 */
	public void addWinningCoord(Coordinate coord) {
		if (0 <= coord.getX() && coord.getX() < x && 0 <= coord.getY() && coord.getY() < y) {
			winningCoords.add(coord);
		}
	}
	
	/**
	 * Getter for winningCoords.
	 * @return winningCoords
	 */
	public HashSet<Coordinate> getWinningCoords() {
		return winningCoords;
	}

	/**
	 * Checks collision between 2 entities (only step over).
	 * @param ent1
	 * @param ent2
	 * @return boolean
	 */
	public boolean checkEntityStepOver (Entity ent1, Entity ent2) {
		return ent1.getCoordinate() == ent2.getCoordinate();
	}
	
	/**
	 * Checks collision between 2 entities (adjacency).
	 * @param ent1
	 * @param ent2
	 * @return boolean
	 */
	public boolean checkAdjacency(Entity ent1, Entity ent2) {
		return ent1.getCoordinate().isAdjacent(ent2.getCoordinate());
	}
	
	/**
	 * Getter for x.
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for y.
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns most prioritized element in the coordinate. It is used to know which one is going to appear in the map.
	 * @param coord
	 * @return Entity
	 */
	public Entity getEntity(Coordinate coord) {
		Entity ret = null;
		int i = 100;
		if (coordToEntityMap.get(coord) == null) {
			return null;
		}
		for (Entity e : coordToEntityMap.get(coord)) {
			if (e.getPriority() < i) {
				i = e.getPriority();
				ret = e;
			}
		}
		return ret;
	}

	/**
	 * Setter for hero
	 * @param hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	/**
	 * Getter for hero.
	 * @return hero
	 */
	public Hero getHero() {
		return (Hero)hero;
	}
	
	/**
	 * Getter for enemies.
	 * @return enemies
	 */
	public HashSet<Entity> getEnemies() {
		return enemies;
	}
	
	/**
	 * Adds an Entity to the HashSet enemies.
	 * @param e
	 */
	public void addEnemy(Entity e) {
		enemies.add(e);
	}
	
	
	
	
}
