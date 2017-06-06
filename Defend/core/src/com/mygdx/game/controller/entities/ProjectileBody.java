package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.module.entities.EntityModel;

/**
 * Class that creates a projectile body to live in a world.
 */
public class ProjectileBody extends EntityBody {
    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world    The world this body lives on.
     * @param model    The model representing the body.
     */
    public ProjectileBody(World world, EntityModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);
        createFixture(body, new float[]{5f,0, 8.534f,1.464f, 10,5f, 8.534f,8.534f, 5f,10, 1.464f,8.534f, 0,5f, 1.464f,1.464f}, 5, 5, 1f, 0.4f, 0.5f, (short)-1, CATEGORY_PROJECTILE);
        body.setBullet(true);
    }
}
