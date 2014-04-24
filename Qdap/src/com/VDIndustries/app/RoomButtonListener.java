package com.VDIndustries.app;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Room button/touch listener for buttons on FloorScreen
 * and FloorInfoWindow.
 * 
 * @author T-Dawg
 * 
 */
public class RoomButtonListener extends ClickListener {
	
	private final Qdap	qdap;
	
	
	public RoomButtonListener(Qdap qdap) {
	
		this.qdap = qdap;
	}
	
	
	@Override public void clicked(InputEvent event, float x, float y) {
	
		Actor a = event.getListenerActor();
		
		Qdap.roomScreen.loadRoomProfile(((FloorScreen) qdap.getScreen()).currentFloor, a.getName());
		qdap.setScreen(Qdap.roomScreen);
	}
}
