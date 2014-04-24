package com.VDIndustries.clientInfo;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Storage class for specific floors.
 * 
 * @author T-Dawg
 * 
 */
public class FloorProfile {
	
	public Texture				floorPlan;
	public Sprite				spriteFloor;
	public final String			floorName;
	
	private final Element		floorRoot;
	
	public final Array<Element>	roomElements;
	public Array<RoomProfile>	roomProfiles;
	
	/* Used for getRoomBoundaries */
	private final Rectangle		rect	= new Rectangle();
	
	
	public FloorProfile(String flrname, Element floor) {
	
		floorRoot = floor;
		floorName = flrname;
		Qdap.am.load(floor.getChildByName("floorplan").get("floorsrc"), Texture.class); //flrplan
		
		roomElements = floor.getChildrenByName("room");
		
		roomProfiles = new Array<RoomProfile>();
		loadRooms();
		
	}
	
	/** Builds the floor after textures loaded from asset manager */
	public void initializeFloor() {
	
		
		floorPlan = Qdap.am.get(floorRoot.getChildByName("floorplan").get("floorsrc"),
				Texture.class);
		spriteFloor = new Sprite(floorPlan);
		
		for (RoomProfile room : roomProfiles)
			room.initializeRoom();
		
//		roomProfiles = new Array<RoomProfile>();
		
		
//		loadRooms();
	}
	
	
	public void loadRooms() {
	
		for (Element room : roomElements) {
			roomProfiles.add(new RoomProfile(room));
//			System.out.println(room.get("no"));
		}
	}
	
//	
//	public Rectangle getRoomBoundary() {
//	
//		String temp = roomElements.get(0).getChildByName("touchButton").getAttribute("x");
//		rect.x = Integer.parseInt(temp);
//		temp = roomElements.get(0).getChildByName("touchButton").getAttribute("y");
//		rect.y = Integer.parseInt(temp);
//		temp = roomElements.get(0).getChildByName("touchButton").getAttribute("width");
//		rect.width = Integer.parseInt(temp);
//		temp = roomElements.get(0).getChildByName("touchButton").getAttribute("height");
//		rect.height = Integer.parseInt(temp);
//		return rect;
//	}
	
	
	
}
