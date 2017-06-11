package com.mygdx.game.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Class that stores all the information needed to be stored, in order to keep the user progress.
 */
public class GameInfo implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * Tower stats.
     */
    public StatsGroup towerStats;
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
    private Preferences prefs;

    /**
     * Default constructor.
     */
    public GameInfo () {
        money = 0;
        hpEvNumber = 1;
        damageEvNumber = 1;
        critEvNumber = 1;
        defenseEvNumber = 1;
        towerStats = new StatsGroup(200, 0, 50, 0);
        initializePrefs();
    }

    /**
     * Initializes preferences.
     */
    private void initializePrefs() {

        prefs = Gdx.app.getPreferences("GameInfo");

        if (prefs.getBoolean("exists", false)) {
            readPrefs();
        } else {
            writePrefs();
        }


    }

    /**
     * Reads preferences file.
     */
    private void readPrefs() {
        money = prefs.getInteger("money");
        hpEvNumber = prefs.getInteger("hpEvNumber");
        damageEvNumber = prefs.getInteger("damageEvNumber");
        critEvNumber = prefs.getInteger("critEvNumber");
        defenseEvNumber = prefs.getInteger("defenseEvNumber");
        towerStats.setHp(prefs.getInteger("hp"));
        towerStats.setDefense(prefs.getInteger("defense"));
        towerStats.setDamage(prefs.getInteger("damage"));
        towerStats.setCritChance(prefs.getFloat("critChance"));
    }

    /**
     * Writes preferences file.
     */
    public void writePrefs() {
        prefs.putBoolean("exists", true);
        prefs.putInteger("money", money);
        prefs.putInteger("hpEvNumber", hpEvNumber);
        prefs.putInteger("damageEvNumber", damageEvNumber);
        prefs.putInteger("critEvNumber", critEvNumber);
        prefs.putInteger("defenseEvNumber", defenseEvNumber);
        prefs.putInteger("hp", towerStats.getHp());
        prefs.putInteger("defense", towerStats.getDefense());
        prefs.putInteger("damage", towerStats.getDamage());
        prefs.putFloat("critChance", (float) towerStats.getCritChance());
        prefs.flush();
    }

    /**
     * Resets preferences file.
     */
    public void resetPrefs() {
        prefs.putBoolean("exists", false);
    }

    /**
     * Getter for money.
     *
     * @return user money.
     */
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public void setHpEvNumber(int hpEvNumber) {
        this.hpEvNumber = hpEvNumber;
    }

    public int getDamageEvNumber() {
        return damageEvNumber;
    }

    public void setDamageEvNumber(int damageEvNumber) {
        this.damageEvNumber = damageEvNumber;
    }

    public int getCritEvNumber() {
        return critEvNumber;
    }

    public void setCritEvNumber(int critEvNumber) {
        this.critEvNumber = critEvNumber;
    }

    public int getDefenseEvNumber() {
        return defenseEvNumber;
    }

    public void setDefenseEvNumber(int defenseEvNumber) {
        this.defenseEvNumber = defenseEvNumber;
    }
}
