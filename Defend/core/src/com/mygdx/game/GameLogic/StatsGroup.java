package com.mygdx.game.GameLogic;

/**
 * Class that stores staistic information relative to a object (only tower).
 */
public class StatsGroup implements  java.io.Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * Hit points.
     */
    private int hp;
    /**
     * Defense which subtracts damage each hit.
     */
    private int defense;
    /**
     * Damage given.
     */
    private int damage;
    /**
     * Chance of giving twice the damage.
     */
    private double critChance;

    /**
     * Constructor of StatsGroup.
     *
     * @param hp hp of group.
     * @param defense defense of group.
     * @param damage damage of group.
     * @param critChance crit chance of group.
     */
    public StatsGroup(int hp, int defense, int damage, double critChance) {
        this.hp = hp;
        this.defense = defense;
        this.damage = damage;
        this.critChance = critChance;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    /**
     * Add the stats passed as parameter to our StatsGroup.
     * @param statsIncrease stats to add.
     */
    public void add(StatsGroup statsIncrease) {
        this.hp += statsIncrease.getHp();
        this.defense += statsIncrease.getDefense();
        this.damage += statsIncrease.getDamage();
        this.critChance += statsIncrease.getCritChance();
    }
}
