package com.mygdx.game.GameLogic;

import java.util.ArrayList;

/**
 * Created by Tiago on 01/05/2017.
 */

public class EvolutionPartition {
    private int level;
    private boolean lock;
    private TowerPartition towerPart;
    private double costBegin;
    private double costIncreaseByLevel;
    private ArrayList<EvolutionPartition> unlocks;
    private ArrayList<EvolutionPartition> isUnlockedBy;

}
