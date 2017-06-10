package com.mygdx.game.Utils;

import java.util.Random;

public class Chance {

    /**
     * Random values generator.
     */
    private Random rand;

    /**
     * Constructor. Initializes Random.
     */
    public Chance() {
        rand = new Random();
    }

    /**
     * Function that returns true or false, given a chance. It is used to calculate whether an attack is critical strike or not.
     *
     * @param chance Porbability of function returning true.
     * @return True or false.
     */
    public boolean giveChance(double chance) {
        double value = rand.nextDouble() * 100;
        return chance >= value;
    }
}
