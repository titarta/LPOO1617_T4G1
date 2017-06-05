package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.Projectile;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.entities.*;
import com.mygdx.game.module.GameModel;
import com.mygdx.game.module.entities.*;
import com.mygdx.game.view.Screens.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import Utils.EnemyEntry;

/**
 * Created by Tiago on 04/05/2017.
 */

public class GameScreen extends ScreenMother {

    private ScreenMother returnScreen;
    public final static float PIXEL_TO_METER = 0.05f;
    private final DefendGame game;
    private final GameModel model;
    private final GameController controller;
    private TowerView towerView;
    private FloorView floorView;
    private EnemyView enemyView;
    private ProjectileView projectileView;
    private ArrayList<EnemyEntry> levelMap;
    private float timeElapsed;
    private int enemyIndex;
    private float deltaX;
    private float deltaY;
    private Projectile projectileDamage;
    private boolean endFlag;


    public GameScreen(DefendGame game, GameModel model, final GameController controller, ArrayList<EnemyEntry> level, ScreenMother returnScreen) {
        super(game);
        this.game = game;
        this.model = model;
        this.controller = controller;
        createContactListener();
        this.levelMap = level;
        this.timeElapsed = 0;
        this.enemyIndex = 0;
        this.deltaX = 0;
        this.deltaY = 0;
        this.endFlag = false;
        this.towerView = new TowerView(game);
        this.floorView = new FloorView(game);
        this.enemyView = new EnemyView(game);
        this.projectileView = new ProjectileView(game);
        this.returnScreen = returnScreen;
        stage.addListener(new ActorGestureListener() {

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startProjectilePath();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                endProjectilePath();
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                updateProjectilePath(deltaX, deltaY);
            }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor( 0.95f, 0.95f, 0.95f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        controller.update(delta);
        spawnEnemies(delta);

        game.batch.begin();
        drawEntities();
        game.batch.end();
    }

    private void drawEntities() {
        floorView.update(model.getFloor());
        floorView.draw(game.batch);
        towerView.update(model.getTower());
        towerView.draw(game.batch);
        for (EnemyModel e : model.getEnemies()) {
            enemyView.update(e);
            enemyView.draw(game.batch);
        }
        for (ProjectileModel p : model.getProjectiles()) {
            projectileView.update(p);
            projectileView.draw(game.batch);
        }
    }

    private void spawnEnemies(float delta) {
        if  (endFlag) {
            return;
        }
        timeElapsed += delta;
        if (timeElapsed < levelMap.get(enemyIndex).getTime()) {
            return;
        }
        addEnemy(levelMap.get(enemyIndex).getPower());
        enemyIndex++;
        if (enemyIndex == levelMap.size()) {
            endFlag = true;
        }
    }

    private void startProjectilePath() {
        deltaX = 0;
        deltaY = 0;
    }

    private void updateProjectilePath(float deltaX, float deltaY) {
        this.deltaX += deltaX;
        this.deltaY += deltaY;
    }

    private void endProjectilePath () {
        if (deltaY == 0 && deltaX == 0) {
            return;
        }
        ProjectileModel p = new ProjectileModel(((float) (Math.atan2(-deltaY, -deltaX))), new Projectile(50, 50, 0));
        model.addProjectileModel(p);
        controller.createProjectileBody(p, -deltaX, -deltaY);
    }

    private void addEnemy(int power) {
        EnemyModel e = new EnemyModel(power);
        model.addEnemyModel(e);
        controller.createEnemyBody(e);
    }

    private void createContactListener() {
        controller.getWorld().setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                EntityModel a = ((EntityModel) contact.getFixtureA().getBody().getUserData());
                EntityModel b = ((EntityModel) contact.getFixtureB().getBody().getUserData());

                if (a.getType() == b.getType()) {
                    return;
                }
                if (a.getType() == "Floor" && b.getType() == "Enemy") {
                    return;
                }
                if (a.getType() == "Projectile" && b.getType() == "Enemy") {
                    if(((EnemyModel) b).getAttacked(((ProjectileModel) a).attacks())) {
                        eraseEnemyEntity(controller.getEnemyBody(contact.getFixtureB().getBody()));
                    }
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureA().getBody()));
                    return;
                }
                if (a.getType() == "Enemy" && b.getType() == "Projectile") {
                    if(((EnemyModel) a).getAttacked(((ProjectileModel) b).attacks())) {
                        eraseEnemyEntity(controller.getEnemyBody(contact.getFixtureA().getBody()));
                    }
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureB().getBody()));
                    return;
                }
                if (b.getType() == "Projectile") {
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureB().getBody()));
                }
                if (a.getType() == "Projectile") {
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureA().getBody()));
                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    private void eraseEnemyEntity(EnemyBody ent) {
        controller.deleteEnemyBody(ent);
        model.deleteEnemyModel(((EnemyModel) ent.getUserData()));
    }

    private void eraseProjectileEntity(ProjectileBody ent) {
        controller.deleteProjectileBody(ent);
        model.deleteProjectileModel(((ProjectileModel) ent.getUserData()));
    }

    private void endGame() {

    }
}
