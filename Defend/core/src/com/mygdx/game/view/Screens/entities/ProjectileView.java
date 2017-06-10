package com.mygdx.game.view.Screens.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DefendGame;

/**
 * Class that handles projectiles sprites.
 */

public class ProjectileView extends EntityView {
    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public ProjectileView(DefendGame game) {
        super(game);
    }

    /**
     * Creates Projectile sprite.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return Projectile Sprite.
     */
    @Override
    public Sprite createSprite(DefendGame game) {
        Texture texture = game.getAssetManager().get("projectile.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
