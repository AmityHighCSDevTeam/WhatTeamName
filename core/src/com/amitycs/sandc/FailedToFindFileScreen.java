package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FailedToFindFileScreen implements Screen{
	OrthographicCamera cam;
	SpriteBatch batch;
	SupplyAndConquer game;
	Texture failedToFind;
	
	
	public FailedToFindFileScreen(SupplyAndConquer game) {
		this.game = game;
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		failedToFind = new Texture(Gdx.files.internal("FailedToFindFile.png"));
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			batch.draw(failedToFind, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}batch.end();
		if (Gdx.input.justTouched()) clickEvents();
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
	
	public void clickEvents() {
		returnToMenu();
	}
	
	public void returnToMenu() {
		this.game.setScreen(new MainMenuScreen(game));
	}
}
