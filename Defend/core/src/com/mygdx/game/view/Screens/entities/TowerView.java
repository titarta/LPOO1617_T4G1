package com.mygdx.game.view.Screens.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DefendGame;

/**
 * Created by Tiago on 29/05/2017.
 */

public class TowerView extends EntityView {
    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public TowerView(DefendGame game) {
        super(game);
    }

    @Override
    public Sprite createSprite(DefendGame game) {
        Texture texture = game.getAssetManager().get("tower.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
