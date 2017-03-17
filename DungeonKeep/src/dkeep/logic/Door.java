package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Door extends Static {
	public Door(Coordinate coord) {
		super(coord);
		entityChar = 'I';
		open = false;
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
	}
	
}