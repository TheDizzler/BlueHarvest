package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.VDIndustries.app.RoomScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class FlagListener extends ClickListener {
	
	
	public FlagListener() {
	
	}
	
	
	@Override public void clicked(InputEvent event, float x, float y) {
		
		RoomScreen.defReport.loadDeficiency(RoomScreen.getReport(event.getListenerActor().getName()));
//		Qdap.stage.addActor(RoomScreen.defReport); 
		Qdap.roomScreen.makeReportWindow();
	}
}
