package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.DefendGame;

import java.util.ArrayList;

import com.mygdx.game.Utils.MenuOption;

/**
 * Screen that handles the normal menus.
 */
public class MenuScreen extends ScreenMother implements Screen {

    /**
     * Information about the various options of the menu.
     */
    ArrayList<MenuOption> options;

    /**
     * ArrayList of the TextButtons which make the menu.
     */
    private ArrayList<TextButton> buttons;

    /**
     * Background texture.
     */
    private Texture back;

    /**
     * Skin of buttons who make the menu.
     */
    private Skin buttonsSkin;

    /**
     * Used to implement buttons correctly.
     */
    private boolean buttonFlag;

    /**
     * Creates the menu screen.
     *
     * @param game Instance of game.
     */
    MenuScreen(DefendGame game) {
        super(game);
        back = new Texture("MainMenu/fundoMenu.png");
        buttonsSkin = new Skin(Gdx.files.internal("MainMenu/uiskin.json"));
        buttons = new ArrayList<TextButton>();
        buttonFlag = false;
    }

    /**
     * Override render function from ScreenMother.
     * Draws the back ground and handles buttons and labels.
     *
     * @param delta Not used.
     */
    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(back,0,0);
        game.batch.end();
        stage.act();
        stage.draw();
    }

    /**
     * Creates the buttons which consist the menu.
     */
    void createButtons() {
        for (int i = 0; i < options.size(); i++) {
            TextButton button = new TextButton(options.get(i).getName(), buttonsSkin);
            button.setWidth(150);
            button.setHeight(50);
            button.setX(50);
            button.setY(300 - 50*i);
            button.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    buttonFlag = true;
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if (buttonFlag) {
                        buttonFlag = false;
                        buttonClickEvent((int)(6 - Math.floor(event.getStageY()/50)));
                    }
                }
            });
            buttons.add(button);
            stage.addActor(button);
        }
    }

    /**
     * Handles the user input.
     *
     * @param index Button user presses.
     */
    private void buttonClickEvent(int index) {
        this.dispose();
        if (options.get(index).getNextScreen() == null) {
            Gdx.app.exit();
        } else {
            game.setScreen(options.get(index).getNextScreen());
            Gdx.input.setInputProcessor(((ScreenMother)options.get(index).getNextScreen()).stage);
            if (options.get(index).getNextScreen().getClass() == GameScreen.class) {
                ((GameScreen) options.get(index).getNextScreen()).reset();
            } else {
                ((ScreenMother) options.get(index).getNextScreen()).updateGameInfo();
            }
        }
    }
}
