package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Rookie extends Guard implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

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
