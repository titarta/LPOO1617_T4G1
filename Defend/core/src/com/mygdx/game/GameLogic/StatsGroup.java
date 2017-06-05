package com.mygdx.game.GameLogic;

public class StatsGroup{
    private int hp;
    private int defense;
    private int damage;
    private double critChance;

    public StatsGroup(int hp, int defense, int damage, double critChance) {
        this.hp = hp;
        this.defense = defense;
        this.damage = damage;
        this.critChance = critChance;
    }

    public int getHp() {
        return hp;
    }

    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public void add(StatsGroup statsIncrease) {
        this.hp += statsIncrease.getHp();
        this.defense += statsIncrease.getDefense();
        this.damage += statsIncrease.getDamage();
        this.critChance += statsIncrease.getCritChance();
    }

    public void reset() {
        this.hp = 0;
        this.defense = 0;
        this.damage = 0;
        this.critChance = 0;
    }


}
