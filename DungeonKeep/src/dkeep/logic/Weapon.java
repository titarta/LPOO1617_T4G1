package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Weapon extends Static implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	public Weapon(Coordinate coord) {
		super(coord);
		blocksMovement = false;
		entityChar = 'w';
		priority = 9;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Weapon((Coordinate) coordinate.clone());
	}
	
	

}
