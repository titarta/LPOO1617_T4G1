package dkeep.logic;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class NonStatic extends Entity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public NonStatic(Coordinate coord) {
		super(coord);
		blocksMovement = false;
	}

	public void move(Direction direction) {
		coordinate.update(direction);
	}
	
	
}
