package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

/**
 * Screen that handles the menu where the user upgrade the tower stats.
 */
class UpgradeScreen extends ScreenMother implements Screen {

    /**
     * Cost of an upgrade, increase with number of upgrades made.
     */
    private int upgradeCost;

    /**
     * Number of upgrades made + 1 (number of the upgrade we are going to buy).
     */
    private int evolutionNumber;

    /**
     * Skin for the TextButtons.
     */
    private Skin buttonsSkin;

    /**
     * Background texture.
     */
    private Texture background;

    /**
     * Button of upgrade.
     */
    private TextButton buttonUpgrade;

    /**
     * Button to go back.
     */
    private TextButton buttonBack;

    /**
     * Label indicating the stat we are upgrading.
     */
    private Label label;

    /**
     * Screen that called this screen (LobbyScreen).
     */
    private ScreenMother backScreen;

    /**
     * Stats which are increased with each upgrade.
     */
    private StatsGroup statsIncrease;

    /**
     * Used to implement upgrade button correctly.
     */
    private boolean upgFlag;

    /**
     * Used to implement back button correctly.
     */
    private boolean backFlag;

    /**
     * Creates the upgrade menu screen.
     *
     * @param game Instance of game, to acess tower stats.
     * @param statName Name of the stat we are increasing, to appear in the label.
     * @param screen Screen to return.
     * @param statsIncrease Increase of stats per upgrade.
     */
    UpgradeScreen(DefendGame game, String statName, ScreenMother screen, StatsGroup statsIncrease) {
        super(game);
        upgradeCost = 100;
        evolutionNumber = 0;
        background = new Texture("MainMenu/fundoUpgrade.png");
        buttonsSkin = new Skin(Gdx.files.internal("MainMenu/uiskin.json"));
        backScreen = screen;
        createButtons();
        createLabel(statName);
        this.statsIncrease = statsIncrease;
        upgFlag = false;
        backFlag = false;
    }

    /**
     * Override of render method of ScreenMother.
     * Draws the back ground and handles buttons and labels.
     *
     * @param delta Not used.
     */
    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.end();
        stage.act();
        stage.draw();
    }

    /**
     * Creates a label indicating the stat we are upgrading.
     *
     * @param statName String wrote on the label.
     */
    private void createLabel(String statName) {
        label = new Label(statName, labelSkin);
        label.setPosition(300 - 5*statName.length(), 250);
        label.setFontScaleX(1.4f);
        label.setFontScaleY(1.4f);
        stage.addActor(label);
    }

    /**
     * Create the two buttons of the screen.
     */
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

    /**
     * Updates the upgrade cost and evolution number.
     */
    private void increaseCost() {
        evolutionNumber++;
        upgradeCost = evolutionNumber*100;
        buttonUpgrade.setText("Cost: " + upgradeCost);
    }

    /**
     * Increases the stats of tower.
     */
    private void upgradeHandler() {
        if (game.gameInfo.getMoney() < upgradeCost) {
            return;
        }
        game.gameInfo.spendMoney(upgradeCost);
        game.gameInfo.upgradeStat(statsIncrease);
        increaseCost();
        this.updateGameInfo();
    }

    /**
     * Sets the screen to the previous one.
     */
    protected void backHandler() {
        game.setScreen(backScreen);
        Gdx.input.setInputProcessor(backScreen.stage);
        backScreen.updateGameInfo();
    }

    /**
     * Sets evolution number and corresponded upgrade cost.
     *
     * @param evolutionNumber Evolution number to be setted.
     */
    void setEvolutionNumber(int evolutionNumber) {
        this.evolutionNumber = evolutionNumber;
        upgradeCost = evolutionNumber*100;
        buttonUpgrade.setText("Cost: " + upgradeCost);
    }

    /**
     * Getter for evolution number.
     *
     * @return Evolution number.
     */
    int getEvolutionNumber() {
        return evolutionNumber;
    }

}
