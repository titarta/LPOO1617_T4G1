package com.mygdx.game.module;

import com.mygdx.game.GameLogic.GameInfo;
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;
import com.mygdx.game.module.entities.FloorModel;
import com.mygdx.game.module.entities.ProjectileModel;
import com.mygdx.game.module.entities.TowerModel;

import java.util.ArrayList;

public class GameModel {

    private TowerModel tower;
    private FloorModel floor;
    private ArrayList<EnemyModel> enemies;
    private ArrayList<ProjectileModel> projectiles;

    public GameModel() {
        tower = new TowerModel();
        floor = new FloorModel();
        enemies = new ArrayList<EnemyModel>();
        projectiles = new ArrayList<ProjectileModel>();
    }

    public void addEnemyModel(EnemyModel e) {
        enemies.add(e);
    }

    public void addProjectileModel(ProjectileModel model) {
        projectiles.add(model);
    }

    public TowerModel getTower() {
        return tower;
    }

    public FloorModel getFloor() {
        return floor;
    }

    public ArrayList<EnemyModel> getEnemies() {
        return enemies;
    }

    public ArrayList<ProjectileModel> getProjectiles() {
        return projectiles;
    }

    public void deleteEnemyModel (EnemyModel e) {
        enemies.remove(e);
    }

    public void deleteProjectileModel (ProjectileModel p) {
        projectiles.remove(p);
    }

}
