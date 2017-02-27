package com.amitycs.sandc;

import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen{
	private final SupplyAndConquer game;
	OrthographicCamera cam;
	SpriteBatch batch;
	Button exitButton;
	Map map;
	
	public GameScreen (SupplyAndConquer game, File file) {
		this.game = game;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		try {
			map = new Map(file, this);
		}catch (FileNotFoundException e) {
			game.setScreen(new FailedToFindFileScreen(game));
		}
		//exitButton = new Button(new Texture(Gdx.files.internal()));
	}

	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			
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
		this.dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
	private void clickEvents() {

	}
	
	/*
	 * conditions:
	 * 0 = user exit
	 * 1 = some kind of error
	 * #theClifegotmelike
	 */
	
	public void exit(int condition) {
		switch (condition) {
			case 0 :
				this.game.setScreen(new SaveGameScreen(game, map));
				break;
			case 1 :
				this.game.setScreen(new ErrorScreen(game));
				break;
		}
	}
	
	public int smithProductionSelect() {
		//do things later
		return -1;
	}
}
