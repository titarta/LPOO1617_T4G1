package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.resolutionStrategy.calcMeasures(2,2);
		try {
			initialize(new DefendGame(), config);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
