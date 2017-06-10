package com.mygdx.game.GameLogic;

import com.mygdx.game.Utils.Chance;

/**
 * Class responsible for storing projectile damage and crit damage.
 */
public class Projectile {

    /**
     * Projectile damage.
     */
    private int damage;

    /**
     * Critical strike chance of projectile.
     */
    private double critChance;

    /**
     * Instance of chance class (used to calculate whether the projectile will crit or not).
     */
    private Chance chance;

    /**
     * Constructor. Initialize attributes.
     *
     * @param damage Projectile damage.
     * @param critChance Critical strike chance.
     */
    public Projectile(int damage, double critChance) {
        this.damage = damage;
        this.critChance = critChance;
        this.chance = new Chance();
    }

    /**
     * Calculates the damage the projectile will give (already with the crit damage).
     *
     * @return Projectile actual damage.
     */
    public int calculatesDamage() {
        if (chance.giveChance(critChance)) {
            return damage * 2;
        } else {
            return damage;
        }
    }
}
