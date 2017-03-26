package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Hero extends NonStatic implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
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
		if (isArmed) {
			entityChar = 's';
		} else {
			entityChar = 'K';
		}
	}
	
	public void releaseKey() {
		hasKey = false;
		if (isArmed) {
			entityChar = 'A';
		} else {
			entityChar = 'H';
		}
	}
	
	public boolean hasKey() {
		return hasKey;
	}

	public boolean isArmed() {
		return isArmed;
	}
	
	public void catchWeapon() {
		isArmed = true;
		if (!hasKey) {
			entityChar = 'A';
		} else {
			entityChar = 's';
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Hero((Coordinate) coordinate.clone());
	}

	
	

}