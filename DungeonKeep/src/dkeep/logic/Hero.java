package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Hero extends NonStatic {

	boolean hasKey;
	
	public Hero(Coordinate coord) {
		super(coord);
		entityChar = 'H';
		hasKey = false;
	}
	
	public void catchKey() {
		hasKey = true;
		entityChar = 'K';
	}
	
	public void releasKey() {
		hasKey = false;
		entityChar = 'H';
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	

}