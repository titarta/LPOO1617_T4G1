package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.GameInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class ClearSave extends ScreenMother {
    private ScreenMother backScreen;

    ClearSave(DefendGame game, ScreenMother back) {
        super(game);
        backScreen = back;
    }

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
