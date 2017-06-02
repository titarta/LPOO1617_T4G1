package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DefendGame;

/**
 * Created by Tiago on 27/05/2017.
 */

public class ScreenMother implements Screen {
    protected DefendGame game;
    protected Viewport menuPort;
    protected OrthographicCamera menuCam;
    public Stage stage;

    public ScreenMother(DefendGame game) {
        this.game = game;
        menuCam = new OrthographicCamera(game.getViewportWidth(), game.getViewportHeight());
        menuPort = new FitViewport(game.getViewportWidth(), game.getViewportHeight(), menuCam);
        stage = new Stage(menuPort, game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
}
