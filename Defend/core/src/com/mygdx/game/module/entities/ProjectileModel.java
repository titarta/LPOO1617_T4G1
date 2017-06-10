package com.mygdx.game.module.entities;

import com.mygdx.game.GameLogic.Projectile;

/**
 * Class that stores the projectile information (to be the body user data).
 *
 * <p>
 *     Stores his position, rotation and stats.
 * </p>
 */
public class ProjectileModel extends EntityModel {

    /**
     * Store projectile damage and crit chance. Also have functions to get the damage.
     */
    private Projectile stats;

    /**
     * Creates the projectile model. Always set the position in the top of the tower.
     *
     * @param rotation Projectile angle of rotation.
     * @param stats Projectile damage and crit chance.
     */
    public ProjectileModel(float rotation, Projectile stats) {
        super(50, 200, rotation);
        this.stats = stats;
    }

    /**
     * Override of EntityModel getType.
     *
     * @return "Projectile".
     */
    @Override
    public String getType() {
        return "Projectile";
    }

    /**
     * Calculates the damage of the projectile, already "rolling the dice" and considering critical hit damage.
     *
     * @return Damage.
     */
    public int attacks() {
        return stats.calculatesDamage();
    }

    /**
     * Rotates the sprite of projectiles.
     */
    public void rotate() {
        setRotation(getRotation() - 0.2f);
    }
}
