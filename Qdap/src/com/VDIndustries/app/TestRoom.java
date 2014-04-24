package com.VDIndustries.app;

import java.io.IOException;

import com.VDIndustries.clientInfo.RoomProfile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;


/**
 * Loads first room in XML file.
 * 
 * @author T-Dawg
 */
public class TestRoom extends RoomScreen {
	
	
	public TestRoom(Qdap qd) {
	
		super(qd);
		Element root;
		try {
			root = new XmlReader().parse(Gdx.files.internal("client/testProject.xml"));
			
//			Element floor = root.getChildByName("floor");
//			Element roomElem = floor.getChildByName("room");
			Element floor = null;
			Array<Element> floors = root.getChildrenByName("floor");
			for (Element flr : floors)
				if (flr.getAttribute("floorID").equals("Floor 3"))
					floor = flr;
			Array<Element> roomElems = floor.getChildrenByName("room");
			for (Element roomElem : roomElems)
				if (roomElem.getAttribute("no").equals("Test"))
					currentRoom = new RoomProfile(roomElem);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
