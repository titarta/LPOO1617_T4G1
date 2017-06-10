package com.mygdx.game.module.entities;

/**
 * Class that stores the floor position.
 */
public class FloorModel extends EntityModel {

    /**
     * Constructor. Sets it position in the bottom of the screen.
     */
    public FloorModel() {
        super(450, 10, 0);
    }

    /**
     * Override of EntityModel getType.
     *
     * @return "Floor".
     */
    @Override
    public String getType() {
        return "Floor";
    }
}
