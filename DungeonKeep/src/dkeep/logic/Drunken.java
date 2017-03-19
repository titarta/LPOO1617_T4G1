package dkeep.logic;

import java.util.Random;

import dkeep.logic.Generic.Coordinate;

public class Drunken extends Guard{
	
	private boolean isSleeping;
	private boolean isConfused;
	private int sleepTurn;


	public Drunken(Coordinate coord) {
		super(coord);
		isSleeping = false;
		sleepTurn = 0;
	}
	
	public boolean isConfused() {
		return isConfused;
	}

	public void setConfused(boolean isConfused) {
		this.isConfused = isConfused;
	}
	
	public boolean isSleeping() {
		return isSleeping;
	}
	

	@Override
	public void updateWalkPathPos() {
		if (!isSleeping) {
			if (isConfused) {
				if (walkPathPosition > 0) {
					walkPathPosition--;
				} else {
					walkPathPosition = walkPath.length;
				}
			} else {
				super.updateWalkPathPos();
			}
		}
	}

	@Override
	protected boolean deploysEvent() {
		if(super.deploysEvent()) {
			isSleeping = true;
			entityChar = 'g';
			return true;
		}
		return false;
	}

	@Override
	public Coordinate updateGuard() {
		if (!isSleeping) {
			this.deploysEvent();
			if (isConfused) {
				if (walkPathPosition > 0) {
					walkPathPosition--;
				} else {
					walkPathPosition = walkPath.length - 1;
				}
				this.coordinate.set(this.coordinate.getX() - walkPath[walkPathPosition].getX(),this.coordinate.getY() - walkPath[walkPathPosition].getY());
				return this.coordinate;
			} else {
				return super.updateGuard();
			}
		} else {
			if (sleepTurn == 2) {
				sleepTurn = 0;
				Random r = new Random();
				if (r.nextInt(2) == 0) {
					isConfused = !isConfused;
				}
				isSleeping = false;
				entityChar = 'G';
			} else {
				sleepTurn++;
			}
			return this.coordinate;
		}
	}

	public void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}

	public int getSleepTurn() {
		return sleepTurn;
	}

	public void setSleepTurn(int sleepTurn) {
		this.sleepTurn = sleepTurn;
	}
	
	
	
}
