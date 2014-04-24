package com.VDIndustries.app;

import com.VDIndustries.clientInfo.FloorProfile;
import com.VDIndustries.ui.FloorInfoWindow;
import com.VDIndustries.ui.PlanBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * UI screen for floors.
 * 
 * @author T-Dawg
 * 
 */
public class FloorScreen implements Screen {
	
	public FloorProfile			currentFloor;
	public FloorInfoWindow		floorInfo;
	
	
	private final Array<Actor>	screenActors	= new Array<Actor>();
	
	
	
	private PlanBox				planBox;
	static private int			planX, planY;
	static private float		lastInfoX		= 0, lastInfoY = 0, lastInfoWidth = 0,
												lastInfoHeight = 0;
	private GestureDetector		gestureDetector;
	
	
	public FloorScreen() {
	
	}
	
	/**
	 * Loads the Floor Profile to view.
	 * 
	 * ???TEMP Room Boundary Setup ???
	 * 
	 * @param name
	 */
	public void loadFloorProfile(String name) {
	
		
		for (FloorProfile fp : Qdap.project.floorProfiles)
			if (fp.floorName.equals(name)) {
				currentFloor = fp;
				setRoomBoundaries();
				return;
			}
		
		System.out.println("Did not find " + name);
	}
	
	/**
	 * Sets up touchable areas for rooms.
	 */
	private void setRoomBoundaries() {
	
		
//		bounds = currentFloor.getRoomBoundary();
		
	}
	
	
	@Override public void render(float delta) {
	
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Qdap.camera.update();
		Qdap.batch.setProjectionMatrix(Qdap.camera.combined);
		Qdap.batch.begin();
		{
			
			planBox.draw(Qdap.batch);
			
		}
		Qdap.batch.end();
		
		
		Qdap.stage.draw();
//		Qdap.stage.act();
		Qdap.hud.draw();
	}
	
	
	@Override public void resize(int width, int height) {
	
	}
	
	
	@Override public void show() {
	
		floorInfo = new FloorInfoWindow();
		if (lastInfoWidth == 0)
			floorInfo.setBounds(Gdx.graphics.getWidth() / 3 * 4 - 10,
					Gdx.graphics.getHeight() / 3 * 4 - 10,
					Gdx.graphics.getWidth() / 4 - 20, Gdx.graphics.getHeight() / 4 * 3);
		else
			floorInfo.setBounds(lastInfoX, lastInfoY, lastInfoWidth, lastInfoHeight);
		
		floorInfo.setTitle(currentFloor.floorName + " data");
		floorInfo.createRoomBtns(currentFloor);
		
		screenActors.add(floorInfo);
		Qdap.stage.addActor(floorInfo);
		
		
		planBox = new PlanBox(currentFloor.floorPlan);
		planBox.setPosition(planX, planY);
		

		gestureDetector = new GestureDetector(planBox);
		Qdap.im.addProcessor(gestureDetector);
	}
	
	
	@Override public void hide() {
	
		lastInfoX = floorInfo.getX();
		lastInfoY = floorInfo.getY();
		lastInfoWidth = floorInfo.getWidth();
		lastInfoHeight = floorInfo.getHeight();
		
		Qdap.im.removeProcessor(gestureDetector);
		planX = (int) planBox.getX();
		planY = (int) planBox.getY();
		for (Actor a : screenActors) {
			Qdap.stage.getRoot().removeActor(a);
			screenActors.removeValue(a, true);
		}
	}
	
	@Override public void pause() {
	
	}
	
	@Override public void resume() {
	
	}
	
	@Override public void dispose() {
	
	}
}
