package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Ogre extends NonStatic {
	protected boolean isStunned;

	public Ogre(Coordinate coord) {
		super(coord);
		entityChar = 'O';
		isStunned = false;
	}

	public boolean isStunned() {
		return isStunned;
	}

	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}
	

}