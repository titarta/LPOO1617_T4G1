package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLogic.GameInfo;
import com.mygdx.game.Utils.Chance;
import com.mygdx.game.view.Screens.MainMenuScreen;

/**
 * Game start class.
 */
public class DefendGame extends Game {

	/**
	 * All information about the state of the game.
	 */
	public GameInfo gameInfo;

	/**
	 * Batch to be used to draw.
	 */
	public SpriteBatch batch;

	/**
	 * First screen of the game.
	 */
	private MainMenuScreen mainMenu;

	/**
	 * Asset manager. Has all the assets stored.
	 */
	private AssetManager assetManager;

	private int VIEWPORT_HEIGHT = 400;
	private int VIEWPORT_WIDTH = 600;

	/**
	 * Creates the game.
	 */
	public DefendGame() {
		Chance.initialize();
	}

	public int getViewportHeight() {
		return VIEWPORT_HEIGHT;
	}

	public int getViewportWidth() {
		return VIEWPORT_WIDTH;
	}

	/**
	 * Starts the game. Loads all assets and sets Game screen to be the main menu. Also loads information from file.
	 */
	@Override
	public void create () {
		gameInfo = new GameInfo();
		gameInfo.initializePrefs();
		assetManager = new AssetManager();
		assetManager.load("tower.png", Texture.class);
		assetManager.load("enemy.png", Texture.class);
		assetManager.load("floor.png", Texture.class);
		assetManager.load("projectile.png", Texture.class);
		assetManager.finishLoading();

		batch = new SpriteBatch();
		mainMenu = new MainMenuScreen(this);
		setScreen(mainMenu);
		Gdx.input.setInputProcessor(mainMenu.stage);
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * Override of render method of Game.
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	/**
	 * Override of dispose method of Game.
	 */
	@Override
	public void dispose () {
		super.dispose();
	}

	/**
	 * Getter for AssetManager.
	 *
	 * @return Asset manager.
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}
}
