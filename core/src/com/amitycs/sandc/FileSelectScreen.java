package com.amitycs.sandc;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class FileSelectScreen implements Screen{
	private final SupplyAndConquer game;
	String fileName = null;
	
	public FileSelectScreen(SupplyAndConquer game) {
		this.game = game;
		FileSelectTextInputListener listener = new FileSelectTextInputListener(this);
		Gdx.input.getTextInput(listener, "Input a file name", "", "Game File");
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		if (fileName != null) {
			fileThings();
		}
	}

	@Override
	public void resize(int width, int height) {
		
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
