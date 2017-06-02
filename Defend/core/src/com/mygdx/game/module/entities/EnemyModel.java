package com.mygdx.game.module.entities;

import com.mygdx.game.GameLogic.Enemy;

/**
 * Created by Tiago on 28/05/2017.
 */

public class EnemyModel extends EntityModel {

    private Enemy enemyStats;

    public EnemyModel(int power) {
        super(700, 20, 0);
        enemyStats = new Enemy(power);
    }

    public void step() {
        if (getX() < 100) {
            return;
        }
        setX(getX() - 1);
    }
}
