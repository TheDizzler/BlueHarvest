package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.VDIndustries.clientInfo.FloorProfile;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * For now, a temporary window for floor buttons and other handy things.
 * 
 * @author T-Dawg
 * 
 */
public class FloorInfoWindow extends Window {
	
	public final Array<Button>	buttonRooms	= new Array<Button>();
	
	
	
	public FloorInfoWindow() {
	
		super("Empty", Qdap.uiSkin);
		
//		this.setBounds(Gdx.graphics.getWidth() / 3 * 4 - 10, Gdx.graphics.getHeight() / 3 * 4 - 10,
//				Gdx.graphics.getWidth() / 4 - 20, Gdx.graphics.getHeight() / 4 * 3);
		this.setResizable(true);
	}
	
	/**
	 * Parses XML for room numbers and creates buttons.
	 * 
	 * @param currentFloor
	 */
	public void createRoomBtns(FloorProfile currentFloor) {
	
		int maxRow = (int) (this.getWidth() - this.getWidth() / 8);
		int count = 0;
		int row = 0;
		int pad = 10;
		this.row().minHeight(Qdap.btnHeight);
		
		for (Element room : currentFloor.roomElements) {
			
			TextButton temp = new TextButton(room.get("no"), Qdap.uiSkin);
			buttonRooms.add(temp);
			temp.setName(room.get("no"));
			temp.addListener(Qdap.roomBtnListener);
			temp.setSize(Qdap.btnWdth, Qdap.btnHeight);
			
			if ((temp.getWidth() + 4*pad)*count > maxRow) {
				count = 0;
				this.row().minHeight(Qdap.btnHeight);
				row++;
			}
			this.add(temp).width(Qdap.btnWdth).pad(pad);
			count++;
			
		}
		
		
	}
	
}
