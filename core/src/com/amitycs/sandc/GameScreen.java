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
	private final Screen parent;
	OrthographicCamera cam;
	SpriteBatch batch;
	Button exitButton;
	Map map;
	
	public GameScreen (SupplyAndConquer game, Screen parent, String fileName) {
		this.game = game;
		this.parent = parent;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		try {
			map = new Map(new File(fileName), this);
		}catch (FileNotFoundException e) {
			game.setScreen(new FailedToFindFileScreen(game, this));
		}
		//exitButton = new Button(new Texture(Gdx.files.internal()));
	}

	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
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
