package Utils;

/**
 * Created by Tiago on 03/06/2017.
 */

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
