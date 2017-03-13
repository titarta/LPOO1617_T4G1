package dkeep.logic;

import java.util.HashMap;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Entity {
	protected Coordinate coordinate;
	protected char entityChar;
	protected boolean blocksMovement;
	
	//private HashMap<Coordinate, Entity> board;
	//private Entity holster;
	
	
	
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

	/*
	public HashMap<Coordinate, Entity> getBoard() {
		return board;
	}

	public void setBoard(HashMap<Coordinate, Entity> board) {
		this.board = board;
	}
	
	Entity(HashMap<Coordinate, Entity> board, Coordinate coordinate) {
		this.board = board;
		this.coordinate = coordinate;
	}
*/
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
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
		return true;
	}

	/*Entity(HashMap<Coordinate, Entity> board) {
		this.board = board;
	}
	*/
	
	
}
