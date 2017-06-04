package com.mygdx.game.module.entities;

import com.mygdx.game.GameLogic.Enemy;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

/**
 * Created by Tiago on 28/05/2017.
 */

public class EnemyModel extends EntityModel {

    private Enemy enemyStats;

    public EnemyModel(int power) {
        super(700, 35, 0);
        enemyStats = new Enemy(power);
    }

    @Override
    public String getType() {
        return "Enemy";
    }

    public boolean getAttacked(int damage) {
        return enemyStats.getsAttacked(damage);
    }
}
