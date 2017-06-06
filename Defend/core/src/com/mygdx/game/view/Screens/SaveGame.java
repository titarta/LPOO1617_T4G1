package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.DefendGame;
import com.mygdx.game.view.Screens.ScreenMother;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SaveGame extends ScreenMother {
    private ScreenMother backScreen;

    SaveGame(DefendGame game, ScreenMother back) {
        super(game);
        backScreen = back;
    }

    @Override
    public void render(float delta) {
        try {
            FileOutputStream fileOut =  new FileOutputStream("../../core/src/tmp/gamestate.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game.gameInfo);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Couldn't save the game");
        }
        game.setScreen(backScreen);
        Gdx.input.setInputProcessor(backScreen.stage);
    }
}
