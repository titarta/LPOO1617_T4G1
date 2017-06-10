package com.mygdx.game.Utils;

import java.util.Random;

public final class Chance {

    /**
     * Random values generator.
     */
    private static Random rand;


    /**
     * Initializes random;
     */
    public static void initialize() {
        rand = new Random();
    }

    /**
     * Function that returns true or false, given a chance. It is used to calculate whether an attack is critical strike or not.
     *
     * @param chance Probability of function returning true.
     * @return True or false.
     */
    public static boolean giveChance(double chance) {
        double value = rand.nextDouble() * 100;
        return chance >= value;
    }
}
