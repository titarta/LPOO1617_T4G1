package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

/**
 * Crit chance upgrade screen.
 */
class CritChanceUpgradeScreen extends UpgradeScreen{

    /**
     * Creates the Crit chance upgrade screen.
     *
     * @param game Instance of game.
     * @param backScreen Screen to return.
     */
    CritChanceUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Critical Chance", backScreen, new StatsGroup(0, 0, 0, 5));
        setEvolutionNumber(game.gameInfo.getCritEvNumber());
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
        game.gameInfo.setCritEvNumber(this.getEvolutionNumber());
    }
}
