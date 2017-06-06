package com.mygdx.game.Test;


import com.mygdx.game.GameLogic.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDefend {

    @Test
    public void testNoCritDamage() {
        Projectile projectile = new Projectile(50, 0);
        Enemy enemy = new Enemy(1);
        enemy.getsAttacked(projectile.calculatesDamage());
        assertEquals(enemy.getHp(), 50);
    }

    @Test(timeout=10000)
    public void testCritDamage() {
        Projectile projectile = new Projectile(50, 0.5);
        Enemy enemy = new Enemy(1);
        enemy.setHp(100000);
        for(int i = 0; i < 1000; i++) {
            enemy.getsAttacked(projectile.calculatesDamage());
        }
        assertTrue(enemy.getHp() > 0.4*100000 && enemy.getHp() < 0.6*100000);
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
    public void testGameInfoInfochange() {
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

}
