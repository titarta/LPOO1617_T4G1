package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import com.mygdx.game.Utils.MenuOption;

public class MainMenuScreen extends MenuScreen implements Screen{



    public MainMenuScreen(DefendGame game) {
        super(game);
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption(new LobbyMenuScreen(game, this), "Start Game", game));
        options.add(new MenuOption(new SaveGame(game, this), "Save Game", game));
        options.add(new MenuOption(new ClearSave(game, this), "Clear save and exit", game));
        options.add(new MenuOption(null, "Exit", game));
        createButtons();
    }

    @Override
    public void render(float delta) {
        super.render(1);
    }
}
