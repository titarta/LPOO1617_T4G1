package com.mygdx.game.Utils;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;
import com.mygdx.game.view.Screens.MenuScreen;
import com.mygdx.game.view.Screens.ScreenMother;

public class MenuOption {
    private ScreenMother nextScreen;
    private String name;

    public MenuOption(ScreenMother nextScreen, String name, DefendGame game) {
        this.nextScreen = nextScreen;
        this.name = name;
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

    public String getName() {
        return name;
    }
}
