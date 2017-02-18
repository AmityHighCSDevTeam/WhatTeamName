package com.amitycs.sandc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class SupplyAndConquer extends Game {

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void create() {
		setScreen(new MainMenuScreen());
	}
	
}
