package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Rookie extends Guard{

	public Rookie(Coordinate coord) {
		super(coord);
	}

	@Override
	public Coordinate updateGuard() {
		this.deploysEvent();
		return super.updateGuard();
	}

	@Override
	protected boolean deploysEvent() {
		return false;
	}
	
	

}
