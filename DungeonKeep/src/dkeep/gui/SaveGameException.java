package dkeep.gui;

import dkeep.logic.Game;

public class SaveGameException extends Throwable{

	private Game game;
	
	public SaveGameException( Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
}
