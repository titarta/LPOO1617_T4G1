package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

/**
 *
 */
class CritChanceUpgradeScreen extends UpgradeScreen{

    CritChanceUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Critical Chance", backScreen, new StatsGroup(0, 0, 0, 5));
        setEvolutionNumber(game.gameInfo.getCritEvNumber());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    protected void backHandler() {
        super.backHandler();
        game.gameInfo.setCritEvNumber(this.getEvolutionNumber());
    }
}
