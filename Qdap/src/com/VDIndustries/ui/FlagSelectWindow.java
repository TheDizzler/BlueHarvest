package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Window;


public class FlagSelectWindow extends WorkWindow {

	
	public static CheckBox showPriority, hideDone, showDone;
	
	
	public FlagSelectWindow(Qdap qd) {
	
		super(qd, RIGHT);
	}

	@Override protected void initializeTabs() {
		
		// Start Open
		tabOpen.setPosition(tabOpenX, tabOpenY);
		tabOpen.addListener(tabListener);
		tabClose.setPosition(tabOpenY, tabOpenY);
		tabClose.addListener(tabListener);
		
		tabOpen.setVisible(false);
		currentTab = tabClose;
		currentTab.setPosition(tabOpenX, tabOpenY);
		
		isOpen = true;
	}

	@Override protected void initializeDock() {
		
		
		dock = new Window("Reports to Show", Qdap.uiSkin);
		((Window) dock).setKeepWithinStage(false);
		((Window) dock).setMovable(false);
		
		
		showPriority = new CheckBox("Only Priority Tasks", Qdap.uiSkin);
		hideDone = new CheckBox("Hide Completed Tasks", Qdap.uiSkin);
		showDone = new CheckBox("Only Completed Tasks", Qdap.uiSkin);
		
		dock.add(showPriority);
		dock.row();
		dock.add(hideDone);
		dock.row();
		dock.add(showDone);
		
		dock.setBounds(dockOpenX, dockOpenY, dockWidth, dockHeight);
	
	}
	
}
