package com.amitycs.sandc;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class FileSelectScreen implements Screen{

	//OrthographicCamera cam;
	//SpriteBatch batch;
	SupplyAndConquer game;
	
	/*
	Button newFile;
	Button loadFile;
	Texture prompt;
	*/
	String fileName = null;
	/*
	private Texture FONT_ATLAS = new Texture(Gdx.files.internal("Font.png"));
	public Font[][] FONT_CHARACTERS; // these should be final, but eh
	*/
	
	
	public FileSelectScreen(SupplyAndConquer game) {
		this.game = game;
		FileSelectTextInputListener listener = new FileSelectTextInputListener(this);
		Gdx.input.getTextInput(listener, "Input a file name", "", "Game File");
		/*
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		newFile = new Button(new Texture(Gdx.files.internal("New")));
		loadFile = new Button(new Texture(Gdx.files.internal("LOAD")));

		char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
						'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
						'(', '{', '[', '<', '>', ']', '}', ')', ',', '.', '?', '!', '@', '#', '$', '%', '^', '&', '*', '-', '+', '_', '=', '\'', '"', '`',
						'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		
		for (int i = 0; i < 4; i++) {
			if (i != 3) {
				for (int j = 0; j < 26; j++) {
					FONT_CHARACTERS[i][j] = new Font(FONT_ATLAS, i, j, chars[i + j]);
				}
			}else {
				for (int j = 0; j < 10; j++) {
					FONT_CHARACTERS[i][j] = new Font(FONT_ATLAS, i, j, chars[i + j]);
				}
			}
		}
		*/
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();{
			
		//}batch.end();
		if (fileName != null) {
			fileThings();
		}
	}

	@Override
	public void resize(int width, int height) {
		//cam.setToOrtho(false, width, height);
		//batch.setProjectionMatrix(cam.combined);
		
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
	
	private void fileThings() {
		if (Gdx.files.internal(fileName).exists()) 
			launchGame(fileName);
		else
			makeFileScreen();
	}
	
	public void launchGame(String fileName) {
		game.setScreen(new GameScreen(game, new File(fileName)));
	}
	
	public void returnToMenu() {
		game.setScreen(new MainMenuScreen(game));
	}
	
	public void makeFileScreen() {
		game.setScreen(new MakeFileScreen(game, fileName));
	}
}
