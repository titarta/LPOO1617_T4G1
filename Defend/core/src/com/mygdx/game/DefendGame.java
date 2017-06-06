package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.GameLogic.GameInfo;
import com.mygdx.game.view.Screens.MainMenuScreen;
import com.mygdx.game.view.Screens.MenuScreen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DefendGame extends Game {
	public GameInfo gameInfo;
	private MainMenuScreen mainMenu;
	private AssetManager assetManager;
	private int VIEWPORT_HEIGHT = 400;
	private int VIEWPORT_WIDTH = 600;
	public SpriteBatch batch;

	public DefendGame() throws ClassNotFoundException {
		try {
			FileInputStream fileIn = new FileInputStream("../../core/src/tmp/gamestate.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			gameInfo = (GameInfo)in.readObject();
			in.close();
			fileIn.close();
			System.out.println(gameInfo.getMoney());
			System.out.println(gameInfo.getCritEvNumber());
			System.out.println(gameInfo.getDamageEvNumber());
			System.out.println(gameInfo.getDefenseEvNumber());
			System.out.println(gameInfo.getHpEvNumber());
			System.out.println(gameInfo.towerStats.getDamage());
		}catch(IOException i) {
			gameInfo = new GameInfo();
		}
	}

	public int getViewportHeight() {
		return VIEWPORT_HEIGHT;
	}

	public int getViewportWidth() {
		return VIEWPORT_WIDTH;
	}

	@Override
	public void create () {
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
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
