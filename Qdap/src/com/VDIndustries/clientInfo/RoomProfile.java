package com.VDIndustries.clientInfo;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Stores individual room info.
 * 
 * @author T-Dawg
 * 
 */
public class RoomProfile {
	
	final Element			roomElem;
	
	public Texture			roomPlan;
	public String			roomNo;
	
	public Element			currentTrade;
	
	/** All deficiencies in a room, regardless of trade. */
	public Array<Element>	deficiencies;
	
	
	public RoomProfile(Element room) {
	
		roomElem = room;
		roomNo = roomElem.get("no");
		
		
		Qdap.am.load(roomElem.getChildByName("roomplan").getAttribute("roomsrc"), Texture.class);
//		roomPlan = new Texture(Gdx.files.internal(
//										roomElem.getChildByName("roomplan").getAttribute("roomsrc")));
//		
		currentTrade = roomElem.getChildByName("trade");
		
		deficiencies = new Array<Element>();
		
		buildReports();
	}
	
	public void initializeRoom() {
		
		roomPlan = Qdap.am.get(roomElem.getChildByName("roomplan").getAttribute("roomsrc"));
		
//		buildReports();
		
	}
	
	
	/**
	 * Builds reports from Trade element.
	 * Must be called after a report is added.
	 */
	public void buildReports() {
	
//		System.out.println("Build reports called");
		try {
			deficiencies = currentTrade.getChildrenByName("deficiency");
		} catch (Exception e) {
			System.out.println("no deficiency found in " + roomNo);
		}
	}
}
