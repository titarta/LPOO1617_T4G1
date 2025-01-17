package com.mygdx.game.GameLogic;

import com.mygdx.game.Utils.Chance;

/**
 * Class responsible for storing projectile damage and crit damage.
 */
public class Projectile {

    /**
     * Projectile damage.
     */
    private final int damage;

    /**
     * Whether or not the projectile is going to be a critical strike.
     */
    private final boolean crit;

    /**
     * Constructor. Initialize attributes.
     *
     * @param damage Projectile damage.
     * @param critChance Critical strike chance.
     */
    public Projectile(int damage, double critChance) {
        this.damage = damage;
        this.crit = Chance.giveChance(critChance);
    }

    /**
     * Getter for crit.
     *
     * @return whether or not the projectile is going to critical strike an enemy.
     */
    public boolean isCrit() {
        return crit;
    }

    /**
     * Calculates the damage the projectile will give (already with the crit damage).
     *
     * @return Projectile actual damage.
     */
    public int calculatesDamage() {
        if (this.crit) {
            return damage * 2;
        } else {
            return damage;
        }
    }
}
