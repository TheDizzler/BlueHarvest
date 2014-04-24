package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.VDIndustries.app.RoomScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;


public class RoomPlanBox extends PlanBox {
	
	
	static Array<Element>					deficiencies;
	
	private final static Array<ImageButton>	flags			= new Array<ImageButton>();
	
	static TextureRegionDrawable			normalFlag		= new TextureRegionDrawable(new TextureRegion(blueFlag));
	static TextureRegionDrawable			priorityFlag	= new TextureRegionDrawable(new TextureRegion(redFlag));
	static TextureRegionDrawable			doneFlag		= new TextureRegionDrawable(new TextureRegion(greyFlag));
	
	
	
	public RoomPlanBox(Texture roomPlan) {
	
		super(roomPlan);
//		scaleImage(roomPlan);
		
	}
	
	
	@Override public void draw(SpriteBatch batch) {
	
		super.draw(batch);
		
	}
	
	
	public static void refreshFlags() {
	
		removeFlags();
		for (Element report : deficiencies)
			placeFlag(report);
		
	}
	
	
	public static void placeFlags(Array<Element> deficiencies) {
	
		RoomPlanBox.deficiencies = deficiencies;
		
		removeFlags();
		
		for (Element report : deficiencies)
			placeFlag(report);
	}
	
	
	public void placeFlags() {
	
		removeFlags();
		
		
		for (Element report : deficiencies)
			placeFlag(report);
	}
	
	
	public static void removeFlags() {
	
		for (ImageButton flag : flags)
			Qdap.stage.getRoot().removeActor(flag);
		flags.clear();
	}
	
	/**
	 * Creates and places a flag
	 * 
	 * @param report
	 */
	static void placeFlag(Element report) {
	
		
		ImageButton mark;
		
		boolean showDone = FlagSelectWindow.showDone.isChecked();
		boolean showPriority = FlagSelectWindow.showPriority.isChecked();
		boolean hideDone = FlagSelectWindow.hideDone.isChecked();
		
		/* Is Completed */
		if (report.get("completed").equals("true")) {
			if (hideDone || showPriority)
				return;
			mark = new ImageButton(doneFlag);
			
			/* Is Normal */
		} else if (!report.get("priority").equals("true") && !report.get("completed").equals("true")) {
			if (showDone || showPriority)
				return;
			mark = new ImageButton(normalFlag);
			/* Is Priority */
		} else {
			if (showDone)
				return;
			mark = new ImageButton(priorityFlag);
		}
		
		mark.setWidth(blueFlag.getWidth());
		mark.setHeight(blueFlag.getHeight());
		
		
		mark.setPosition(
				(Integer.parseInt(report.getChildByName("coordinates")
										.getAttribute("x")) + shiftX),
				((Gdx.graphics.getHeight() - Integer.parseInt(
													report.getChildByName("coordinates")
															.getAttribute("y"))) - shiftY));
		mark.addListener(RoomScreen.flagListen);
		
		mark.setName(report.getAttribute("reportID"));
		
		flags.add(mark);
		Qdap.stage.addActor(mark);
		
	}
	
	/** x and y are internal plan coordinates in pixels. */
//	public static void placeFlag(float x, float y) {
//	
//		ImageButton mark = new ImageButton(normalFlag);
//		mark.setWidth(blueFlag.getWidth());
//		mark.setHeight(blueFlag.getHeight());
//		
//		mark.setPosition(x + innerX - 4,
//				(Gdx.graphics.getHeight() - y - innerY) - mark.getHeight() / 2 - 3);
//		mark.addListener(RoomScreen.flagListen);
////		mark.setName(report.getAttribute("reportID"));
//		
//		flags.add(mark);
//		Qdap.stage.addActor(mark);
//	}
	
	
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {
	
		float differenceX = this.getX();
		float differenceY = this.getY();
		
		if (super.pan(x, y, deltaX, deltaY)) {
			differenceX -= this.getX();
			differenceY -= this.getY();
			shiftX -= differenceX;
			shiftY -= differenceY;
			updateFlags(differenceX, differenceY);
		}
		return false;
	}
	
	
	private void updateFlags(float differenceX, float differenceY) {
	
		for (ImageButton flag : flags)
			flag.setPosition(flag.getX() - differenceX, flag.getY() + differenceY);
	}
	
	
	@Override public boolean longPress(float x, float y) {
	
		if (this.getBoundingRectangle().contains(x, y)) {
			
			Qdap.roomScreen.makeReportWindow(x - shiftX, y - shiftY, "New Deficiency");
			return true;
		}
		
		return false;
	}
	
	
}
