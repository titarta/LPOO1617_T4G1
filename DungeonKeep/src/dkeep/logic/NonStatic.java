package dkeep.logic;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class NonStatic extends Entity {
	

	public NonStatic(Coordinate coord) {
		super(coord);
		blocksMovement = false;
	}

	public void move(Direction direction) {
		coordinate.update(direction);
	}
	
	
}
