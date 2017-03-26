package dkeep.logic;

import java.util.HashSet;

import dkeep.logic.Generic.Coordinate;

public class Lever extends Static implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private HashSet<Door> doors;

	public Lever(Coordinate coord, HashSet<Door> d) {
		super(coord);
		doors = d;
		blocksMovement = false;
		entityChar = 'l';
		priority = 8;
	}

	public HashSet<Door> getDoors() {
		return doors;
	}

	public void setDoors(HashSet<Door> doors) {
		this.doors = doors;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Lever((Coordinate) coordinate.clone(),null);
	}
	
	
	
}
