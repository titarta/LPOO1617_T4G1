package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;


class HpUpgradeScreen extends UpgradeScreen {
    HpUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Health", backScreen, new StatsGroup(50, 0, 0, 0));
        setEvolutionNumber(game.gameInfo.getHpEvNumber());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    protected void backHandler() {
        super.backHandler();
        game.gameInfo.setHpEvNumber(this.getEvolutionNumber());
    }
}
