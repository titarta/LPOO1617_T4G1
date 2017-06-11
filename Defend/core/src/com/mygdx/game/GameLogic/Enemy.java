package com.mygdx.game.GameLogic;

/**
 * Class that represents an enemy.
 *
 * <p>
 *     Stores his health and damage.
 * </p>
 */
public class Enemy {
    /**
     * Enemy hit points.
     */
    private int hp;
    /**
     * Enemy damage.
     */
    private int damage;

    /**
     * Enemy money drop.
     */
    private int money;

    /**
     * Constructor that calculates enemy health and damage based on one integer.
     *
     * @param power enemy power level (his stats are based on it).
     */
    public Enemy(int power) {
        hp = 100*(power);
        damage = 10*(power + 2);
        money = 40*power;
    }

    /**
     * Getter for hp.
     *
     * @return hit points.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Setter for hp.
     *
     * @param hp hit points.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Simulates the enemy received by an enemy.
     *
     * @param damage damage taken.
     * @return whether or not the enemy died.
     */
    public boolean getsAttacked (int damage) {
        this.hp -= damage;
        return (this.hp <= 0);
    }

    /**
     * Getter for money.
     *
     * @return Money that enemy drops.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Getter for enemy damage.
     *
     * @return enemy Damage.
     */
    public int getDamage() {
        return damage;
    }

}
