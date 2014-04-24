package com.VDIndustries.ui;

import com.VDIndustries.app.MainScreen;
import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * The ever-present looming HUD...
 * 
 */
public class HUD extends WorkWindow {
	
	
	public static final TextButton	hudBackBtn		= new TextButton("Back", Qdap.uiSkin);
	public static final TextButton	hudMainBtn		= new TextButton("Main", Qdap.uiSkin);
	public static final TextButton	hudDummyBtn		= new TextButton("Dummy", Qdap.uiSkin);
	public static final TextButton	hudLogOutBtn	= new TextButton("LogOut", Qdap.uiSkin);

	
	
	public HUD(Qdap qd) {
	
		super(qd, LEFT);
		
		hudBackBtn.addListener(Qdap.universalBtnListener);
		hudMainBtn.addListener(Qdap.universalBtnListener);
		hudLogOutBtn.addListener(Qdap.universalBtnListener);
		
		
	}
	
	@Override protected void initializeTabs() {
		
		tabOpen.setPosition(tabClosedX, tabClosedY);
		tabOpen.addListener(tabListener);
		tabClose.setPosition(tabClosedX, tabClosedY);
		tabClose.addListener(tabListener);

		
		currentTab = tabOpen;
	}
	
	
	@Override protected void initializeDock() {
	
		dock = new Window("", Qdap.uiSkin);
		((Window) dock).setKeepWithinStage(false);
		((Window) dock).setMovable(false);
		
		dock.setBounds(dockClosedX, dockClosedY, dockWidth, dockHeight);
		
		hudDummyBtn.setVisible(false);
		dock.add(hudBackBtn).minSize(dockWidth / 8 * 7, 50);
		dock.row();
		dock.add(hudDummyBtn).minSize(dockWidth / 8 * 7, 25);
		dock.row();
		dock.add(hudMainBtn).minSize(dockWidth / 8 * 7, 50);
		dock.row().minSize(dockWidth / 8 * 7, 50);
		dock.add(hudDummyBtn).minSize(dockWidth / 8 * 7, 25);
		dock.row();
		dock.add(hudLogOutBtn).minSize(dockWidth / 8 * 7, 50);
		
	}
	
	/** */
	@Override public void draw() {
	
		super.draw();
	}
	

	@Override public boolean keyDown(int keycode) {
	
		if (keycode == Keys.BACK) {
			
			if (qdap.getScreen().equals(Qdap.floorScreen)) {
				
				qdap.setScreen(Qdap.mainScreen);
				Qdap.stage.addActor(MainScreen.buildingWnd);
				
				
			} else if (qdap.getScreen().equals(Qdap.roomScreen)) {
				
				qdap.setScreen(Qdap.floorScreen);
//				Qdap.roomScreen.removeWindows();
				
			} else if (qdap.getScreen().equals(Qdap.mainScreen)) {
				
				if (!Qdap.mainScreen.currentWindow.equals(Qdap.mainScreen.loginWnd)) {
					Qdap.mainScreen.removeWindow();
					Qdap.mainScreen.projectWindow();
				}
			} else if (qdap.getScreen().equals(Qdap.reportsScreen)) {
				
				qdap.setScreen(Qdap.mainScreen);
				Qdap.stage.addActor(MainScreen.buildingWnd);
			}
			
			return true;
		}
		return false;
	}
}
