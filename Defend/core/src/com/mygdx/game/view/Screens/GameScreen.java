package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.Projectile;
import com.mygdx.game.Utils.EnemyEntry;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.entities.EnemyBody;
import com.mygdx.game.controller.entities.ProjectileBody;
import com.mygdx.game.module.GameModel;
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;
import com.mygdx.game.module.entities.ProjectileModel;
import com.mygdx.game.view.Screens.entities.EnemyView;
import com.mygdx.game.view.Screens.entities.FloorView;
import com.mygdx.game.view.Screens.entities.ProjectileView;
import com.mygdx.game.view.Screens.entities.TowerView;

import java.util.ArrayList;

/**
 * Actual game screen.
 *
 * <p>
 *     Screen that handles the levels.
 * </p>
 */
public class GameScreen extends ScreenMother {

    /**
     * Pixel to meter ratio. Used to unlimit world velocity (it is limited to 120 units per second)
     */
    public final static float PIXEL_TO_METER = 0.05f;

    /**
     * Instance of game.
     */
    private final DefendGame game;

    /**
     * Game model, where most of the info is stored
     */
    private GameModel model;

    /**
     * Controller which creates and updates a physic world.
     */
    private GameController controller;

    /**
     * Screen that calls the game.
     */
    private ScreenMother returnScreen;

    /**
     * Tower sprite wrapping class.
     */
    private TowerView towerView;

    /**
     * Floor sprite wrapping class.
     */
    private FloorView floorView;

    /**
     * Enemy sprite wrapping class.
     */
    private EnemyView enemyView;

    /**
     * Projectile sprite wrapping class.
     */
    private ProjectileView projectileView;

    /**
     * Enemies spawn time and power.
     */
    private ArrayList<EnemyEntry> levelMap;

    /**
     * Time elapsed since the beginning of the game.
     */
    private float timeElapsed;

    /**
     * Time elapsed in integer since the beginning of the game.
     */
    private int timeElapsedInt;

    /**
     * Enemy to be spawned next.
     */
    private int enemyIndex;

    /**
     * Value which stores the x-value of the movement made by the user.
     */
    private float deltaX;

    /**
     * Value which stores the y-value of the movement made by the user.
     */
    private float deltaY;

    /**
     * Flag that represents whether or not there are not any more enemies to spwn
     */
    private boolean endFlag;

    /**
     * Tower defense.
     */
    private int defense;

    /**
     * Tower hp.
     */
    private int hp;

    /**
     * Creates the screen of a game.
     *
     * @param game Instance of game.
     * @param level Enemies spawn time and power.
     * @param returnScreen Screen to go back when game ends.
     */
    GameScreen(DefendGame game, ArrayList<EnemyEntry> level, ScreenMother returnScreen) {
        super(game);
        this.game = game;
        this.levelMap = level;
        reset();
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

    /**
     * Overrides render method from ScreenMother.
     *
     * @param delta Time passed since last render.
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor( 0.95f, 0.95f, 0.95f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        if (controller.update(delta, endFlag)) {
            endGame();
        }

        spawnEnemies(delta);
        checkEnemiesDamage();

        game.batch.begin();
        drawEntities();
        game.batch.end();
    }

    /**
     * Make enemies attack at each second.
     */
    private void checkEnemiesDamage() {
        if (timeElapsed < timeElapsedInt + 1) {
            return;
        }
        timeElapsedInt++;
        hp = controller.enemiesAtack(hp, defense);
        if (hp <= 0) {
            endGame();
        }
    }

    /**
     * Draws all the entities.
     */
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
            p.rotate();
            projectileView.update(p);
            projectileView.draw(game.batch);
        }
    }

    /**
     * Handles enemy spawns using levelMap as reference.
     *
     * @param delta Time passed since last render.
     */
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

    /**
     * Used when user clicks or press the screen. It updates deltaX and deltaY to 0.
     */
    private void startProjectilePath() {
        deltaX = 0;
        deltaY = 0;
    }

    /**
     * Used when a pan is detected. Updates deltaY and deltaX based on the pan.
     *
     * @param deltaX Distance covered in x-axis by the pan.
     * @param deltaY Distance covered in y-axis by the pan.
     */
    private void updateProjectilePath(float deltaX, float deltaY) {
        this.deltaX += deltaX;
        this.deltaY += deltaY;
    }

    /**
     * Creates the projectile based on deltaX and deltaY values.
     */
    private void endProjectilePath () {
        if (deltaY == 0 && deltaX == 0) {
            return;
        }
        ProjectileModel p = new ProjectileModel(((float) (Math.atan2(-deltaY, -deltaX))), new Projectile(game.gameInfo.towerStats.getDamage(), game.gameInfo.towerStats.getCritChance()));
        model.addProjectileModel(p);
        controller.createProjectileBody(p, -deltaX, -deltaY);
    }

    /**
     * Spawns an enemy in the game.
     *
     * @param power Enemy power.
     */
    private void addEnemy(int power) {
        EnemyModel e = new EnemyModel(power);
        model.addEnemyModel(e);
        controller.createEnemyBody(e);
    }

    /**
     * Contact Listener ti check collision between bodies.
     */
    private void createContactListener() {
        controller.getWorld().setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                EntityModel a = ((EntityModel) contact.getFixtureA().getBody().getUserData());
                EntityModel b = ((EntityModel) contact.getFixtureB().getBody().getUserData());

                if (a.getType().equals(b.getType())) {
                    return;
                }
                if (a.getType().equals("Floor") && b.getType().equals("Enemy")) {
                    return;
                }
                if (a.getType().equals("Projectile") && b.getType().equals("Enemy")) {
                    if (((ProjectileModel) a).isCrit()) {
                        System.out.println("I critted!");
                    }
                    if (((EnemyModel) b).getAttacked(((ProjectileModel) a).attacks())) {
                        eraseEnemyEntity(controller.getEnemyBody(contact.getFixtureB().getBody()));
                    }
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureA().getBody()));
                    return;
                }
                if (a.getType().equals("Enemy") && b.getType().equals("Projectile")) {
                    if(((EnemyModel) a).getAttacked(((ProjectileModel) b).attacks())) {
                        eraseEnemyEntity(controller.getEnemyBody(contact.getFixtureA().getBody()));
                    }
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureB().getBody()));
                    return;
                }
                if (b.getType().equals("Projectile")) {
                    eraseProjectileEntity(controller.getProjectileBody(contact.getFixtureB().getBody()));
                }
                if (a.getType().equals("Projectile")) {
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

    /**
     * Removes an enemy from the game.
     *
     * @param ent Enemy to be removed.
     */
    private void eraseEnemyEntity(EnemyBody ent) {
        if (ent == null) return;
        game.gameInfo.addMoney(((EnemyModel) ent.getUserData()).getMoney());
        controller.deleteEnemyBody(ent);
        model.deleteEnemyModel(((EnemyModel) ent.getUserData()));
    }

    /**
     * Removes a projectile from the game.
     *
     * @param ent Projectile to be removed.
     */
    private void eraseProjectileEntity(ProjectileBody ent) {
        if (ent == null) return;
        controller.deleteProjectileBody(ent);
        model.deleteProjectileModel(((ProjectileModel) ent.getUserData()));
    }

    /**
     * Returns to the previous screen. Called when game ends.
     */
    private void endGame() {
        game.setScreen(returnScreen);
        Gdx.input.setInputProcessor(returnScreen.stage);
        returnScreen.updateGameInfo();
    }

    /**
     * Reset values so the game can be replayed.
     */
    void reset() {
        this.timeElapsed = 0;
        this.enemyIndex = 0;
        this.deltaX = 0;
        this.deltaY = 0;
        this.timeElapsedInt = 0;
        this.endFlag = false;
        hp = game.gameInfo.towerStats.getHp();
        defense = game.gameInfo.towerStats.getDefense();
        model = new GameModel();
        controller = new GameController(model);
        createContactListener();
    }
}
