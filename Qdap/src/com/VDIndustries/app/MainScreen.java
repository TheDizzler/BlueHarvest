package com.VDIndustries.app;

import com.VDIndustries.ui.BuildingWindow;
import com.VDIndustries.ui.LoginWindow;
import com.VDIndustries.ui.ProjectWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;


public class MainScreen implements Screen {
	
	
	public static LoginWindow		loginWnd;
	public static ProjectWindow		projectWnd;
	public static BuildingWindow	buildingWnd;
	
	public static Table				currentWindow;
	
	
	/* Stage actors for this specific screen that need to be cleaned up before
	 * switching screens. */
	private final Array<Actor>		screenActors	= new Array<Actor>();
	
	private final Qdap				qdap;
	
	
	/**
	 * Main screen. Menu (login, project, building) windows are built on top of
	 * this screen.
	 */
	public MainScreen(Qdap q) {
	
		qdap = q;
		
		Qdap.logo.setX(Gdx.graphics.getWidth() / 2 - Qdap.logo.getWidth() / 2);
		Qdap.logo.setY(Gdx.graphics.getHeight() * 3 / 4 - Qdap.logo.getHeight() / 2);
		
		
		
		loginWnd = new LoginWindow();
		projectWnd = new ProjectWindow();
		buildingWnd = new BuildingWindow();
		
		login();
	}
	
	
	@Override public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Qdap.batch.setProjectionMatrix(Qdap.camera.combined);
		
		Qdap.stage.draw();
		Qdap.hud.draw();
	}
	
	
	@Override public void show() {
	
		Qdap.stage.addActor(Qdap.logo);
		screenActors.add(Qdap.logo);
		
	}
	
	/**
	 * Initiates project building.
	 * Called after asset manager finished.
	 */
	public void projectInitialize() {
	
		Qdap.projectLoaded = true;
//		Qdap.am.finishLoading();
		Qdap.project.initializeFloors();
		System.out.println("...Project Initialized");
	}
	
	/** On changing screen from this one, remove all the actors from stage */
	@Override public void hide() {
	
		for (Actor a : screenActors) {
			Qdap.stage.getRoot().removeActor(a);
			screenActors.removeValue(a, true);
		}
		
		removeWindow();
	}
	
	/**
	 * Load login window and unload project.
	 */
	public void login() {
	
		Qdap.projectLoaded = false;
		Qdap.am.clear();
		currentWindow = loginWnd;
		Qdap.stage.addActor(currentWindow);
	}
	
	/**
	 * Load project window.
	 */
	public void projectWindow() {
	
		currentWindow = projectWnd;
		Qdap.stage.addActor(currentWindow);
	}
	
	/**
	 * Load building window
	 */
	public void buildingWindow() {
	
		if (Qdap.projectLoaded) {
			if (buildingWnd.buttons.size == 0)
				buildingWnd.buildFloorButtons();
			currentWindow = buildingWnd;
			Qdap.stage.addActor(currentWindow);
		}
	}
	
	/**
	 * Clears the current window.
	 */
	public void removeWindow() {
	
		Qdap.stage.getRoot().removeActor(MainScreen.currentWindow);
	}
	
	
	
	@Override public void resize(int width, int height) {
	
	}
	
	
	@Override public void pause() {
	
	}
	
	@Override public void resume() {
	
	}
	
	@Override public void dispose() {
	
	}
}
