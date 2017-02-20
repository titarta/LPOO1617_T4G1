package dkeep.logic;

public class Door extends Static {
	boolean state;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void toggle() {
		state = state ? false : true;
	}
	
}