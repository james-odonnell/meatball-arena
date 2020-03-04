package com.monmouth.screens.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.monmouth.game.MeatballArena;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		final int WIDTH = 1500;
		final int HEIGHT = 800;
		config.width = WIDTH;
		config.height = HEIGHT;
		new LwjglApplication(new MeatballArena(), config);
	}
}	