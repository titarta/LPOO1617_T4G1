package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameLogic.GameInfo;
import com.mygdx.game.GameLogic.StatsGroup;
import com.mygdx.game.controller.entities.EnemyBody;
import com.mygdx.game.controller.entities.FloorBody;
import com.mygdx.game.controller.entities.ProjectileBody;
import com.mygdx.game.module.GameModel;
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;
import com.mygdx.game.module.entities.ProjectileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

public class GameController {

    private final World world;
    private float accumulator;
    private ArrayList<EnemyBody> enemies;
    private ArrayList<ProjectileBody> projectiles;
    private HashMap<Body, EnemyBody> enemiesMap;
    private HashMap<Body, ProjectileBody> projectilesMap;
    private HashSet<Body> bodiesToDestroy;

    public GameController(GameModel model) {
        world = new World(new Vector2(0, -9.8f), true);
        new FloorBody(world, model.getFloor());
        enemies = new ArrayList<EnemyBody>();
        projectiles = new ArrayList<ProjectileBody>();
        enemiesMap = new HashMap<Body, EnemyBody>();
        projectilesMap = new HashMap<Body, ProjectileBody>();
        bodiesToDestroy = new HashSet<Body>();
    }

    public boolean update(float delta, boolean endFlag) {
        float frameTime = Math.min(delta, 0.25f);

        accumulator += frameTime;

        while (accumulator >= 1/60f) {
            world.step(1/60f, 4, 2);
            accumulator -= 1/60f;

        }
        for (Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();

        enemiesWalk();

        Array<Body> bodies = new Array<Body>();

        world.getBodies(bodies);

        if (bodies.size == 1 && endFlag) {
            return true;
        }
        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
            verifyBounds(body);
        }

        return false;

    }

    private void verifyBounds(Body body) {
        if (body.getPosition().y < 0)
            world.destroyBody(body);
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

    private void enemiesWalk() {
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
        projectiles.remove(p);
        projectilesMap.remove(p.getBody());
    }

    public EnemyBody getEnemyBody(Body b) {
        return enemiesMap.get(b);
    }

    public ProjectileBody getProjectileBody(Body b) {
        return projectilesMap.get(b);
    }

    public int enemiesAtack(int hp, int defense) {
        for (EnemyBody e : enemies) {
            if (((EnemyModel) e.getUserData()).getX() >= 130*PIXEL_TO_METER) {
                continue;
            }
            int aux = ((EnemyModel) e.getUserData()).getDamage() - defense;
            if (aux > 0) {
                System.out.println(aux);
                hp -= aux;
            }
        }
        return hp;
    }
}
