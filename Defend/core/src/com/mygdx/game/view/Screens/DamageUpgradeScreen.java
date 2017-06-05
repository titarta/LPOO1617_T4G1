package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;



class DamageUpgradeScreen extends UpgradeScreen {

    DamageUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Damage", backScreen, new StatsGroup(0, 0, 20, 0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
