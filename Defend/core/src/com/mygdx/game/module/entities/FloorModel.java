package com.mygdx.game.module.entities;

/**
 * Created by Tiago on 28/05/2017.
 */

public class FloorModel extends EntityModel {

    public FloorModel() {
        super(450, 10, 0);
    }

    @Override
    public String getType() {
        return "Floor";
    }
}
