package dkeep.logic;

import java.util.HashMap;
import java.util.UUID;

import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Entity {
	protected Coordinate coordinate;
	protected char entityChar;
	protected boolean blocksMovement;
	protected int priority;
	private UUID id = UUID.randomUUID();
	
	
	public Entity(Coordinate coord) {
		coordinate = coord;
	}
	public boolean blocksMovement() {
		return blocksMovement;
	}
	public char getEntityChar() {
		return entityChar;
	}
	
	public void move(Direction direction) {
	}


	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public void setEntityChar(char c) {
		entityChar = c;
	}
	
	public boolean isOgre() {
		return false;
	}
	

	
	public int getPriority() {
		return priority;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Entity other = (Entity) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Entity((Coordinate) coordinate.clone());
	}

	
	
	
}
