package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.entities.EnemyBody;
import com.mygdx.game.controller.entities.FloorBody;
import com.mygdx.game.controller.entities.ProjectileBody;
import com.mygdx.game.module.GameModel;
import com.mygdx.game.module.entities.*;

import java.util.ArrayList;
import java.util.HashMap;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

/**
 * Created by Tiago on 28/05/2017.
 */

public class GameController {

    private final World world;
    private float accumulator;
    private ArrayList<EnemyBody> enemies;
    private ArrayList<ProjectileBody> projectiles;
    private HashMap<Body, EnemyBody> enemiesMap;
    private HashMap<Body, ProjectileBody> projectilesMap;
    private ArrayList<Body> bodiesToDestroy;

    public GameController(GameModel model) {
        world = new World(new Vector2(0, -9.8f), true);
        new FloorBody(world, model.getFloor());
        enemies = new ArrayList<EnemyBody>();
        projectiles = new ArrayList<ProjectileBody>();
        enemiesMap = new HashMap<Body, EnemyBody>();
        projectilesMap = new HashMap<Body, ProjectileBody>();
        bodiesToDestroy = new ArrayList<Body>();

    }

    public void update(float delta) {
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 4, 2);
            accumulator -= 1/60f;

        }
        for (Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        enemiesWalk();
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
        bodiesToDestroy.clear();
    }


    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    public void createEnemyBody(EnemyModel e) {
        EnemyBody enemyBody = new EnemyBody(world, e);
        enemies.add(enemyBody);
        enemiesMap.put(enemyBody.getBody(), enemyBody);
    }

    public void createProjectileBody(ProjectileModel p, float x, float y) {
        ProjectileBody pBody = new ProjectileBody(world, p);
        projectiles.add(pBody);
        projectilesMap.put(pBody.getBody(), pBody);
        pBody.setVelocity(x*0.04f, y*0.04f);

    }

    public void enemiesWalk() {
        for(EnemyBody e : enemies) {
            if (e.getX() <= 115*PIXEL_TO_METER) {
                e.setVelocity(0, 0);
            } else {
                e.setVelocity(-40*PIXEL_TO_METER, 0);
            }
        }
    }

    public void deleteEnemyBody(EnemyBody e) {
        bodiesToDestroy.add(e.getBody());
        enemies.remove(e);
        enemiesMap.remove(e.getBody());
    }

    public void deleteProjectileBody(ProjectileBody p) {
        bodiesToDestroy.add(p.getBody());
        //TODO tá a crashar aqui (linha 105)
        projectiles.remove(p);
        projectilesMap.remove(p.getBody());
    }

    public EnemyBody getEnemyBody(Body b) {
        return enemiesMap.get(b);
    }

    public ProjectileBody getProjectileBody(Body b) {
        return projectilesMap.get(b);
    }

}
