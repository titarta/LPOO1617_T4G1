package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Ogre extends NonStatic {
	protected boolean isStunned;
	protected boolean isOverKey;

	public Ogre(Coordinate coord) {
		super(coord);
		entityChar = 'O';
		isStunned = false;
		isOverKey = false;
	}

	public boolean isStunned() {
		return isStunned;
	}

	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}

	public boolean isOverKey() {
		return isOverKey;
	}

	public void setOverKey(boolean isOverKey) {
		if (isOverKey) {
			entityChar = '$';
		} else {
			entityChar = 'O';
		}
		this.isOverKey = isOverKey;
	}
	

}