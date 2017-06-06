package com.mygdx.game.Utils;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;
import com.mygdx.game.view.Screens.MenuScreen;
import com.mygdx.game.view.Screens.ScreenMother;

public class MenuOption {
    private ScreenMother nextScreen;
    private String name;
    private boolean pressed;
    private DefendGame game;

    public MenuOption(ScreenMother nextScreen, String name, DefendGame game) {
        this.nextScreen = nextScreen;
        this.name = name;
        this.game = game;
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean value) {
        pressed = value;
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

    public String getName() {
        return name;
    }
}
