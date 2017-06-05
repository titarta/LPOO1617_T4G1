package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.DefendGame;

/**
 * Created by Tiago on 04/05/2017.
 */

public class UpgradeScreen extends ScreenMother implements Screen {
    private int upgradeCost;
    private Skin buttonsSkin;
    private Texture background;
    private TextButton button;


    public UpgradeScreen(DefendGame game) {
        super(game);
        background = new Texture("MainMenu/fundoUpgrade.png");
        buttonsSkin = new Skin(Gdx.files.internal("MainMenu/uiskin.json"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
