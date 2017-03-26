package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Key extends Static {

	public Key(Coordinate coord) {
		super(coord);
		entityChar = 'k';
		blocksMovement = false;
		priority = 7;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Key((Coordinate) coordinate.clone());
	}
	
}