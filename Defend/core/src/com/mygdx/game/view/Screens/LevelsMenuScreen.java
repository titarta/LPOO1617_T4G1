package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.module.GameModel;

import java.util.ArrayList;

import com.mygdx.game.Utils.EnemyEntry;
import com.mygdx.game.Utils.MenuOption;


class LevelsMenuScreen extends MenuScreen {
    private GameModel g;
    private ScreenMother returnScreen;

    LevelsMenuScreen(DefendGame game, ScreenMother returnScreen) {
        super(game);
        options = new ArrayList<MenuOption>();
        this.returnScreen = returnScreen;
        g = new GameModel();
        options.add(new MenuOption(new GameScreen(game, level(1), this), "Level 1"));
        options.add(new MenuOption(new GameScreen(game, level(2), this), "Level 2"));
        options.add(new MenuOption(new GameScreen(game, level(3), this), "Level 3"));
        options.add(new MenuOption(new GameScreen(game, level(4), this), "Level 4"));
        options.add(new MenuOption(new GameScreen(game, level(5), this), "Level 5"));
        options.add(new MenuOption(returnScreen, "Back"));
        createButtons();
    }

    @Override
    public void render(float delta) {
        super.render(1);
    }


    private ArrayList<EnemyEntry> level(int number) {
        ArrayList<EnemyEntry> level = new ArrayList<EnemyEntry>();
        for (int i = 0; i < 30; i = i + 3) {
            level.add(new EnemyEntry(i, number));
        }
        return level;
    }
}
