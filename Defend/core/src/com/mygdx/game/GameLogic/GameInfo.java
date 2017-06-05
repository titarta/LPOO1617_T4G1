package com.mygdx.game.GameLogic;

import java.util.ArrayList;

/**
 * Created by Tiago on 01/05/2017.
 */

public class GameInfo implements java.io.Serializable{
    private int money;
    private StatsGroup towerStats;

    public GameInfo () {
        money = 0;
        towerStats = new StatsGroup(200, 0, 50, 0);
    }

    public int getMoney() {
        return money;
    }

    public void spendMoney(int cost) {
        money -= cost;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void upgradeStat (StatsGroup s) {
        towerStats.add(s);
    }



}
