package com.mygdx.game.GameLogic;

import Utils.Chance;

public class Projectile {
    private int damage;
    private double critChance;
    private Chance chance;

    public Projectile(int damage, double critChance) {
        this.damage = damage;
        this.critChance = critChance;
        this.chance = new Chance();
    }

    public int calculatesDamage() {
        if (chance.giveChance(critChance)) {
            return damage * 2;
        } else {
            return damage;
        }
    }
}
