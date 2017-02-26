package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Font {
	TextureRegion texture;
	char character;
	
	public Font(Texture font, int[] position, char character) {
		this.set(font, position[0], position[1], character);
	}
	
	public Font(Texture font, int x, int y, char character) {
		this.set(font, x, y, character);
	}
	
	private void set(Texture font, int x, int y, char character) {
		texture = new TextureRegion(font, x * Const.CHARACTER_WIDTH, y * Const.CHARACTER_HEIGHT);
		this.character = character;
	}
}
