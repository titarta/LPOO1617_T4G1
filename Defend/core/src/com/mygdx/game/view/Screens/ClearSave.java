package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.DefendGame;

/**
 * Screen that deletes the saved game.
 *
 * <p>
 *     This functionality is a screen because it is an option of the main menu.
 * </p>
 */
class ClearSave extends ScreenMother {

    /**
     * Creates ClearSave screen.
     * @param game
     */
    ClearSave(DefendGame game) {
        super(game);
    }

    /**
     * Render method of screen. Actually stores the the information of a new game in the serializable file, and ends the program.
     *
     * @param delta Only here because it is an override method (it is not needed or used).
     */
    @Override
    public void render(float delta) {
        game.gameInfo.resetPrefs();
        Gdx.app.exit();
    }
}
