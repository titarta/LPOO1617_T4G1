package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

class UpgradeScreen extends ScreenMother implements Screen {
    private int upgradeCost;
    private int evolutionNumber;
    private Skin buttonsSkin;
    private Texture background;
    private TextButton buttonUpgrade;
    private TextButton buttonBack;
    private Label label;
    private ScreenMother backScreen;
    private StatsGroup statsIncrease;
    private boolean upgFlag;
    private boolean backFlag;


    UpgradeScreen(DefendGame game, String statName, ScreenMother screen, StatsGroup statsIncrease) {
        super(game);
        upgradeCost = 100;
        evolutionNumber = 1;
        background = new Texture("MainMenu/fundoUpgrade.png");
        buttonsSkin = new Skin(Gdx.files.internal("MainMenu/uiskin.json"));
        backScreen = screen;
        createButtons();
        createLabel(statName);
        this.statsIncrease = statsIncrease;
        upgFlag = false;
        backFlag = false;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.end();
        stage.act();
        stage.draw();
    }

    private void createLabel(String statName) {
        label = new Label(statName, buttonsSkin);
        this.label.setPosition(200, 250);
        this.label.setWidth(200);
        this.label.setHeight(100);
        stage.addActor(label);
    }

    private void createButtons() {
        buttonUpgrade = new TextButton("Cost: " + upgradeCost, buttonsSkin);
        buttonUpgrade.setPosition(50, 100);
        buttonUpgrade.setWidth(200);
        buttonUpgrade.setHeight(100);
        buttonUpgrade.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upgFlag = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (upgFlag) {
                    upgFlag = false;
                    upgradeHandler();
                }
            }
        });
        stage.addActor(buttonUpgrade);
        buttonBack = new TextButton("Back", buttonsSkin);
        buttonBack.setPosition(350, 100);
        buttonBack.setWidth(200);
        buttonBack.setHeight(100);
        buttonBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backFlag = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (backFlag) {
                    backFlag = false;
                    backHandler();
                }
            }
        });
        stage.addActor(buttonBack);
    }

    private void increaseCost() {
        evolutionNumber++;
        upgradeCost = evolutionNumber*100;
        System.out.println(upgradeCost);
        buttonUpgrade.setText("Cost: " + upgradeCost);
    }

    private void upgradeHandler() {
        game.gameInfo.towerStats.add(statsIncrease);
        increaseCost();
    }

    private void backHandler() {
        game.setScreen(backScreen);
        Gdx.input.setInputProcessor(backScreen.stage);
    }


}
