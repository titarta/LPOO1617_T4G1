package com.mygdx.game.GameLogic;

import java.util.ArrayList;
import java.io.Serializable;

public class GameInfo implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private int money;
    private int hpEvNumber;
    private int damageEvNumber;
    private int critEvNumber;
    private int defenseEvNumber;
    public StatsGroup towerStats;

    public GameInfo () {
        money = 0;
        hpEvNumber = 1;
        damageEvNumber = 1;
        critEvNumber = 1;
        defenseEvNumber = 1;
        towerStats = new StatsGroup(200, 0, 50, 0);
    }

    public int getMoney() {
        return money;
    }

    public void spendMoney(int cost) {
        money -= cost;
    }

    public void addMoney(int money) {
        this.money += money;
    }

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
