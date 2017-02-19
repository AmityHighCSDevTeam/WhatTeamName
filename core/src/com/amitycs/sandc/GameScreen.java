package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen{
	private final SupplyAndConquer game;
	private final Screen parent;
	OrthographicCamera cam;
	SpriteBatch batch;
	
	public GameScreen (SupplyAndConquer game, Screen parent) {
		this.game = game;
		this.parent = parent;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			
		}batch.end();
		if (Gdx.input.justTouched()) clickEvents();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
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
		this.dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
	private void clickEvents() {
		
	}
	
	public void exit() {
		game.setScreen(parent);
	}
	
}
