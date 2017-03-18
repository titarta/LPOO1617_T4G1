package dkeep.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import dkeep.logic.Generic.*;
import dkeep.logic.Generic.Generic.Direction;

public class GameMap {
	private HashMap<Coordinate,HashSet<Entity>> coordToEntityMap;
	private int y;
	private int x;
	private Entity hero;
	private HashSet<Entity> enemies;
	private HashSet<Coordinate> winningCoords;
	
	public void addEntityToCoord(Entity ent, Coordinate coord) {
		if (coordToEntityMap.get(coord) == null) {
			HashSet<Entity> aux = new HashSet<Entity>();
			aux.add(ent);
			coordToEntityMap.put(coord, aux);
		} else {
			coordToEntityMap.get(coord).add(ent);
		}
	}
	
	public void removeEntityFromCoord(Entity ent, Coordinate coord) {
		if (coordToEntityMap.get(coord).size() == 1) {
			coordToEntityMap.remove(coord);
		} else {
			coordToEntityMap.get(coord).remove(ent);
		}
	}
	
	
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
						Coordinate newCoord = new Coordinate(i,j);
						Wall wall = new Wall(newCoord);
						addEntityToCoord(wall,newCoord);
					} else if (map[j][i] == 'H') {
						Coordinate newCoord = new Coordinate(i,j);
						Hero hero = new Hero(newCoord);
						addEntityToCoord(hero,newCoord);
						this.hero = hero;
					} else if (map[j][i] == 'I') {
						Coordinate newCoord = new Coordinate(i,j);
						Door door = new Door(newCoord);
						addEntityToCoord(door,newCoord);
					} else if (map[j][i] == 'G') {
						Coordinate newCoord = new Coordinate(i,j);
						Guard guard = new Guard(newCoord);
						addEntityToCoord(guard,newCoord);
						enemies.add(guard);
					} else if (map[j][i] == 'k') {
						Coordinate newCoord = new Coordinate(i,j);
						Lever lever = new Lever(newCoord, new HashSet<Door>());
						addEntityToCoord(lever,newCoord);
					} else if (map[j][i] == 'O') {
						Coordinate newCoord = new Coordinate(i,j);
						Ogre ogre = new Ogre(newCoord, true);
						addEntityToCoord(ogre,newCoord);
						addEntityToCoord(ogre.getClub(), ogre.getClub().getCoordinate());
						enemies.add(ogre);
					} else if (map[j][i] == 'w') {
						Coordinate newCoord = new Coordinate(i,j);
						Weapon weapon = new Weapon(newCoord);
						addEntityToCoord(weapon,newCoord);
					}
				}
			}
		}
	}

	
	
	public void setEntityCoord(Entity ent, Coordinate coord) {
		removeEntityFromCoord(ent, ent.getCoordinate());
		addEntityToCoord(ent, coord);
	}
	
	public void moveEntity(Entity ent, Direction dir) {
		removeEntityFromCoord(ent,ent.getCoordinate());
		addEntityToCoord(ent,ent.getCoordinate().update(dir));
	}
	
	
	public boolean coordBlocksMovement(Coordinate coord) {
		if (coordToEntityMap.get(coord) != null){
			return coordToEntityMap.get(coord).iterator().next().blocksMovement();
		}
		return false;
	}

	public boolean coordHasEntity(Coordinate coord, Entity ent) {
		if (coordToEntityMap.get(coord) != null){
			return coordToEntityMap.get(coord).contains(ent);
		}
		return false;
	}
	
	public Entity coordHasKey(Coordinate coord) {
		if (coordToEntityMap.get(coord) != null ){
			for(Entity e : coordToEntityMap.get(coord)) {
				if (e instanceof Key) {
					return e;
				}
			}
		}
		return null;
	}
	
	public Entity coordHasLever(Coordinate coord) {
		if (coordToEntityMap.get(coord) != null ){
			for(Entity e : coordToEntityMap.get(coord)) {
				if (e instanceof Lever) {
					return e;
				}
			}
		}
		return null;
	}
	
	public Entity coordHasDoor(Coordinate coord) {
		if (coordToEntityMap.get(coord) != null ){
			for(Entity e : coordToEntityMap.get(coord)) {
				if (e instanceof Door) {
					return e;
				}
			}
		}
		return null;
	}
	
	public boolean coordIsWinningCoord(Coordinate coord) {
		for (Coordinate c : winningCoords) {
			if (c.equals(coord)) {
				return true;
			}
		}
		return false;
	}
	
	public void addWinningCoord(Coordinate coord) {
		if (0 <= coord.getX() && coord.getX() < x && 0 <= coord.getY() && coord.getY() < y) {
			winningCoords.add(coord);
		}
	}
	
	public HashSet<Coordinate> getWinningCoords() {
		return winningCoords;
	}

	public boolean checkEntityStepOver (Entity ent1, Entity ent2) {
		return ent1.getCoordinate() == ent2.getCoordinate();
	}
	
	public boolean checkAdjacency(Entity ent1, Entity ent2) {
		return ent1.getCoordinate().isAdjacent(ent2.getCoordinate());
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Entity getEntity(Coordinate coord) {
		if (coordToEntityMap.get(coord) == null) {
			return null;
		} else {
			return coordToEntityMap.get(coord).iterator().next();
		}
	}


	public Hero getHero() {
		return (Hero)hero;
	}
	
	public HashSet<Entity> getEnemies() {
		return enemies;
	}

	
	
}
