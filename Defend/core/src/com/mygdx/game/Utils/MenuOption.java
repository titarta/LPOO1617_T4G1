package com.mygdx.game.Utils;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DefendGame;
import com.mygdx.game.view.Screens.MenuScreen;
import com.mygdx.game.view.Screens.ScreenMother;

/**
 * Class that stores information relative to an option of the menu.
 */
public class MenuOption {

    /**
     * Screen where the options leads to.
     */
    private ScreenMother nextScreen;

    /**
     * Name that appears in the button.
     */
    private String name;

    /**
     * Create a menu option.
     *
     * @param nextScreen Screen where the options leads to.
     * @param name Name that appears in the button.
     */
    public MenuOption(ScreenMother nextScreen, String name) {
        this.nextScreen = nextScreen;
        this.name = name;
    }

    /**
     * Getter for nextScreen.
     *
     * @return Screen where the options leads to.
     */
    public Screen getNextScreen() {
        return nextScreen;
    }

    /**
     * Getter for name.
     *
     * @return Name that appears in the button.
     */
    public String getName() {
        return name;
    }
}
