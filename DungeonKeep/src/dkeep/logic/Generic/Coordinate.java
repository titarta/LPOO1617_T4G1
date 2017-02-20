package dkeep.logic.Generic;

import dkeep.logic.Generic.Generic.Direction;

public class Coordinate {
	
	Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	private Integer x;
	private Integer y;
	
	public void set(Integer x, Integer y) {
		setX(x);
		setY(y);
	}
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
	public Coordinate update(Direction direction) {
		if (direction.equals(Direction.UP)) {
			y++;
		} else if (direction.equals(Direction.DOWN)) {
			y--;
		} else if (direction.equals(Direction.RIGHT)) {
			x++;
		} else if (direction.equals(Direction.LEFT)) {
			x--;
		}
		return this;
	}
}
