package com.VDIndustries.app;

import com.VDIndustries.clientInfo.FloorProfile;
import com.VDIndustries.clientInfo.Project;
import com.VDIndustries.clientInfo.RoomProfile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.XmlReader.Element;


public class ReportsScreen implements Screen {
	
	private final Project	project;
	
//	Table					table	= new Window("  ", Qdap.uiSkin);
	Table					table	= new Table();
	ScrollPane				pane	= new ScrollPane(table);
	
	
	
	public ReportsScreen(Project proj) {
	
		project = proj;

		Qdap.logo.setX(Gdx.graphics.getWidth() / 2 - Qdap.logo.getWidth() / 2);
		Qdap.logo.setY(Gdx.graphics.getHeight() * 3 / 4 - Qdap.logo.getHeight() / 2);
		
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		
	}
	
	
	@Override public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Qdap.batch.setProjectionMatrix(Qdap.camera.combined);
		
		Qdap.stage.draw();
//		Qdap.stage.act();
		Qdap.hud.draw();
		
	}
	
	@Override public void resize(int width, int height) {
	
	}
	
	@Override public void show() {
	
		Qdap.stage.addActor(table);
		table.clear();
		buildReportList();
	}
	
	private void buildReportList() {
	
		
		table.addActor(new Label("Project : " + project.projectName, Qdap.uiSkin));
		table.row();
		
		
		for (FloorProfile floor : project.floorProfiles) {
			
			int floorDefCount = 0;
			
			Window floorTemp = new Window(floor.floorName, Qdap.uiSkin);
//			ScrollPane floorScroll = new ScrollPane(floorTemp);
			floorTemp.setKeepWithinStage(true); // Y U NO WORK
			table.add(floorTemp);
			
			
			
			for (RoomProfile room : floor.roomProfiles) {
				int roomDefCount = 0;
				if (room.deficiencies.size > 0) {
					
					Window roomTemp = new Window("Room " + room.roomNo, Qdap.uiSkin);
//					ScrollPane roomScroll = new ScrollPane(roomTemp);
					
					floorTemp.add(roomTemp);
					
					for (Element report : room.deficiencies) {
						
						floorDefCount++;
						roomDefCount++;
						
						roomTemp.add(new Label("Report #" + report.getAttribute("reportID") + ": "
												, Qdap.uiSkin));
						
						Element desc = report.getChildByName("description");
//					
//					priorityCheck.setChecked(report.get("priority").equals("true"));
//					completedCheck.setChecked(report.get("completed").equals("true"));
						
						
						String s = desc.getChildByName("object").getText() + " "
									+ desc.getChildByName("item").getText() + " "
									+ desc.getChildByName("verb").getText() + " "
									+ desc.getChildByName("direction").getText() + " "
									+ desc.getChildByName("location").getText();
						roomTemp.add(new Label(s, Qdap.uiSkin));
						roomTemp.row();
						
					}
					roomTemp.setTitle(roomTemp.getTitle() + "   Outstanding Tasks: " + roomDefCount);
				}
				floorTemp.row();
			}
			
			floorTemp.setTitle(floorTemp.getTitle() + "   Outstanding Tasks: " + floorDefCount);
			
		}
	}
	
	
	@Override public void hide() {
	
		Qdap.stage.getRoot().removeActor(table);
	}
	
	@Override public void pause() {
	
	}
	
	@Override public void resume() {
	
	}
	
	@Override public void dispose() {
	
	}
	
}
