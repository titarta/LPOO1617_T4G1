package dkeep.logic;

public class GameEndException extends Exception {
	private boolean result;

	public GameEndException(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
		return result;
	}
	
}
