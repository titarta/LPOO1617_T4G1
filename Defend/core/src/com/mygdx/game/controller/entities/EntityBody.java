package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

/**
 * Class responsible to wrapping the body of a world.
 *
 * <p>
 *     Has a method to create a fixture for the body, method which is called by subclasses.
 * </p>
 */
public abstract class EntityBody {
    /**
     * The Box2D body that supports this body.
     */
    final Body body;
    /**
     * Enemy Bit for collision mask
     */
    final short CATEGORY_ENEMY = 0x0001;  // 0000000000000001 in binary
    /**
     * Projectile Bit for collision mask
     */
    final short CATEGORY_PROJECTILE = 0x0002; // 0000000000000010 in binary
    /**
     * Floor Bit for collision mask
     */
    final short CATEGORY_FLOOR = 0x0004; // 0000000000000100 in binary

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     */
    EntityBody(World world, EntityModel model, BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    /**
     * Helper method to create a polygon fixture represented by a set of vertexes.
     *
     * @param body The body the fixture is to be attached to.
     * @param vertexes The vertexes defining the fixture in pixels so it is
     *                 easier to get them from a bitmap image.
     * @param density The density of the fixture. How heavy it is in relation to its area.
     * @param friction The friction of the fixture. How slippery it is.
     * @param restitution The restitution of the fixture. How much it bounces.
     */
    final void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short collisionMask, short collisionType) {

        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate

            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_METER;
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.maskBits = collisionMask;
        fixtureDef.filter.categoryBits = collisionType;

        body.createFixture(fixtureDef);

        polygon.dispose();
    }

    /**
     * Wraps the getX method from the Box2D body class.
     *
     * @return the x-coordinate of this body.
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Wraps the getY method from the Box2D body class.
     *
     * @return the y-coordinate of this body.
     */
    public float getY() {
        return body.getPosition().y;
    }
    
    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }

    /**
     * Wraps the setLinearVelocity method from the Box2D body class.
     *
     * @param x the new velocity in x axis
     * @param y the new velocity in y axis
     */
    public void setVelocity(float x, float y) {
        body.setLinearVelocity(x,y);
    }

    /**
     * Getter for body
     *
     * @return the body which is being wrapped.
     */
    public Body getBody() {
        return body;
    }
}
