package com.mygdx.game.module.entities;

import com.mygdx.game.GameLogic.Projectile;

/**
 * Created by Tiago on 28/05/2017.
 */

public class ProjectileModel extends EntityModel {

    private Projectile stats;

    public ProjectileModel(float rotation, Projectile stats) {
        super(50, 200, rotation);
        this.stats = stats;
    }

    @Override
    public String getType() {
        return "Projectile";
    }

    public int attacks() {
        return stats.calculatesDamage();
    }
}
