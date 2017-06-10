package com.mygdx.game.GameLogic;

import com.mygdx.game.Utils.Chance;

public class Projectile {
    private final int damage;
    private final boolean crit;

    public Projectile(int damage, double critChance) {
        this.damage = damage;
        this.crit = Chance.giveChance(critChance);
    }

    public boolean isCrit() {
        return crit;
    }

    public int calculatesDamage() {
        if (this.crit) {
            return damage * 2;
        } else {
            return damage;
        }
    }
}
