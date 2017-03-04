package dkeep.logic.Generic;

import dkeep.logic.Generic.Generic;
import dkeep.logic.Generic.Generic.Direction;

public class Coordinate {
	
	public Coordinate(Integer x, Integer y) {
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
		x += direction.getX();
		y += direction.getY();
		return this;
	}
}
