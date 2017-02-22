package com.amitycs.sandc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SaveGameScreen implements Screen {

	OrthographicCamera cam;
	SpriteBatch batch;
	private final SupplyAndConquer game;
	private final Map map;
	String file;
	
	public SaveGameScreen(SupplyAndConquer game, Map map) {
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		this.game = game;
		this.map = map;
	}
	
	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			
		}batch.end();

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
	
	public void write() throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter(new File(file))); {
			w.write("");
		}w.close();
	}
	
	public void returnToMenu() {
		this.game.setScreen(new MainMenuScreen(game));
	}

}
