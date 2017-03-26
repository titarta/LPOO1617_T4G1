package dkeep.logic;


import dkeep.logic.Generic.Coordinate;

public class Club extends NonStatic implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	public Club() {
		super(new Coordinate(-1,-1));
		entityChar = '*';
		blocksMovement = false;
		priority = 2;
	}
	
	public boolean use(Coordinate coord) {
		coordinate = coord;
		entityChar = '*';
		return true;
	}
	
	public void goesToKey() {
		entityChar = '$';
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Club();
	}
	
	
	
}