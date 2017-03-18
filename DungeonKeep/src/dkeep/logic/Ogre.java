package dkeep.logic;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Ogre extends NonStatic {
	protected boolean isStunned;
	protected boolean isArmed;
	protected Club club;

	public Ogre(Coordinate coord, boolean hasClub) {
		super(coord);
		entityChar = 'O';
		isStunned = false;
		isArmed = hasClub;
		if (hasClub) {
			club = new Club();
		} else {
			club = null;
		}
	}

	public boolean isArmed() {
		return isArmed;
	}

	public Club getClub() {
		return club;
	}


	public boolean isStunned() {
		return isStunned;
	}

	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}


	public void goOverKey(boolean isOverKey) {
		entityChar = '$';
	}

	@Override
	public void move(Direction direction) {
		entityChar = 'O';
		super.move(direction);
	}
	

}