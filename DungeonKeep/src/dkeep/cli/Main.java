package dkeep.cli;

import java.sql.ClientInfoStatus;
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
		char map[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		GameMap gameMap1 = new GameMap(map);
		Game game1 = new Game(gameMap1);
		gameMap1.removeEntityFromCoord(gameMap1.coordHasKey(new Coordinate(8,1)), new Coordinate(8,1));
		Key key = new Key(new Coordinate(8,1));
		gameMap1.addEntityToCoord(key, key.getCoordinate());
		gameMap1.addWinningCoord(new Coordinate(0, 1));
		game1.printMap();
		
		while(true) {
			Direction d = Direction.DOWN;
			game1.moveOgre(game1.getEnemies().iterator().next());
			try {
				game1.moveHero(d);
			} catch (GameEndException e1) {
				if (e1.getResult()) {
					System.out.print("You Won\n");
					game1.printMap();
					return;
				} else {
					System.out.print("You Lost\n");
					game1.printMap();
					return;
				}
			}
			game1.printMap();
		}
		
	}

}
