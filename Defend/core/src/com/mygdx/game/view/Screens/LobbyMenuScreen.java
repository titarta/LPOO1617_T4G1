package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import Utils.MenuOption;

/**
 * Created by Tiago on 27/05/2017.
 */

public class LobbyMenuScreen extends MenuScreen implements Screen {

    public LobbyMenuScreen(DefendGame game, ScreenMother returnScreen) {
        super(game);
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption(new LevelsMenuScreen(game, this), "Levels", game));
        options.add(new MenuOption(returnScreen, "Back", game));
        createButtons();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(1);
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

