package dkeep.logic;

import java.util.Random;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Guard extends NonStatic {
	protected Direction[] walkPath;
	protected int walkPathPosition;

	public Guard(Coordinate coord) {
		super(coord);
		entityChar = 'G';
	}
	
	protected boolean deploysEvent() {
		Random rnd = new Random();
		return rnd.nextInt(3) == 0;
	}
	
	public void setWalkPath(Direction[] walkPath) {
		this.walkPath = walkPath;
	}
	
	public void updateWalkPathPos() {
		walkPathPosition++;
	}
	
	public int getWalkPathPos() {
		return walkPathPosition;
	}
	
	

}