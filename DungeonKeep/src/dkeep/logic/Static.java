package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Static extends Entity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public Static(Coordinate coord) {
		super(coord);
		blocksMovement = true;
	}
	

}
