package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Wall extends Static {

	public Wall(Coordinate coord) {
		super(coord);
		entityChar = 'X';
		priority = 1;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Wall((Coordinate) coordinate.clone());
	}
	
}
