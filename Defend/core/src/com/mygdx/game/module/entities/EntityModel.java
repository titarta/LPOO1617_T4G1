package com.mygdx.game.module.entities;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

/**
 * Class that stores enemy information.
 *
 * <p>
 *     Stores position, rotation and, in some cases, stats.
 * </p>
 */

public abstract class EntityModel {
    /**
     * The x-coordinate of this model in meters.
     */
    private float x;

    /**
     * The y-coordinate of this model in meters.
     */
    private float y;

    /**
     * The current rotation of this model in radians.
     */
    private float rotation;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    EntityModel(float x, float y, float rotation) {
        this.x = x*PIXEL_TO_METER;
        this.y = y*PIXEL_TO_METER;
        this.rotation = rotation;
    }

    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the rotation of this entity.
     *
     * @return The rotation of this entity in radians.
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Sets the position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets y position of this entity.
     *
     * @param y The y-coordinate of this entity in meters.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Sets x position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     */
    public void setX(float x) { this.x = x; }

    /**
     * Sets the rotation of this entity.
     *
     * @param rotation The current rotation of this entity in radians.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Function that returns the type of entity, in string format. Used to avoid using instanceof.
     *
     * @return String equivalent to entity type name.
     */
    public String getType() { return "";}
}