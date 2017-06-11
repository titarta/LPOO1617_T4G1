package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DefendGame;

/**
 * Wraps Screen class, and added some attributes common to every Screen.
 */
public abstract class ScreenMother implements Screen {

    /**
     * Stage.
     */
    public Stage stage;
    /**
     * Skin of the label where current money is displayed.
     */
    public Skin labelSkin;
    /**
     * Label where current money is displayed.
     */
    public Label moneyLabel;
    /**
     * Instance of game, with the assetManager and gameInfo.
     */
    protected DefendGame game;
    /**
     * Viewport.
     */
    protected Viewport menuPort;
    /**
     * Camera.
     */
    protected OrthographicCamera menuCam;

    /**
     * Creates a class which wraps Screen.
     *
     * @param game Instance of game.
     */
    public ScreenMother(DefendGame game) {
        this.game = game;
        menuCam = new OrthographicCamera(game.getViewportWidth(), game.getViewportHeight());
        menuPort = new FitViewport(game.getViewportWidth(), game.getViewportHeight(), menuCam);
        labelSkin = new Skin(Gdx.files.internal("LabelSkin/comic-ui.json"));
        stage = new Stage(menuPort, game.batch);
        createLabel();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    /**
     * Overrides Screen method resize.
     *
     * @param width New game width.
     * @param height New game height.
     */
    @Override
    public void resize(int width, int height) {
        menuPort.update(width,height);
        menuCam.update();
        stage.setViewport(menuPort);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    /**
     * Saves the game and updates the label where money is displayed.
     */
    public void updateGameInfo() {
        moneyLabel.setText("Money: " + game.gameInfo.getMoney());
        game.gameInfo.writePrefs();
    }

    /**
     * Creates the label where money is displayed.
     */
    private void createLabel() {
        moneyLabel = new Label("Money: " + game.gameInfo.getMoney(), labelSkin);
        moneyLabel.setPosition(470, 370);
        moneyLabel.setFontScaleX(1.2f);
        moneyLabel.setFontScaleY(1.2f);
        stage.addActor(moneyLabel);
    }
}
