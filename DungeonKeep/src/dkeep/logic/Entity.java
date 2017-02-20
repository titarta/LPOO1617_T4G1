package dkeep.logic;

import java.util.HashMap;

import dkeep.logic.Generic.Coordinate;

public class Entity {
	private Coordinate coordinate;
	private HashMap<Coordinate, Entity> board;
	private Entity holster;
	
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

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	Entity(HashMap<Coordinate, Entity> board) {
		this.board = board;
	}
	
	Entity() {
		
	}
}