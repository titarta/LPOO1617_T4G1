package dkeep.cli;

import java.sql.ClientInfoStatus;
import java.util.Scanner;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.Hero;
import dkeep.logic.Generic.Generic;
import dkeep.logic.Generic.Generic.Direction;

public class Main {
	
	public static Direction readCommand() {
		while (true) {
			Scanner reader = new Scanner(System.in);
			String input = reader.next();
			reader.close();
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
		}
	}
	
	public static void main(String[] args) {
		char map1[][] = {
				 {'X','X','X','X','X','X','X','X','X','X'},
				 {'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				 {'X','X','X',' ','X','X','X',' ',' ','X'},
				 {'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				 {'X','X','X',' ','X','X','X',' ',' ','X'},
				 {' ',' ','H',' ',' ',' ',' ',' ',' ','X'},
				 {' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				 {'X','X','X',' ','X','X','X','X',' ','X'},
				 {'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				 {'X','X','X','X','X','X','X','X','X','X'}
				 };
		
		GameMap gameMap1 = new GameMap(map1);
		Game game1 = new Game(gameMap1);
		game1.printMap();
		Hero hero = (Hero) game1.getHero();
		
		while(true) {
			Direction d = readCommand();
			game1.moveEntity(hero, d);
			game1.printMap();
		}
		
	}

}
