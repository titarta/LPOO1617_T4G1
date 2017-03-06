package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.Generic.Generic;
import dkeep.logic.Generic.Generic.Direction;

public class Main {
	
	public static void main(String[] args) {
		char map1[][] = {
				 {'X','X','X','X','X','X','X','X','X','X'},
				 {'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				 {'X','X','X',' ','X','X','X',' ',' ','X'},
				 {'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				 {'X','X','X',' ','X','X','X',' ',' ','X'},
				 {' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				 {' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				 {'X','X','X',' ','X','X','X','X',' ','X'},
				 {'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
				 {'X','X','X','X','X','X','X','X','X','X'}
				 };
		
		GameMap gameMap1 = new GameMap(map1);
		Game game1 = new Game(gameMap1);
		game1.printMap();
	}

}
