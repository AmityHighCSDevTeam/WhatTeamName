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
		setFields(xPos, yPos, width, height, t);
	}
	
	public Button(Texture t) {
		setFields(0, 0, 0, 0, t);
	}
	
	private void setFields(int xPos, int yPos, int width, int height, Texture t){
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.width = width;
		this.height = height;
		this.texture = t;
	}
	
	//sets fields of button, but if a num is negative it will be unmodified. Same for texture w/ null
	public void modify(int xPos, int yPos, int width, int height, Texture t){
		modifyPos(xPos, yPos);
		modifySize(width, height);
		modifyTexture(t);
	}
	
	//same as above, but only for position
	public void modifyPos(int xPos, int yPos){
		if (xPos < 0); else this.xPosition = xPos;
		if (yPos < 0); else this.yPosition = yPos;
	}
	
	//same as above, but only for size
	public void modifySize(int width, int height){
		if (width < 0); else this.width = width;
		if (height < 0); else this.height = height;
	}
	
	//same as above, but only for texture Null is the rejected val
	public void modifyTexture(Texture t) {
		if (t == null); else this.texture = t;
	}
	
	public void draw(SpriteBatch b) {
		b.draw(Const.BUTTON_BASE, xPosition, yPosition, width, height);
		b.draw(texture, xPosition, yPosition, width, height);
	}
	
	public boolean mousedOver() {
		int x = Gdx.input.getX();
		if (xPosition < x && x < xPosition + width){
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();
			if (yPosition < y && y < yPosition + height)
				return true;
		}
		return false;
	}
}
