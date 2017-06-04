package com.mygdx.game.GameLogic;

public class StatsGroup{
    private int hp;
    private int defense;
    private int damage;
    private double critChance;
    private double critDamage;

    public StatsGroup(int hp, int defense, int damage, double critChance, double critDamage, double cd) {
        this.hp = hp;
        this.defense = defense;
        this.damage = damage;
        this.critChance = critChance;
        this.critDamage = critDamage;
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

    public double getCritDamage() {
        return critDamage;
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

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    public void add(StatsGroup statsIncrease) {
        this.hp += statsIncrease.getHp();
        this.defense += statsIncrease.getDefense();
        this.damage += statsIncrease.getDamage();
        this.critChance += statsIncrease.getCritChance();
        this.critDamage += statsIncrease.getCritDamage();
    }

    public void reset() {
        this.hp = 0;
        this.defense = 0;
        this.damage = 0;
        this.critChance = 0;
        this.critDamage = 0;
    }


}
