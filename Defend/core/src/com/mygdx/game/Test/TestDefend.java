package com.mygdx.game.Test;


import com.mygdx.game.GameLogic.*;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.module.GameModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestDefend extends TestBackend {

    @Test
    public void testNoCritDamage() {
        Projectile projectile = new Projectile(50, 0);
        Enemy enemy = new Enemy(1);
        enemy.getsAttacked(projectile.calculatesDamage());
        assertEquals(enemy.getHp(), 50);
    }

    @Test(timeout=100)
    public void testCritDamage() {
        Projectile projectile = new Projectile(50, 50);
        Enemy enemy = new Enemy(1);
        enemy.setHp(100000);
        for(int i = 0; i < 1000; i++) {
            enemy.getsAttacked(projectile.calculatesDamage());
        }
        assertTrue(enemy.getHp() > 0.2*100000 && enemy.getHp() < 0.3*100000);
    }

    @Test
    public void testEnemyAttack() {
        GameInfo game = new GameInfo();
        Enemy enemy = new Enemy(3);
        int counter = 0;
        while (game.towerStats.getHp() > 0) {
            game.towerStats.setHp(game.towerStats.getHp() - enemy.getDamage());
            counter++;
        }
        GameInfo newGame = new GameInfo();
        assertEquals(((int) Math.ceil(newGame.towerStats.getHp() / enemy.getDamage())), counter);
    }

    @Test
    public void testStatsGroupAdd() {
        GameInfo game1 = new GameInfo();
        GameInfo game2 = new GameInfo();
        game1.upgradeStat(game2.towerStats);
        game1.upgradeStat(game2.towerStats);
        game1.upgradeStat(game2.towerStats);
        assertEquals(((int) game1.towerStats.getCritChance()), ((int) (game2.towerStats.getCritChance() * 4)));
        assertEquals(game1.towerStats.getHp(), game2.towerStats.getHp() * 4);
        assertEquals(game1.towerStats.getDamage(), game2.towerStats.getDamage() * 4);
        assertEquals(game1.towerStats.getDefense(), game2.towerStats.getDefense() * 4);
    }

    @Test
    public void testGameInfochange() {
        GameInfo game = new GameInfo();
        game.addMoney(400);
        game.spendMoney(200);
        assertEquals(game.getMoney(), 200);
        game.setCritEvNumber(3);
        assertEquals(game.getCritEvNumber(), 3);
        game.setHpEvNumber(3);
        assertEquals(game.getHpEvNumber(), 3);
        game.setDamageEvNumber(3);
        assertEquals(game.getDamageEvNumber(), 3);
        game.setDefenseEvNumber(3);
        assertEquals(game.getDefenseEvNumber(), 3);
    }

    @Test
    public void createFloor() {
        GameController controller = new GameController(new GameModel());
        assertEquals(controller.getWorld().getBodyCount(), 1);
    }

}
