package dkeep.logic;

import java.util.HashMap;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

public class Entity {
	protected Coordinate coordinate;
	protected char entityChar;
	protected boolean blockMovement;
	
	//private HashMap<Coordinate, Entity> board;
	//private Entity holster;
	
	
	
	public Entity(Coordinate coord) {
		coordinate = coord;
	}
	public boolean blocksMovement() {
		return blockMovement;
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

	/*Entity(HashMap<Coordinate, Entity> board) {
		this.board = board;
	}
	*/
}
