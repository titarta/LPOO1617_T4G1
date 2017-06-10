package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DefendGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Tiago on 27/05/2017.
 */

public abstract class ScreenMother implements Screen {
    protected DefendGame game;
    protected Viewport menuPort;
    protected OrthographicCamera menuCam;
    public Stage stage;
    public Skin labelSkin;
    public Label moneyLabel;


    public ScreenMother(DefendGame game) {
        this.game = game;
        menuCam = new OrthographicCamera(game.getViewportWidth(), game.getViewportHeight());
        menuPort = new FitViewport(game.getViewportWidth(), game.getViewportHeight(), menuCam);
        labelSkin = new Skin(Gdx.files.internal("LabelSkin/comic-ui.json"));
        stage = new Stage(menuPort, game.batch);
        createLabel();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        menuPort.update(width,height);
        menuCam.update();
        stage.setViewport(menuPort);
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

    public void updateGameInfo() {
        moneyLabel.setText("Money: " + game.gameInfo.getMoney());
        try {
            FileOutputStream fileOut =  new FileOutputStream("../../core/src/tmp/gamestate.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game.gameInfo);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Couldn't save the game");
        }
    }

    private void createLabel() {
        moneyLabel = new Label("Money: " + game.gameInfo.getMoney(), labelSkin);
        moneyLabel.setPosition(470, 370);
        moneyLabel.setFontScaleX(1.2f);
        moneyLabel.setFontScaleY(1.2f);
        stage.addActor(moneyLabel);
    }
}
