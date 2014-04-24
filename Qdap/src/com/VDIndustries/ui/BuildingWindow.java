package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;


public class BuildingWindow extends Table {

	public Array<TextButton> buttons;
	
	
	public BuildingWindow() {
//		super("Select Location", Qdap.uiSkin);
		
		this.setBounds(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16,
				Gdx.graphics.getWidth() * 3 / 4, Gdx.graphics.getHeight() / 2.5f);
//		this.setMovable(false);
		
		buttons = new Array<TextButton>();
	}
	
	/** Creates the button table.
		 Smart checking to make columns? */
	public void buildFloorButtons() {
		
		int space = 10;
		
		for (Element floor : Qdap.project.floors) {
			this.row();
			buttons.add(new TextButton(floor.getAttribute("floorID"), Qdap.uiSkin));
			this.add(buttons.peek()).space(space).width(Qdap.btnWdth).height(Qdap.btnHeight);
			buttons.peek().setName(floor.getAttribute("floorID"));
			buttons.peek().addListener(Qdap.universalBtnListener);
			buttons.peek().setWidth(Qdap.btnWdth);
			
		}
		
	}
}
