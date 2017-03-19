package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Door extends Static {
	public Door(Coordinate coord) {
		super(coord);
		entityChar = 'I';
		open = false;
		priority = 6;
	}

	boolean open; //V - aberta

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean state) {
		this.open = state;
	}
	
	public void toggle() {
		open = open ? false : true;
		blocksMovement = !blocksMovement;
		if (entityChar == 'I') {
			entityChar = 'S';
		} else {
			entityChar = 'I';
		}
	}
}