package com.mygdx.game.GameLogic;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Class that stores all the information needed to be stored, in order to keep the user progress.
 */
public class GameInfo implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * User money.
     */
    private int money;
    /**
     * Evolution phase on health upgrade.
     */
    private int hpEvNumber;
    /**
     * Evolution phase on damage upgrade.
     */
    private int damageEvNumber;
    /**
     * Evolution phase on critical chance upgrade.
     */
    private int critEvNumber;
    /**
     * Evolution phase on defense upgrade.
     */
    private int defenseEvNumber;
    /**
     * Tower stats.
     */
    public StatsGroup towerStats;

    /**
     * Default constructor. Called when starting a new game.
     */
    public GameInfo () {
        money = 0;
        hpEvNumber = 1;
        damageEvNumber = 1;
        critEvNumber = 1;
        defenseEvNumber = 1;
        towerStats = new StatsGroup(200, 0, 50, 0);
    }

    /**
     * Getter for money.
     *
     * @return user money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Decrease user money.
     *
     * @param cost money lost.
     */
    public void spendMoney(int cost) {
        money -= cost;
    }

    /**
     * Increase user money.
     *
     * @param money money earned.
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * Wrap function for method add from StatsGroup.
     *
     * @param s stats to add.
     */
    public void upgradeStat (StatsGroup s) {
        towerStats.add(s);
    }

    public int getHpEvNumber() {
        return hpEvNumber;
    }

    public int getDamageEvNumber() {
        return damageEvNumber;
    }

    public int getCritEvNumber() {
        return critEvNumber;
    }

    public int getDefenseEvNumber() {
        return defenseEvNumber;
    }

    public void setHpEvNumber(int hpEvNumber) {
        this.hpEvNumber = hpEvNumber;
    }

    public void setDamageEvNumber(int damageEvNumber) {
        this.damageEvNumber = damageEvNumber;
    }

    public void setCritEvNumber(int critEvNumber) {
        this.critEvNumber = critEvNumber;
    }

    public void setDefenseEvNumber(int defenseEvNumber) {
        this.defenseEvNumber = defenseEvNumber;
    }
}
