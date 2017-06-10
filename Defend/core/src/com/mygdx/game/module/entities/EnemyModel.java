package com.mygdx.game.module.entities;

import com.mygdx.game.GameLogic.Enemy;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

/**
 * Class that stores the enemy information (to be the body user data).
 *
 * <p>
 *     Stores his position, rotation and stats.
 * </p>
 */
public class EnemyModel extends EntityModel {

    /**
     * Store enemy hp and damage. Also have functions to change or get this two values.
     */
    private Enemy enemyStats;

    /**
     * Creates an enemy model. Set the position of the enemy always out of visible screen.
     *
     * @param power Stat that decides enemy hp and damage.
     */
    public EnemyModel(int power) {
        super(700, 35, 0);
        enemyStats = new Enemy(power);
    }

    /**
     * Function to avoid using instanceof.
     *
     * Override of EntityModel getType.
     *
     * @return "Enemy".
     */
    @Override
    public String getType() {
        return "Enemy";
    }

    /**
     * Getter for enemy damage.
     *
     * @return Enemy damage.
     */
    public int getDamage() {
        return enemyStats.getDamage();
    }

    /**
     * Returns enemy money drop.
     *
     * @return Money the enemy gives.
     */
    public int getMoney() {
        return enemyStats.getMoney();
    }

    /**
     * Subtracts value to enemy health.
     *
     * @param damage Value to be subtracted.
     * @return Whether enemy dies or not.
     */
    public boolean getAttacked(int damage) {
        return enemyStats.getsAttacked(damage);
    }
}
