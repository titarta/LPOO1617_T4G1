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
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;
import com.mygdx.game.module.entities.ProjectileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static com.mygdx.game.view.Screens.GameScreen.PIXEL_TO_METER;

/**
 * Class Responsible for updating the physical world.
 *
 * <p>
 *     It is responsible for controlling the bodies in the world, updating the corresponded models.
 *     It is responsible for deleting bodies from the world, whether because stepping out of bounds, whether by certain contacts.
 * </p>
 * <p>
 *     It checks if the game has ended and also moves the enemies and make them attack the tower.
 * </p>
 */
public class GameController {

    /**
     * World which is being controlled by this class.
     */
    private final World world;
    /**
     * Variable used to keep the frame rate on 60 fps maximum.
     */
    private float accumulator;
    /**
     * ArrayList of EnemyBody.
     *
     * <p>
     *     Used to make enemies walk and attack.
     * </p>
     */
    private ArrayList<EnemyBody> enemies;
    /**
     * Map used to get the Wrap class (EnemyBody) given the body.
     */
    private HashMap<Body, EnemyBody> enemiesMap;
    /**
     * Map used to get the Wrap class (ProjectileBody) given the body.
     */
    private HashMap<Body, ProjectileBody> projectilesMap;
    /**
     * HashSet of bodies in need to be destroyed.
     */
    private HashSet<Body> bodiesToDestroy;

    /**
     * Constructor of the class.
     *
     * <p>
     *     Creates the world with a gravitic acceleration of -9.8m/s^2.
     * </p>
     *
     * @param model GameModel
     */
    public GameController(GameModel model) {
        world = new World(new Vector2(0, -9.8f), true);
        new FloorBody(world, model.getFloor());
        enemies = new ArrayList<EnemyBody>();
        enemiesMap = new HashMap<Body, EnemyBody>();
        projectilesMap = new HashMap<Body, ProjectileBody>();
        bodiesToDestroy = new HashSet<Body>();
    }

    /**
     * Updates the world.
     *
     * @param delta time passed from previous render.
     * @param endFlag flag telling whether or not enemies stop spawning.
     * @return end or not of the game(current level)
     */
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
            verifyBounds(body);
        }
        return false;
    }

    /**
     * Checks if a body got out of world limits, and destroy it.
     *
     * @param body body to be checked.
     */
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

    /**
     * Creates a new Projectile in the world with a initial velocity.
     *
     * @param p model to be userData of the body.
     * @param x distance in x the player made with finger or mouse before unleashing the projectile.
     * @param y distance in y the player made with finger or mouse before unleashing the projectile.
     */
    public void createProjectileBody(ProjectileModel p, float x, float y) {
        ProjectileBody pBody = new ProjectileBody(world, p);
        projectilesMap.put(pBody.getBody(), pBody);
        pBody.setVelocity(x*0.04f, y*0.04f);
    }

    /**
     * Make enemies go forward until they reach the tower.
     */
    private void enemiesWalk() {
        for(EnemyBody e : enemies) {
            if (e.getX() <= 90*PIXEL_TO_METER) {
                e.setVelocity(0, 0);
            } else {
                e.setVelocity(-40*PIXEL_TO_METER, 0);
            }
        }
    }

    /**
     * Delete a enemy body frome the world.
     *
     * @param e Wrap class of the body.
     */
    public void deleteEnemyBody(EnemyBody e) {
        bodiesToDestroy.add(e.getBody());
        enemies.remove(e);
        enemiesMap.remove(e.getBody());
    }

    /**
     * Delete a projectile body frome the world.
     *
     * @param p Wrap class of the body.
     */
    public void deleteProjectileBody(ProjectileBody p) {
        bodiesToDestroy.add(p.getBody());
        projectilesMap.remove(p.getBody());
    }

    /**
     * Gets the Wrap class given an enemy body.
     *
     * @param b body
     * @return the wrap class of b.
     */
    public EnemyBody getEnemyBody(Body b) {
        return enemiesMap.get(b);
    }

    /**
     * Gets the Wrap class given an projectile body.
     *
     * @param b body
     * @return the wrap class of b.
     */
    public ProjectileBody getProjectileBody(Body b) {
        return projectilesMap.get(b);
    }

    /**
     * Make enemies attack the tower, at ticks of 1 second.
     *
     * @param hp tower hp before attack.
     * @param defense tower defense.
     * @return tower new hp.
     */
    public int enemiesAtack(int hp, int defense) {
        for (EnemyBody e : enemies) {
            if (((EnemyModel) e.getUserData()).getX() >= 130*PIXEL_TO_METER) {
                continue;
            }
            int aux = ((EnemyModel) e.getUserData()).getDamage() - defense;
            if (aux > 0) {
                hp -= aux;
                Gdx.input.vibrate(250);
            }
        }
        return hp;
    }
}
