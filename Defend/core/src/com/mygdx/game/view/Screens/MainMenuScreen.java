package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import com.mygdx.game.Utils.MenuOption;

/**
 * Main menu screen.
 */
public class MainMenuScreen extends MenuScreen implements Screen{

    /**
     * Creates main menu.
     *
     * @param game Instance of game.
     */
    public MainMenuScreen(DefendGame game) {
        super(game);
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption(new LobbyMenuScreen(game, this), "Start Game"));
        options.add(new MenuOption(new ClearSave(game), "Clear save and exit"));
        options.add(new MenuOption(null, "Exit"));
        createButtons();
    }

    /**
     * Overrides render method from screenMother.
     *
     * @param delta Not used.
     */
    @Override
    public void render(float delta) {
        super.render(1);
    }
}
