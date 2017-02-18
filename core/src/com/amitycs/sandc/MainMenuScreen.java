package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen{
	SpriteBatch batch;
	Button test;
	@Override
	public void show() {
		batch = new SpriteBatch();
		test = new Button(0, 0, 100, 50, new Texture(Gdx.files.internal("test.png")));
	}

	@Override
	public void render(float delta) {
		batch.begin();{
			test.draw(batch);
		}batch.end();
		System.out.println(test.mousedOver());
	}

	@Override
	public void resize(int width, int height) {
		
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

}
