package com.amitycs.sandc;

import com.badlogic.gdx.Input.TextInputListener;

public class FileSelectTextInputListener implements TextInputListener {
	FileSelectScreen parent;
	
	public FileSelectTextInputListener(FileSelectScreen parent) {
		this.parent = parent;
	}
	
	@Override
	public void input(String text) {
		this.parent.fileName = text;
		this.parent.fileName += ".gam";
	}

	@Override
	public void canceled() {
		this.parent.returnToMenu();
	}
}
