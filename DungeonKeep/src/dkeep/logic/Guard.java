package dkeep.logic;

import java.util.Random;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Guard extends NonStatic implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	protected Direction[] walkPath;
	protected int walkPathPosition;

	public Guard(Coordinate coord) {
		super(coord);
		entityChar = 'G';
		priority = 4;
	}
	
	protected boolean deploysEvent() {
		Random rnd = new Random();
		return rnd.nextInt(6) == 0;
	}
	
	public void setWalkPath(Direction[] walkPath) {
		this.walkPath = walkPath;
	}
	
	public void updateWalkPathPos() {
		if (walkPathPosition < walkPath.length - 1) {
			walkPathPosition++;
		} else {
			walkPathPosition = 0;
		}
	}
	
	public void setWalkPathPosition(int walkPathPosition) {
		this.walkPathPosition = walkPathPosition;
	}

	public int getWalkPathPos() {
		return walkPathPosition;
	}
	
	public Coordinate updateGuard() {
		this.coordinate.update(walkPath[walkPathPosition]);
		if (walkPathPosition < walkPath.length - 1) {
			walkPathPosition++;
		} else {
			walkPathPosition = 0;
		}
		return this.coordinate;
	}
	
	public Direction getGuardDirection() {
		return walkPath[walkPathPosition];
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Guard((Coordinate) coordinate.clone());
	}
	
	

}