package com.VDIndustries.app;

import com.VDIndustries.clientInfo.FloorProfile;
import com.VDIndustries.clientInfo.RoomProfile;
import com.VDIndustries.ui.DeficiencyReportWindow;
import com.VDIndustries.ui.EditReportWindow;
import com.VDIndustries.ui.FlagListener;
import com.VDIndustries.ui.FlagSelectWindow;
import com.VDIndustries.ui.RoomJobWindow;
import com.VDIndustries.ui.RoomPlanBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * UI screen for Rooms.
 * 
 * @author T-Dawg
 * 
 */
public class RoomScreen implements Screen {
	
	
	private final Qdap						qdap;
	
	
	public static RoomProfile				currentRoom;
	public FloorProfile						parentFloor;
	
	public RoomPlanBox						planBox;
	private static RoomJobWindow			roomWindow;
	public static FlagSelectWindow			flagSelect;
	
	
	private GestureDetector					planBoxGestureControl;
	
	public static DeficiencyReportWindow	defReport	= new DeficiencyReportWindow();
	public static FlagListener				flagListen	= new FlagListener();
	
	
	public static EditReportListener		editListener;
	public static EditReportWindow			editReportWindow;
	
	private final Array<Table>				windows;
	
	
	public RoomScreen(Qdap qd) {
	
		qdap = qd;
		windows = new Array<Table>();
		editListener = new EditReportListener();
		
	}
	
	/**
	 * Load selected room.
	 * Called by RoomButtonListener
	 * 
	 * @param parent
	 * @param name
	 */
	public void loadRoomProfile(FloorProfile parent, String name) {
	
		parentFloor = parent;
		
		for (RoomProfile room : parentFloor.roomProfiles) {
			if (room.roomNo.equals(name))
				currentRoom = room;
		}
	}
	
	
	public static Element getReport(String id) {
	
		for (Element def : currentRoom.deficiencies)
			if (def.getAttribute("reportID").equals(id))
				return def;
		
		return null;
	}
	
	
	@Override public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Qdap.camera.update();
		Qdap.batch.setProjectionMatrix(Qdap.camera.combined);
		
		
		planBox.drawFrame();
		Qdap.batch.begin();
		{
			
			planBox.draw(Qdap.batch);
			
		}
		Qdap.batch.end();
		Qdap.stage.draw();
		roomWindow.draw();
		flagSelect.draw();
		
		Qdap.stage.act();
		
		Qdap.hud.draw();
	}
	
	/**
	 * Makes a new Edit Report Window from an existing Report and adds it to the
	 * stage.
	 */
	public void makeReportWindow(float x, float y, Element report) {
	
		editReportWindow = new EditReportWindow("New", x, y);
		windows.add(editReportWindow);
		Qdap.stage.addActor(editReportWindow);
		editReportWindow.setLists(report);
	}
	
	/** Makes a new Edit Report Window and adds it to the stage. */
	public void makeReportWindow(float x, float y, String string) {
	
		editReportWindow = new EditReportWindow(string, x, y);
		Qdap.stage.addActor(editReportWindow);
		windows.add(editReportWindow);
	}
	
	/** Adds the Deficiency Report Window to the stage. */
	public void makeReportWindow() {
	
		Qdap.stage.addActor(defReport);
		windows.add(defReport);
	}
	
	/**
	 * On display of screen, load job window and room plan.
	 */
	@Override public void show() {
	
		if (roomWindow == null)
			roomWindow = new RoomJobWindow(qdap);
		
		if (flagSelect == null)
			flagSelect = new FlagSelectWindow(qdap);
		
		((Window) roomWindow.dock).setTitle("Room " + currentRoom.roomNo + " reports by trade");
//		roomWindow.createJobBtns(currentRoom);
		
		Qdap.im.addProcessor(roomWindow);
		Qdap.im.addProcessor(flagSelect);
		
		planBox = new RoomPlanBox(currentRoom.roomPlan);
		RoomPlanBox.placeFlags(currentRoom.deficiencies);
		
		planBoxGestureControl = new GestureDetector(planBox);
		Qdap.im.addProcessor(planBoxGestureControl);
	}
	
	
	@Override public void hide() {
	
		removeWindows();
		
		Qdap.im.removeProcessor(roomWindow);
		Qdap.im.removeProcessor(planBoxGestureControl);
		Qdap.im.removeProcessor(flagSelect);
		
		planBox.removeFlags();
		planBox.shiftX = planBox.innerX;
		planBox.shiftY = planBox.innerY;
	}
	
	/** Remove all windows from stage. */
	public void removeWindows() {
	
		for (Table window : windows)
			removeWindow(window);
	}
	
	
	public void removeWindow(Table window) {
	
		Qdap.stage.getRoot().removeActor(window);
		windows.removeValue(window, false);
	}
	
	
	@Override public void dispose() {
	
	}
	
	
	@Override public void resize(int width, int height) {
	
	}
	
	@Override public void pause() {
	
	}
	
	@Override public void resume() {
	
	}
	
	
	
}
