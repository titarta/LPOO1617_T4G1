package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;


/**
 * Damage upgrade screen.
 */
class DamageUpgradeScreen extends UpgradeScreen {

    /**
     * Creates the Damage upgrade screen.
     *
     * @param game Instance of game.
     * @param backScreen Screen to return.
     */
    DamageUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Damage", backScreen, new StatsGroup(0, 0, 20, 0));
        setEvolutionNumber(game.gameInfo.getDamageEvNumber());
    }

    @Override
    int getValue() {
        return game.gameInfo.towerStats.getDamage();
    }

    /**
     * Overrides render method from screenMother.
     *
     * @param delta Not used.
     */
    @Override
    public void render(float delta) {
        super.render(delta);
    }

    /**
     * Return handler.
     */
    @Override
    protected void backHandler() {
        super.backHandler();
        game.gameInfo.setDamageEvNumber(this.getEvolutionNumber());
    }
}
