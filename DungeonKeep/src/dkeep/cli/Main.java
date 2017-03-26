package dkeep.cli;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import dkeep.logic.*;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic;
import dkeep.logic.Generic.Generic.Direction;

public class Main {
	
	public static Direction readCommand() {
		while (true) {
			Scanner reader = new Scanner(System.in);
			String input = reader.next();
			input = input.toLowerCase();
			if (input.equals("up") || input.equals("w")) {
				return Direction.UP;
			} else if (input.equals("down") || input.equals("s")) {
				return Direction.DOWN;
			} else if (input.equals("left") || input.equals("a")) {
				return Direction.LEFT;
			} else if (input.equals("right") || input.equals("d")) {
				return Direction.RIGHT;
			}
			reader.close();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Game> games = new ArrayList<Game>();
		int gameNumber = 0;
		
		char map1[][] = {{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
				};
		
		
		Game level1 = new Game(map1);
		HashSet<Door> doors1 = new HashSet<Door>();
		Door door1 = new Door(new Coordinate(5,0));
		Door door2 = new Door(new Coordinate(6,0));
		doors1.add(door1);
		doors1.add(door2);
		Lever lever1 = new Lever(new Coordinate(8, 7), doors1);
		level1.addEntity(door1);
		level1.addEntity(door2);
		level1.addEntity(lever1);
		Guard guard1 = new Rookie(new Coordinate(1, 8));
		Direction guardMovement[] = { Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN,
				Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,
				Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
				Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP };
		guard1.setWalkPath(guardMovement);
		level1.addEntity(guard1);
		Coordinate[] winningCoords1 = {new Coordinate(5,0),new Coordinate(6,0)};
		level1.addWinningCoords(winningCoords1);
		games.add(level1);
		
		char map2[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		Game level2 = new Game(map2);
		Key key1 = new Key(new Coordinate(1, 8));
		level2.addEntity(key1);
		for (int i = 0; i < 2; i++) {
			Ogre ogre = new Ogre(new Coordinate(1, 4), true);
			level2.addEntity(ogre);
			level2.addEntity(ogre.getClub());
		}
		Coordinate[] winningCoords2 = {new Coordinate(1,0)};
		level2.addWinningCoords(winningCoords2);
		level2.addEntity(new Weapon(new Coordinate(8,2)));
		games.add(level2);
		
		games.get(gameNumber).printMap();
		
		while(true) {
			Direction d = readCommand();
			for (Entity e : games.get(gameNumber).getEnemies()) {
				if (e instanceof Ogre) {
					games.get(gameNumber).moveOgre((Ogre)e);
				} else {
					games.get(gameNumber).moveGuard(e);
				}
			}
			try {
				games.get(gameNumber).moveHero(d);
			} catch (GameEndException e1) {
				if (e1.getResult()) {
					games.get(gameNumber).printMap();
					if (gameNumber == games.size() - 1) {
						System.out.print("You Won\n");
						return;
					} else {
						gameNumber++;
					}
				} else {
					System.out.print("You Lost\n");
					games.get(gameNumber).printMap();
					return;
				}
			}
			games.get(gameNumber).printMap();
		}
		
	}

}
