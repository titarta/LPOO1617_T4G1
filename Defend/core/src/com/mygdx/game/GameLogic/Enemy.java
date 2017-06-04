package com.mygdx.game.GameLogic;

public class Enemy {
    private int hp;
    private int damage;

    public Enemy(int power) {
        hp = 100*(power);
        damage = 10*(power + 2);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean getsAttacked (int damage) {
        this.hp -= damage;
        return (this.hp <= 0);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
