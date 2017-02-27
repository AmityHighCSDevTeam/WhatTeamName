package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MakeFileScreen implements Screen {
	private final SupplyAndConquer game;
	OrthographicCamera cam;
	SpriteBatch batch;
	Texture prompt;
	Button yes;
	Button no;
	String fileName;
	
	public MakeFileScreen(SupplyAndConquer game, String fileName) {
		this.game = game;
		this.prompt = new Texture(Gdx.files.internal("MakeNewFilePrompt.png"));
		this.fileName = fileName;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		yes = new Button(new Texture(Gdx.files.internal("Yes.png")));
		no = new Button(new Texture(Gdx.files.internal("No.png")));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			batch.draw(prompt, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			yes.draw(batch);
			no.draw(batch);
			
		}batch.end();
		if (Gdx.input.justTouched()) clickEvents();
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
		batch.setProjectionMatrix(cam.combined);
		yes.modify(width / 5, (height * 2) / 5, width / 5, height / 5, null);
		no.modify((width * 3) / 5, (height * 2) / 5, width / 5, height / 5, null);
		
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
		if (yes.mousedOver()) createNewGame();
		if (no.mousedOver()) exitToMenu();
	}
	
	private void createNewGame() {
		
	}
	
	private void exitToMenu() {
		game.setScreen(new MainMenuScreen(game));
	}
}
