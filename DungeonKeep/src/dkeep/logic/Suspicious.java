package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Suspicious extends Guard{
	
	private boolean isSuspicious;
	
	public Suspicious(Coordinate coord) {
		super(coord);
	}

	public boolean isSuspicious() {
		return isSuspicious;
	}
	
	
}
