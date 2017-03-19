package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Hero extends NonStatic {

	protected boolean hasKey;
	protected boolean isArmed;
	
	
	public Hero(Coordinate coord) {
		super(coord);
		entityChar = 'H';
		hasKey = false;
		priority = 5;
	}
	
	public void catchKey() {
		hasKey = true;
		entityChar = 'K';
	}
	
	public void releaseKey() {
		hasKey = false;
		entityChar = 'H';
	}
	
	public boolean hasKey() {
		return hasKey;
	}

	public boolean isArmed() {
		return isArmed;
	}

	
	

}