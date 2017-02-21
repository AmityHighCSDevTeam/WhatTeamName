package com.amitycs.sandc;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FileSelectScreen implements Screen{

	OrthographicCamera cam;
	SpriteBatch batch;
	SupplyAndConquer game;
	Screen parent;
	
	public FileSelectScreen(SupplyAndConquer game, Screen parent) {
		this.game = game;
		this.parent = parent;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
		batch.setProjectionMatrix(cam.combined);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void launchGame(String fileName) {
		game.setScreen(new GameScreen(game, this, fileName));
	}
	
}
