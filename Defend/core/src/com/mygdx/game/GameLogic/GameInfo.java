package com.mygdx.game.GameLogic;

import java.util.ArrayList;

/**
 * Created by Tiago on 01/05/2017.
 */

public class GameInfo {
    private int money;
    private Tower tower;
    private EvolutionTree weaponEvolutionTree;
    private EvolutionTree divinityEvolutionTree;
    private EvolutionTree shieldEvolutionTree;
    private EvolutionTree architectureEvolutionTree;
    private ArrayList<Level> levels;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public EvolutionTree getWeaponEvolutionTree() {
        return weaponEvolutionTree;
    }

    public void setWeaponEvolutionTree(EvolutionTree weaponEvolutionTree) {
        this.weaponEvolutionTree = weaponEvolutionTree;
    }

    public EvolutionTree getDivinityEvolutionTree() {
        return divinityEvolutionTree;
    }

    public void setDivinityEvolutionTree(EvolutionTree divinityEvolutionTree) {
        this.divinityEvolutionTree = divinityEvolutionTree;
    }

    public EvolutionTree getShieldEvolutionTree() {
        return shieldEvolutionTree;
    }

    public void setShieldEvolutionTree(EvolutionTree shieldEvolutionTree) {
        this.shieldEvolutionTree = shieldEvolutionTree;
    }

    public EvolutionTree getArchitectureEvolutionTree() {
        return architectureEvolutionTree;
    }

    public void setArchitectureEvolutionTree(EvolutionTree architectureEvolutionTree) {
        this.architectureEvolutionTree = architectureEvolutionTree;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
}
