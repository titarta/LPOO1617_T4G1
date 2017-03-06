package dkeep.logic;

import java.util.HashMap;
import java.util.List;
import dkeep.logic.Generic.*;
import dkeep.logic.Generic.Generic.Direction;

public class GameMap {
	private Hero hero;
	private HashMap<Coordinate, Entity> coordToEntityMap;
	private int y;
	private int x;
	
	
	public GameMap(char[][] map) {
		coordToEntityMap = new HashMap<Coordinate, Entity>();
		y = map.length;
		x = map[0].length;
		for(int j = 0; j < y; j++) {
			for(int i = 0; i < x; i++) {
				if (map[j][i] != ' ') {
					if (map[j][i] == 'X') {
						Coordinate newCoord = new Coordinate(j,i);
						Wall wall = new Wall(newCoord);
						coordToEntityMap.put(newCoord, wall);
					} else if (map[j][i] == 'H') {
						Coordinate newCoord = new Coordinate(j,i);
						hero = new Hero(newCoord);
					} else if (map[j][i] == 'I') {
						Coordinate newCoord = new Coordinate(j,i);
						Door door = new Door(newCoord);
						coordToEntityMap.put(newCoord, door);
					} else if (map[j][i] == 'G') {
						Coordinate newCoord = new Coordinate(j,i);
						Guard guard = new Guard(newCoord);
						coordToEntityMap.put(newCoord, guard);
					}
				}
			}
		}
	}
	
	public boolean moveEntity(Entity ent, Direction direction) {
		if (ent.equals(hero)) {
			return false;
		}
		Coordinate oldCoord = ent.getCoordinate();
		Coordinate newCoord = oldCoord;
		newCoord.update(direction);
		if (coordToEntityMap.get(newCoord).blocksMovement()) {
			return false;
		} else {
			ent.move(direction);
			coordToEntityMap.put(newCoord, ent);
			coordToEntityMap.remove(oldCoord);
			return true;
		}
	}
	
	public boolean moveHero(Direction direction) {
		hero.move(direction);
		return true;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Entity getEntity(Coordinate coord) {
		return coordToEntityMap.get(coord);
	}
}
