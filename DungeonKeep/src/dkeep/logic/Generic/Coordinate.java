package dkeep.logic.Generic;

import java.util.ArrayList;
import java.lang.Math;
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
	
	public Coordinate copy() {
		return new Coordinate(x,y);
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
	
	public boolean isAdjacent (Coordinate coord) {
		return (this.y-coord.getY())*(this.y-coord.getY()) + (this.x-coord.getX())*(this.x-coord.getX()) <= 1.1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	
	
}
