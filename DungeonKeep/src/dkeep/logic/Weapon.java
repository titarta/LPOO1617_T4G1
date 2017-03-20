package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Weapon extends Static{

	public Weapon(Coordinate coord) {
		super(coord);
		blocksMovement = false;
		entityChar = 'w';
		priority = 9;
	}

}
