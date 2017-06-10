package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import com.mygdx.game.Utils.MenuOption;



class LobbyMenuScreen extends MenuScreen implements Screen {

    LobbyMenuScreen(DefendGame game, ScreenMother returnScreen) {
        super(game);
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption(new LevelsMenuScreen(game, this), "Levels"));
        options.add(new MenuOption(new HpUpgradeScreen(game, this), "Health upgrade"));
        options.add(new MenuOption(new DamageUpgradeScreen(game, this), "Damage upgrade"));
        options.add(new MenuOption(new DefenseUpgradeScreen(game, this), "Defense upgrade"));
        options.add(new MenuOption(new CritChanceUpgradeScreen(game, this), "Crit chance upgrade"));
        options.add(new MenuOption(returnScreen, "Back"));
        createButtons();
    }

    @Override
    public void render(float delta) {
        super.render(1);
    }

}

