package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
	public Texture texture;
	public int width;
	public int height;
	public int xPosition;
	public int yPosition;
	
	public Button() {
		setFields(0, 0, 0, 0, null);
	}
	
	public Button(int xPos, int yPos, int width, int height, Texture t) {
		setFields(xPos, yPos, width, height, null);
	}
	
	public Button(Texture t) {
		setFields(0, 0, 0, 0, t);
	}
	
	private void setFields(int xPos, int yPos, int width, int height, Texture t){
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.width = width;
		this.height = height;
	}
	
	public void draw(SpriteBatch b) {
		b.draw(texture, xPosition, yPosition, width, height);
	}
	
	public boolean mousedOver() {
		int x = Gdx.input.getX();
		if (xPosition < x && x < xPosition + width){
			int y =Gdx.input.getY();
			if (yPosition < y && y < yPosition + height)
				return true;
		}
		return false;
	}
}
