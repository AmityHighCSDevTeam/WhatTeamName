package com.amitycs.sandc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SaveGameScreen implements Screen {

	OrthographicCamera cam;
	SpriteBatch batch;
	private final SupplyAndConquer game;
	private final Map map;
	String file;
	Texture prompt;
	Button yes;
	Button no;
	Button backToGame;
	
	public SaveGameScreen(SupplyAndConquer game, Map map) {
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		this.game = game;
		this.map = map;
		prompt = new Texture("SaveScreenPrompt.png");
		yes = new Button(new Texture(Gdx.files.internal("Yes.png")));
		no = new Button(new Texture(Gdx.files.internal("No.png")));
		backToGame = new Button(new Texture(Gdx.files.internal("ReturnToGame.png")));
	}
	
	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			batch.draw(prompt, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			yes.draw(batch);
			no.draw(batch);
			backToGame.draw(batch);
		}batch.end();
		if (Gdx.input.justTouched()) clickEvents();
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
		batch.setProjectionMatrix(cam.combined);
		yes.modify(width * 3 / 8, height * 6 / 9, width / 4, height / 9, null);
		no.modify(width * 3 / 8, height * 4 / 9, width / 4, height / 9, null);
		backToGame.modify(width * 3 / 8, height * 2 / 9, width / 4, height / 9, null);
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
		if (yes.mousedOver()) write();
		if (no.mousedOver()) returnToMenu();
		if (backToGame.mousedOver()) returnToGame();
	}
	
	public void write() {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(new File(file))); {
				w.write(map.toString());
			}w.close();
		}catch (IOException e) {
			
		}
		returnToMenu();
	}
	
	public void returnToMenu() {
		this.game.setScreen(new MainMenuScreen(game));
	}
	
	public void returnToGame() {
		this.game.setScreen(new GameScreen(game, map));
	}

}
