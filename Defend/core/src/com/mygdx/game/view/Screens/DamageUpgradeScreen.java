package com.mygdx.game.view.Screens;

import com.mygdx.game.DefendGame;
import com.mygdx.game.GameLogic.StatsGroup;



class DamageUpgradeScreen extends UpgradeScreen {

    DamageUpgradeScreen(DefendGame game, ScreenMother backScreen) {
        super(game, "Damage", backScreen, new StatsGroup(0, 0, 20, 0));
        setEvolutionNumber(game.gameInfo.getDamageEvNumber());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    protected void backHandler() {
        super.backHandler();
        game.gameInfo.setDamageEvNumber(this.getEvolutionNumber());
    }
}
