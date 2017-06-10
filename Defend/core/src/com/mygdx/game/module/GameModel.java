package com.mygdx.game.module;

import com.mygdx.game.GameLogic.GameInfo;
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;
import com.mygdx.game.module.entities.FloorModel;
import com.mygdx.game.module.entities.ProjectileModel;
import com.mygdx.game.module.entities.TowerModel;

import java.util.ArrayList;

/**
 * Class that stores all game models.
 */
public class GameModel {

    /**
     * Tower model.
     */
    private TowerModel tower;

    /**
     * Floor model.
     */
    private FloorModel floor;

    /**
     * ArrayList of enemy models.
     */
    private ArrayList<EnemyModel> enemies;

    /**
     * ArrayList of projectile models.
     */
    private ArrayList<ProjectileModel> projectiles;

    /**
     * Creates the game model and initializes all his attributes.
     */
    public GameModel() {
        tower = new TowerModel();
        floor = new FloorModel();
        enemies = new ArrayList<EnemyModel>();
        projectiles = new ArrayList<ProjectileModel>();
    }

    /**
     * Adds an enemy model to the array.
     *
     * @param e EnemyModel to add.
     */
    public void addEnemyModel(EnemyModel e) {
        enemies.add(e);
    }

    /**
     * Adds a projectile model to the array.
     *
     * @param model ProjectileModel to add.
     */
    public void addProjectileModel(ProjectileModel model) {
        projectiles.add(model);
    }

    /**
     * Getter for the tower model.
     *
     * @return Tower model.
     */
    public TowerModel getTower() {
        return tower;
    }

    /**
     * Getter for the floor model.
     *
     * @return Floor model.
     */
    public FloorModel getFloor() {
        return floor;
    }

    /**
     * Getter for ArrayList of EnemyModel.
     *
     * @return Enemies models.
     */
    public ArrayList<EnemyModel> getEnemies() {
        return enemies;
    }

    /**
     * Getter for ArrayList of ProjectileModel.
     *
     * @return Projectile models.
     */
    public ArrayList<ProjectileModel> getProjectiles() {
        return projectiles;
    }

    /**
     * Deletes an EnemyModel from the arraylist.
     *
     * @param e EnemyModel to remove.
     */
    public void deleteEnemyModel (EnemyModel e) {
        enemies.remove(e);
    }

    /**
     * Deletes an ProjectileModel from the arraylist.
     *
     * @param p ProjectileModel to remove.
     */
    public void deleteProjectileModel (ProjectileModel p) {
        projectiles.remove(p);
    }

}
