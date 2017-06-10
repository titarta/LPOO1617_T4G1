package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

/**
 * Hp upgrade screen.
 */
class HpUpgradeScreen extends UpgradeScreen {

    /**
     * Creates the Hp upgrade screen.
     *
     * @param game Instance of game.
     * @param backScreen Screen to return.
     */
    HpUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Health", backScreen, new StatsGroup(50, 0, 0, 0));
        setEvolutionNumber(game.gameInfo.getHpEvNumber());
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
        game.gameInfo.setHpEvNumber(this.getEvolutionNumber());
    }
}
