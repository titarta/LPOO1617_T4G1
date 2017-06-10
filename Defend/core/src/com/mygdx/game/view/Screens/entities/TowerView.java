package com.mygdx.game.view.Screens.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DefendGame;

/**
 * Class that handles projectiles sprites.
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

    /**
     * Creates Tower sprite.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return Tower sprite.
     */
    @Override
    public Sprite createSprite(DefendGame game) {
        Texture texture = game.getAssetManager().get("tower.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
