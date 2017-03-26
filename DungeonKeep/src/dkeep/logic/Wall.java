package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Wall extends Static implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

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
