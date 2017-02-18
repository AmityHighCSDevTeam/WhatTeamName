package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen{
	OrthographicCamera cam;
	SpriteBatch batch;
	Button newGameButt;
	Texture title;
	@Override
	public void show() {
		batch = new SpriteBatch();
		newGameButt = new Button(new Texture(Gdx.files.internal("newGame.png")));
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		title = new Texture(Gdx.files.internal("title.png"));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			int width = Gdx.graphics.getWidth();
			int height = Gdx.graphics.getHeight();
			batch.draw(title, width / 2 - width / 4, height - width / 8, width / 2, width / 8);
			newGameButt.draw(batch);
		}batch.end();
		if (Gdx.input.justTouched()) clickEvents();
	}

	@Override
	public void resize(int width, int height) {
		final int halfWidth = width / 2;
		cam.setToOrtho(false, width, height);
		//cam.update();
		batch.setProjectionMatrix(cam.combined);
		newGameButt.modifySize(halfWidth / 2, -1);
		newGameButt.modifySize(-1, newGameButt.width / 3);
		newGameButt.modifyPos(halfWidth - newGameButt.width / 2, (height * 3) / 4 - newGameButt.height / 2);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
	
	//this method should only be run where a click happened
	private void clickEvents() {
		if (newGameButt.mousedOver()) {
			//dothings
		}
	}

}
