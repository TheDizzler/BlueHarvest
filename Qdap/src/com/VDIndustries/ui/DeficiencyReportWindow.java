package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.VDIndustries.app.RoomScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Displays Deficiency Report Info.
 * 
 * @author T-Dawg
 */
public class DeficiencyReportWindow extends Window {
	
	
	/* reportId */
	Element		defInfo;
	
	Label		description	= new Label("NO DESCRIPTION ENTERED", Qdap.uiSkin);
	TextButton	editBtn		= new TextButton("Edit...", Qdap.uiSkin);
	
	static private float	lastX, lastY;
	
	
	public DeficiencyReportWindow() {
	
		super("Blank", Qdap.uiSkin);
		
		
		this.setBounds(lastX, lastY,
				Gdx.graphics.getWidth() / 3,
				Gdx.graphics.getHeight() / 4);
		
		this.add(editBtn).top().right();
		row();
		this.add(description);
	}
	
	/** Parses and displays deficiency info from XML. */
	public void loadDeficiency(Element report) {
	
		defInfo = report;
		
		try {
			this.setTitle("Report #" + defInfo.getAttribute("reportID") + " : Room " + report.getParent().getParent().getAttribute(
					"no"));
			
			Element desc = defInfo.getChildByName("description");
			this.description.setText(desc.get("object") + " " +
										desc.get("item") + " " + desc.get("verb") + " " +
										desc.get("direction") + " " + desc.get("location"));
			
		} catch (GdxRuntimeException gdxrt) {
			System.out.println("ERROR: no child or text found in XML file!");
			
			this.description.setText(" ");
		}
		
		
		
		float newwidth = 10 * description.getText().length();
		if (newwidth < Gdx.graphics.getHeight() / 2)
			this.setWidth(Gdx.graphics.getHeight() / 2);
		else
			this.setWidth(newwidth);
		
		RoomScreen.editListener.report = defInfo;
		editBtn.addListener(RoomScreen.editListener);
	}
	
}
