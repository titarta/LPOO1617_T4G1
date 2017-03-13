package dkeep.logic;

import java.util.HashSet;

import dkeep.logic.Generic.Coordinate;

public class Lever extends Static {
	
	private HashSet<Door> doors;

	public Lever(Coordinate coord, HashSet<Door> d) {
		super(coord);
		doors = d;
		
	}

	public HashSet<Door> getDoors() {
		return doors;
	}

	public void setDoors(HashSet<Door> doors) {
		this.doors = doors;
	}
	
	
	
}
