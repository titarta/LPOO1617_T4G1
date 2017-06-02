package com.mygdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.DefendGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.module.GameModel;
import com.mygdx.game.module.entities.EnemyModel;
import com.mygdx.game.module.entities.EntityModel;
import com.mygdx.game.module.entities.ProjectileModel;
import com.mygdx.game.view.Screens.entities.EnemyView;
import com.mygdx.game.view.Screens.entities.FloorView;
import com.mygdx.game.view.Screens.entities.TowerView;

import java.util.ArrayList;

/**
 * Created by Tiago on 04/05/2017.
 */

public class GameScreen extends ScreenMother {

    private final DefendGame game;
    private final GameModel model;
    private final GameController controller;
    private TowerView towerView;
    private FloorView floorView;
    private EnemyView enemyView;




    public GameScreen(DefendGame game, GameModel model, final GameController controller) {
        super(game);
        this.game = game;
        this.model = model;
        this.controller = controller;

        this.towerView = new TowerView(game);
        this.floorView = new FloorView(game);
        this.enemyView = new EnemyView(game);
        stage.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                super.pan(event, x, y, deltaX, deltaY);
                controller.createProjectile(deltaX, deltaY);
            }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor( 0.95f, 0.95f, 0.95f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.batch.begin();
        drawEntities();
        game.batch.end();
    }

    private void drawEntities() {
        //ArrayList<EnemyModel> enemies = model.getEnemies();
        //ArrayList<ProjectileModel> projectiles = model.getProjectiles();
        floorView.update(model.getFloor());
        floorView.draw(game.batch);
        towerView.update(model.getTower());
        towerView.draw(game.batch);
        model.getEnemies().get(0).step();
        enemyView.update(model.getEnemies().get(0));
        enemyView.draw(game.batch);
    }
}
