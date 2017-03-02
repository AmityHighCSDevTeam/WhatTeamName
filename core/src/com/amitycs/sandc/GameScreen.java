package com.amitycs.sandc;

import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen{
	private final SupplyAndConquer game;
	OrthographicCamera cam;
	SpriteBatch batch;
	Button exitButton;
	Map map;
	float[] cameraLocation; //[0] is x, [1] is y marks the center of the view
							//measured in terms of tiles
	
	Texture[][] units; // first array is team, 0 = blu 1 = red
						// inner array is unit type, numbered like in Const
	Texture[] armor; // 0 = none, 1 = leather, 2 = metal
	
	
	public GameScreen (SupplyAndConquer game, File file) {
		this.game = game;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		try {
			map = new Map(file, this);
		}catch (FileNotFoundException e) {
			game.setScreen(new FailedToFindFileScreen(game));
		}
		exitButton = new Button(new Texture(Gdx.files.internal("ExitGame.png")));
		cameraLocation = Const.CAMERA_START_POSITION;
	}
	
	public GameScreen(SupplyAndConquer game, Map map) {
		this.game = game;
		this.map = map;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		exitButton = new Button(new Texture(Gdx.files.internal("ExitGame.png")));
		cameraLocation = Const.CAMERA_START_POSITION;
	}

	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();{
			
			exitButton.draw(batch);
		}batch.end();
		if (Gdx.input.justTouched()) clickEvents();
		if (Gdx.input.isButtonPressed(19)) cameraLocation[1] += Const.CAMERA_MOVE_SPEED * delta;
		if (Gdx.input.isButtonPressed(20)) cameraLocation[1] -= Const.CAMERA_MOVE_SPEED * delta;
		if (Gdx.input.isButtonPressed(21)) cameraLocation[0] -= Const.CAMERA_MOVE_SPEED * delta;
		if (Gdx.input.isButtonPressed(22)) cameraLocation[0] += Const.CAMERA_MOVE_SPEED * delta;
		
		if (cameraLocation[0] < 0) cameraLocation[0] = 0;
		if (cameraLocation[0] > Const.MAP_SIZE[0]) cameraLocation[0] = Const.MAP_SIZE[0];
		if (cameraLocation[1] < 0) cameraLocation[1] = 0;
		if (cameraLocation[1] > Const.MAP_SIZE[1]) cameraLocation[1] = Const.MAP_SIZE[1];
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
		batch.setProjectionMatrix(cam.combined);
		exitButton.modifyPos(width - width / 6, height - height / 6);
		exitButton.modifySize(width / 6, height / 6);
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
		exitButton.dispose();
	}
	
	private void clickEvents() {
		if (exitButton.mousedOver()) exit(0);
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
	
}
