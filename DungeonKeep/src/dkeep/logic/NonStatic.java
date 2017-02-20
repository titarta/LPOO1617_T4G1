package dkeep.logic;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class NonStatic extends Entity {

	public void move(Direction direction) {
		Coordinate nextCoordinate = getCoordinate().update(direction);
		
		if (getBoard().containsKey(nextCoordinate)) {
			if (getBoard().get(nextCoordinate) instanceof Static) {
				
			}
		}
	}

}
