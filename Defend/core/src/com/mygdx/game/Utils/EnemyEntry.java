package com.mygdx.game.Utils;

/**
 * Class that stores information relative to a mob spawn.
 *
 * <p>
 *     It associates a enemy power to a certain time.
 *     In the level, when that time passes, it is generated an enemy with that power.
 * </p>
 */
public class EnemyEntry {

    /**
     * Time, in seconds, when the enemy will spawn.
     */
    private float time;

    /**
     * Power of the enemy that will spawn.
     */
    private int power;

    /**
     * Constructor.
     *
     * @param time Time of spawn.
     * @param power Enemy power.
     */
    public EnemyEntry(float time, int power) {
        this.time = time;
        this.power = power;
    }

    public float getTime() {
        return time;
    }

    public int getPower() {
        return power;
    }
}
