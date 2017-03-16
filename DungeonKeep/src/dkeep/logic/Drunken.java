package dkeep.logic;

import dkeep.logic.Generic.Coordinate;

public class Drunken extends Guard{
	
	private boolean isSleeping;

	public Drunken(Coordinate coord) {
		super(coord);
		isSleeping = false;
	}
	
	public boolean isSleeping() {
		return isSleeping;
	}
	
	

	@Override
	protected boolean deploysEvent() {
		if(super.deploysEvent()) {
			isSleeping = !isSleeping;
			return true;
		}
		return false;
	}

	@Override
	public Coordinate updateGuard() {
		if (!isSleeping) {
			this.deploysEvent();
			return super.updateGuard();
		} else {
			this.deploysEvent();
			return this.coordinate;
		}
	}
	
	
	
}
