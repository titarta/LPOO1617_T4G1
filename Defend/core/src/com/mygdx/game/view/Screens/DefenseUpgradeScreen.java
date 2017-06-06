package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

class DefenseUpgradeScreen extends UpgradeScreen {

    DefenseUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Defense", backScreen, new StatsGroup(0, 3, 0, 0));
        setEvolutionNumber(game.gameInfo.getDefenseEvNumber());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    protected void backHandler() {
        super.backHandler();
        game.gameInfo.setDefenseEvNumber(this.getEvolutionNumber());
    }
}
