package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;

class DefenseUpgradeScreen extends UpgradeScreen {

    DefenseUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Defense", backScreen, new StatsGroup(0, 10, 0, 0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
