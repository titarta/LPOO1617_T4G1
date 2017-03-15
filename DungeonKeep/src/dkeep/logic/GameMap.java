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
					}
				}
			}
		}
	}
	
	
//	public boolean moveEntity(Entity ent, Direction direction) {
//		if (ent instanceof Static) {
//			return false;
//		}
//		if (ent instanceof Hero) {
//			Coordinate oldCoord = ent.getCoordinate();
//			Coordinate newCoord = oldCoord;
//			newCoord.update(direction);
//			if (coordToEntityMap.get(newCoord).blocksMovement()) {
//				if (coordToEntityMap.get(newCoord) instanceof Door) {
//					if (((Hero)ent).hasKey()) {
//						((Door)coordToEntityMap.get(newCoord)).toggle();
//						((Hero)ent).releaseKey();
//					}
//				}
//				return false;
//			} else {
//				if (coordToEntityMap.get(newCoord) instanceof Key) {
//					if (((Hero)ent).getHolster() != null) {
//						coordToEntityMap.put(oldCoord, ((Hero)ent).getHolster());
//					} else {
//						coordToEntityMap.remove(oldCoord);
//					}
//					coordToEntityMap.put(newCoord, ent);
//					ent.move(direction);
//					((Hero)ent).catchKey();
//					
//				}
//				if (coordToEntityMap.get(newCoord) instanceof Door) {
//					if (((Hero)ent).getHolster() != null) {
//						coordToEntityMap.put(oldCoord, ((Hero)ent).getHolster());
//					} else {
//						coordToEntityMap.remove(oldCoord);
//					}
//					((Hero)ent).setHolster(coordToEntityMap.get(newCoord));
//					coordToEntityMap.put(newCoord, ent);
//					ent.move(direction);
//				}
//				if (coordToEntityMap.get(newCoord) instanceof Lever) {
//					for (Door d : ((Lever)coordToEntityMap.get(newCoord)).getDoors()) {
//						d.toggle();
//					}
//					if (((Hero)ent).getHolster() != null) {
//						coordToEntityMap.put(oldCoord, ((Hero)ent).getHolster());
//					} else {
//						coordToEntityMap.remove(oldCoord);
//					}
//					((Hero)ent).setHolster(coordToEntityMap.get(newCoord));
//					coordToEntityMap.put(newCoord, ent);
//					ent.move(direction);
//				}
//				
//			}
//			return true;
//		} else {
//			Coordinate oldCoord = ent.getCoordinate();
//			Coordinate newCoord = oldCoord;
//			newCoord.update(direction);
//			if (coordToEntityMap.get(newCoord).blocksMovement()) {
//				return false;
//			} else {
//				if (((NonStatic)ent).getHolster() != null) {
//					coordToEntityMap.put(oldCoord, ((NonStatic)ent).getHolster());
//				} else {
//					coordToEntityMap.remove(oldCoord);
//				}
//				if ((coordToEntityMap.get(newCoord) instanceof Static)) {
//					((NonStatic)ent).setHolster(coordToEntityMap.get(newCoord));
//				}
//				coordToEntityMap.put(newCoord, ent);
//				ent.move(direction);
//			}
//			return true;
//		}
//	}
	
	public boolean coordBlocksMovement(Coordinate coord) {
		return coordToEntityMap.get(coord).iterator().next().blocksMovement();
	}

	public boolean coordHasEntity(Coordinate coord, Entity ent) {
		return coordToEntityMap.get(coord).contains(ent);
	}
	
	public Entity coordHasKey(Coordinate coord) {
		for(Entity e : coordToEntityMap.get(coord)) {
			if (e instanceof Key) {
				return e;
			}
		}
		return null;
	}
	
	public Entity coordHasLever(Coordinate coord) {
		for(Entity e : coordToEntityMap.get(coord)) {
			if (e instanceof Lever) {
				return e;
			}
		}
		return null;
	}
	
	public boolean checkEntityStepOver (Entity ent1, Entity ent2) {
		return ent1.getCoordinate() == ent2.getCoordinate();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Entity getEntity(Coordinate coord) {
		return coordToEntityMap.get(coord).iterator().next();
	}


	public Entity getHero() {
		return hero;
	}

	
	
}
