package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import com.mygdx.game.Utils.MenuOption;

public class MainMenuScreen extends MenuScreen implements Screen{



    public MainMenuScreen(DefendGame game) {
        super(game);
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption(new LobbyMenuScreen(game, this), "Start Game"));
        options.add(new MenuOption(new ClearSave(game), "Clear save and exit"));
        options.add(new MenuOption(null, "Exit"));
        createButtons();
    }

    @Override
    public void render(float delta) {
        super.render(1);
    }
}
