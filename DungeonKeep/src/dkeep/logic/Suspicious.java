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

	@Override
	public Coordinate updateGuard() {
		if (!isSuspicious) {
			return super.updateGuard();
		} else {
			this.coordinate.set(this.coordinate.getX() - walkPath[walkPathPosition].getX(),this.coordinate.getY() - walkPath[walkPathPosition].getY());
			if (walkPathPosition > 0) {
				walkPathPosition--;
			} else {
				walkPathPosition = walkPath.length;
			}
			return this.coordinate;
		}
	}
	
	
	
}
