package com.amitycs.sandc.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amitycs.sandc.SupplyAndConquer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 750;
		config.width = 1500;
		new LwjglApplication(new SupplyAndConquer(), config);
	}
}
