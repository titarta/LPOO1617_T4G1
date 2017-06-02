package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.module.entities.EntityModel;

/**
 * Created by Tiago on 28/05/2017.
 */

public class FloorBody extends EntityBody {
    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world    The world this body lives on.
     * @param model    The model representing the body.
     */
    public FloorBody(World world, EntityModel model) {
        super(world, model, BodyDef.BodyType.StaticBody);
        createFixture(body, new float[]{0,0, 600,0, 0,20, 600,20}, 600, 20, 1f, 0.4f, 0.5f);
    }
}
