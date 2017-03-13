package dkeep.logic;

import java.util.HashMap;
import java.util.List;
import dkeep.logic.Generic.*;
import dkeep.logic.Generic.Generic.Direction;

public class GameMap {
	private HashMap<Coordinate, Entity> coordToEntityMap;
	private int y;
	private int x;
	private Entity hero;
	
	
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
						Hero hero = new Hero(newCoord);
						coordToEntityMap.put(newCoord, hero);
						this.hero = hero;
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
		if (ent instanceof Static) {
			return false;
		}
		if (ent instanceof Hero) {
			Coordinate oldCoord = ent.getCoordinate();
			Coordinate newCoord = oldCoord;
			newCoord.update(direction);
			if (coordToEntityMap.get(newCoord).blocksMovement()) {
				if (coordToEntityMap.get(newCoord) instanceof Door) {
					if (((Hero)ent).hasKey()) {
						((Door)coordToEntityMap.get(newCoord)).toggle();
						((Hero)ent).releaseKey();
					}
				}
				return false;
			} else {
				if (coordToEntityMap.get(newCoord) instanceof Key) {
					if (((Hero)ent).getHolster() != null) {
						coordToEntityMap.put(oldCoord, ((Hero)ent).getHolster());
					} else {
						coordToEntityMap.remove(oldCoord);
					}
					coordToEntityMap.put(newCoord, ent);
					ent.move(direction);
					((Hero)ent).catchKey();
					
				}
				if (coordToEntityMap.get(newCoord) instanceof Door) {
					if (((Hero)ent).getHolster() != null) {
						coordToEntityMap.put(oldCoord, ((Hero)ent).getHolster());
					} else {
						coordToEntityMap.remove(oldCoord);
					}
					((Hero)ent).setHolster(coordToEntityMap.get(newCoord));
					coordToEntityMap.put(newCoord, ent);
					ent.move(direction);
				}
				if (coordToEntityMap.get(newCoord) instanceof Lever) {
					for (Door d : ((Lever)coordToEntityMap.get(newCoord)).getDoors()) {
						d.toggle();
					}
					if (((Hero)ent).getHolster() != null) {
						coordToEntityMap.put(oldCoord, ((Hero)ent).getHolster());
					} else {
						coordToEntityMap.remove(oldCoord);
					}
					((Hero)ent).setHolster(coordToEntityMap.get(newCoord));
					coordToEntityMap.put(newCoord, ent);
					ent.move(direction);
				}
				
			}
			return true;
		} else {
			Coordinate oldCoord = ent.getCoordinate();
			Coordinate newCoord = oldCoord;
			newCoord.update(direction);
			if (coordToEntityMap.get(newCoord).blocksMovement()) {
				return false;
			} else {
				if (((NonStatic)ent).getHolster() != null) {
					coordToEntityMap.put(oldCoord, ((NonStatic)ent).getHolster());
				} else {
					coordToEntityMap.remove(oldCoord);
				}
				if ((coordToEntityMap.get(newCoord) instanceof Static)) {
					((NonStatic)ent).setHolster(coordToEntityMap.get(newCoord));
				}
				coordToEntityMap.put(newCoord, ent);
				ent.move(direction);
			}
			return true;
		}
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


	public Entity getHero() {
		return hero;
	}

	
	
}
