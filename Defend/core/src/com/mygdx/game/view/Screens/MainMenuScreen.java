package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import Utils.MenuOption;

public class MainMenuScreen extends MenuScreen implements Screen{



    public MainMenuScreen(DefendGame game) {
        super(game);
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption(new LobbyMenuScreen(game, this), "Start Game", game));
        options.add(new MenuOption(null, "Exit", game));
        createButtons();
    }

    @Override
    public void render(float delta) {
        super.render(1);
    }
}
