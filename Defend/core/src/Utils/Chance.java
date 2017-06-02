package Utils;

import java.util.Random;

public class Chance {
    private Random rand;

    public Chance() {
        rand = new Random();
    }

    public boolean giveChance(double chance) {
        double value = rand.nextDouble() * 100;
        return chance >= value;
    }
}
