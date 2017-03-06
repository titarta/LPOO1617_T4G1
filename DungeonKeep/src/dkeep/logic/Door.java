package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Door extends Static {
	public Door(Coordinate coord) {
		super(coord);
		entityChar = 'I';
	}

	boolean state;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void toggle() {
		state = state ? false : true;
	}
	
}