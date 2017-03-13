package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Drunken extends Guard{
	
	private boolean isSleeping;

	public Drunken(Coordinate coord) {
		super(coord);
		isSleeping = false;
	}
	
	public boolean isSleeping() {
		return isSleeping;
	}
	
}
