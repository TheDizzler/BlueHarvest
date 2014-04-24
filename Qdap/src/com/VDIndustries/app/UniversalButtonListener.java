package com.VDIndustries.app;

import com.VDIndustries.clientInfo.Project;
import com.VDIndustries.ui.LoginWindow;
import com.VDIndustries.ui.ProjectWindow;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Universal catch-all button listener.
 * 
 * @author T-Dawg
 * 
 */
public class UniversalButtonListener extends ClickListener {
	
	private final Qdap	qdap;
	
	public UniversalButtonListener(Qdap qdap) {
	
		this.qdap = qdap;
	}
	
	
	@Override public void clicked(InputEvent event, float x, float y) {
	
		Actor a = event.getListenerActor();
		
		/* Checklist button in Project Window */
		if (a.equals(ProjectWindow.checklists)) {
			/* Close Project window and open Building window */
			Qdap.mainScreen.removeWindow();
			Qdap.mainScreen.buildingWindow();
			
			/* Go to Reports Screen */
		} else if (a.equals(ProjectWindow.reports)) {
			
			Qdap.reportsScreen = new ReportsScreen(Qdap.project);
			qdap.setScreen(Qdap.reportsScreen);
			
			/* Go button on login screen */
		} else if (a.equals(LoginWindow.buttonGo)) {
			/* Start loading assets */
			Qdap.project = new Project();
			Qdap.project.loadFloorPlans();
			qdap.setScreen(new LoadingScreen(qdap));
			
			Qdap.mainScreen.removeWindow();
			
		} else if (a.equals(Qdap.hud.hudMainBtn)) {
			
			if (Qdap.projectLoaded) {
				qdap.setScreen(Qdap.mainScreen);
				/* Clean all windows from screen. */
				for (Actor actor : Qdap.stage.getActors())
					if (actor.equals(Window.class))
						Qdap.stage.getRoot().removeActor(actor);
				
				Qdap.mainScreen.projectWindow();
				qdap.hud.closeTab();
			}
			
			/* Back button on HUD */
		} else if (a.equals(Qdap.hud.hudBackBtn)) {
			
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
				
				/* Back Button pressed while in Reports */
			} else if (qdap.getScreen().equals(Qdap.reportsScreen)) {
				
				qdap.setScreen(Qdap.mainScreen);
				Qdap.mainScreen.projectWindow();
			}
			
			/* Logout button on HUD */
		} else if (a.equals(Qdap.hud.hudLogOutBtn)) {
			
			
			qdap.setScreen(Qdap.mainScreen);
			for (Actor actor : Qdap.stage.getActors())
				if (actor.equals(Window.class))
					Qdap.stage.getRoot().removeActor(actor);
			
			Qdap.mainScreen.login();
			qdap.hud.closeTab();
			
			/* Main Button on HUD */
			
			
			/* As long as button has a set name */
		} else if (a.getName() != null) {
			/* Go to a Floor screen */
			Qdap.mainScreen.removeWindow();
			Qdap.floorScreen.loadFloorProfile(a.getName());
			qdap.setScreen(Qdap.floorScreen);
			
		} else {
			System.out.println("no implementente");
		}
	}
}
