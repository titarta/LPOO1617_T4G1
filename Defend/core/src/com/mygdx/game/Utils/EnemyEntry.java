package com.mygdx.game.Utils;

public class EnemyEntry {
    private float time;
    private int power;

    public EnemyEntry(float time, int power) {
        this.time = time;
        this.power = power;
    }

    public float getTime() {
        return time;
    }

    public int getPower() {
        return power;
    }
}
