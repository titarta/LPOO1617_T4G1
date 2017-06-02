package com.mygdx.game.GameLogic;

public class Enemy {
        private int hp;
    private int damage;

    public Enemy(int power) {
        hp = 100*(power + 2);
        damage = 10*(power + 2);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
