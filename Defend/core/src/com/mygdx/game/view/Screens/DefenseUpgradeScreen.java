package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

/**
 * Defense upgrade screen.
 */
class DefenseUpgradeScreen extends UpgradeScreen {

    /**
     * Creates the Defense upgrade screen.
     *
     * @param game Instance of game.
     * @param backScreen Screen to return.
     */
    DefenseUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Defense", backScreen, new StatsGroup(0, 3, 0, 0));
        setEvolutionNumber(game.gameInfo.getDefenseEvNumber());
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
        game.gameInfo.setDefenseEvNumber(this.getEvolutionNumber());
    }
}
