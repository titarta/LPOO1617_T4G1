package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.GameInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
        try {
            FileOutputStream fileOut =  new FileOutputStream("../../core/src/tmp/gamestate.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new GameInfo());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Couldn't wipe out the game data");
        }
        Gdx.app.exit();
    }
}
