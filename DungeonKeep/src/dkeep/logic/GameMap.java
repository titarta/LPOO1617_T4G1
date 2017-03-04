package dkeep.logic;

import java.util.HashMap;
import dkeep.logic.Generic.*;

public class GameMap {
	private HashMap<Coordinate, Entity[]> coordToEntityMap;
	private HashMap<Entity, Coordinate> entityToCoordMap;
	private int y;
	private int x;
	
	GameMap(char[][] map) {
		y = map.length;
		x = map[0].length;
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if (map[i][j] != ' ') {
					if (map[i][j] == 'X') {
						Coordinate newCoord = new Coordinate(i,j);
						Entity[] newWall = {new Wall()};
						coordToEntityMap.put(newCoord,newWall);
						entityToCoordMap.put(newWall[0], newCoord);
					} else if (map[i][j] == 'H') {
						Coordinate newCoord = new Coordinate(i,j);
						Entity[] newHero = {new Hero()};
						coordToEntityMap.put(newCoord,newHero);
						entityToCoordMap.put(newHero[0], newCoord);
					} else if (map[i][j] == 'I') {
						Coordinate newCoord = new Coordinate(i,j);
						Entity[] newDoor = {new Door()};
						coordToEntityMap.put(newCoord,newDoor);
						entityToCoordMap.put(newDoor[0], newCoord);
					} else if (map[i][j] == 'G') {
						Coordinate newCoord = new Coordinate(i,j);
						Entity[] newGuard = {new Guard()};
						coordToEntityMap.put(newCoord,newGuard);
						entityToCoordMap.put(newGuard[0], newCoord);
					} else if (map[i][j] == 'X') {//por mudar ainda
						Coordinate newCoord = new Coordinate(i,j);
						Entity[] newWall = {new Wall()};
						coordToEntityMap.put(newCoord,newWall);
						entityToCoordMap.put(newWall[0], newCoord);
					} else if (map[i][j] == 'X') {
						Coordinate newCoord = new Coordinate(i,j);
						Entity[] newWall = {new Wall()};
						coordToEntityMap.put(newCoord,newWall);
						entityToCoordMap.put(newWall[0], newCoord);
					}
				}
			}
		}
	}
	
}
