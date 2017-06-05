package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;


class HpUpgradeScreen extends UpgradeScreen {
    HpUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Health", backScreen, new StatsGroup(50, 0, 0, 0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
