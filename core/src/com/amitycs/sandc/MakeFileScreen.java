package com.amitycs.sandc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
		this.dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		prompt.dispose();
		yes.dispose();
		no.dispose();
	}
	
	public void clickEvents() {
		if (yes.mousedOver()) {
			createNewGame();
			enterGameScreen();
		}
		if (no.mousedOver()) exitToMenu();
	}
	
	private void createNewGame() {
		try {
			System.out.println(fileName);
			copyFiles(Gdx.files.internal("template").file(), Gdx.files.internal(fileName).file());
		}catch (IOException e) {
			e.printStackTrace(System.out);
		}
		
	}
	
	public void exitToMenu() {
		game.setScreen(new MainMenuScreen(game));
	}
	
	private void enterGameScreen() {
		game.setScreen(new GameScreen(game, new File(fileName)));
	}

	private void copyFiles(File source, File target) throws IOException {
		
		if (source.exists() && source.isFile() && !target.isDirectory()) {
			target.delete();
			target.createNewFile();
			Scanner s = new Scanner(source);
			BufferedWriter w = new BufferedWriter(new FileWriter(target));
			while(s.hasNextLine()) {
				String str = s.nextLine() + "\n";
				w.write(str);
			}
			s.close();
			w.close();
		}
	}
}
