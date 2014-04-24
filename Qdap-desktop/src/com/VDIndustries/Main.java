package com.VDIndustries;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Qdap - The Industry Standard";
		
		cfg.width = 1280 / 4 * 3; // Tablet ratio 1280x800
		cfg.height = 800 / 4 * 3; // Scaled for viewability on laptop
		cfg.resizable = false;
		
		new LwjglApplication(new Qdap(), cfg);
	}
}
