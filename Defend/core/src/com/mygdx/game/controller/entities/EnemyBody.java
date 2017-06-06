package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.module.entities.EntityModel;

/**
 * Class that creates an enemy body to live in a world.
 */
public class EnemyBody extends EntityBody {
    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world    The world this body lives on.
     * @param model    The model representing the body.
     */
    public EnemyBody(World world, EntityModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);
        createFixture(body, new float[]{0,0, 30,0, 0,30, 30,30}, 30, 30, 1f, 0f, 0.5f, (short)(~CATEGORY_ENEMY), CATEGORY_ENEMY);
    }
}
