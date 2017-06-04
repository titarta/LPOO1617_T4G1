package com.mygdx.game.GameLogic;

import Utils.Chance;

/**
 * Created by Tiago on 04/06/2017.
 */

public class Projectile {
    private int damage;
    private double critChance;
    private double critDamage;
    private Chance chance;

    public Projectile(int damage, double critChance, double critDamage) {
        this.damage = damage;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.chance = new Chance();
    }

    public int calculatesDamage() {
        if (chance.giveChance(critChance)) {
            return ((int) (damage * (2 + critDamage)));
        } else {
            return damage;
        }
    }
}
