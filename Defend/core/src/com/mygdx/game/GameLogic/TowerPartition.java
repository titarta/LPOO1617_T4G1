package com.mygdx.game.GameLogic;

/**
 * Created by Tiago on 24/04/2017.
 */

public class TowerPartition {
    private int level;
    private StatsGroup stats;
    private StatsGroup statsIncrease;

    public void levelUp() {
        if (level >= 50) {
            return;
        }
        level++;
        stats.add(statsIncrease);
    }

    public TowerPartition(StatsGroup stats, StatsGroup statsIncrease) {
        this.level = 1;
        this.stats = stats;
        this.statsIncrease = statsIncrease;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public StatsGroup getStats() {
        return stats;
    }

    public void setStats(StatsGroup stats) {
        this.stats = stats;
    }

    public StatsGroup getStatsIncrease() {
        return statsIncrease;
    }

    public void setStatsIncrease(StatsGroup statsIncrease) {
        this.statsIncrease = statsIncrease;
    }
}
