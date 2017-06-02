package com.mygdx.game.GameLogic;

public class Tower {
    private StatsGroup stats;
    private TowerPartition weapon;
    private TowerPartition divinity;
    private TowerPartition shield;
    private TowerPartition architecture;

    public Tower() {
        //TODO
    }

    public void updateStats() {
        stats.reset();
        stats.add(weapon.getStats());
        stats.add(divinity.getStats());
        stats.add(shield.getStats());
        stats.add(architecture.getStats());
    }
}
