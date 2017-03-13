package dkeep.logic;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class NonStatic extends Entity {
	
	Entity holster;

	public NonStatic(Coordinate coord) {
		super(coord);
		blocksMovement = false;
	}

	public void move(Direction direction) {
		coordinate.update(direction);
	}
	
	public Entity getHolster() {
		return holster;
	}

	public void setHolster(Entity holster) {
		this.holster = holster;
	}

}
