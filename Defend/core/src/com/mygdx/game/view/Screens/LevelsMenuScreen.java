package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.module.GameModel;

import java.util.ArrayList;

import Utils.EnemyEntry;
import Utils.MenuOption;

/**
 * Created by Tiago on 28/05/2017.
 */

public class LevelsMenuScreen extends MenuScreen {

    public LevelsMenuScreen(DefendGame game, ScreenMother returnScreen) {
        super(game);
        options = new ArrayList<MenuOption>();
        GameModel g = new GameModel();
        options.add(new MenuOption(new GameScreen(game, g, new GameController(g), level(1), this), "Level 1", game));
        options.add(new MenuOption(new GameScreen(game, g, new GameController(g), level(2), this), "Level 2", game));
        options.add(new MenuOption(new GameScreen(game, g, new GameController(g), level(3), this), "Level 3", game));
        options.add(new MenuOption(new GameScreen(game, g, new GameController(g), level(4), this), "Level 4", game));
        options.add(new MenuOption(new GameScreen(game, g, new GameController(g), level(5), this), "Level 5", game));
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

    private ArrayList<EnemyEntry> level(int number) {
        ArrayList<EnemyEntry> level = new ArrayList<EnemyEntry>();
        for (int i = 0; i < 30; i = i + 3) {
            level.add(new EnemyEntry(i, number));
        }
        return level;
    }
}
