package dkeep.logic;

import java.util.HashMap;

import dkeep.logic.Generic.Coordinate;

public class Map {
	private HashMap<Coordinate, Entity> board = new HashMap<Coordinate, Entity>();
	
	public HashMap<Coordinate, Entity> getBoard() {
		return board;
	}

	public void setBoard(HashMap<Coordinate, Entity> board) {
		this.board = board;
	}

	public void insert(Coordinate coordinate, Entity entity) {
		board.put(coordinate, entity);
	}
	
	
}
