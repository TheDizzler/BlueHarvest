package com.VDIndustries.clientInfo;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Stores all Project site info, including XML element info (root and floors)
 * floorProfiles.
 * 
 * @author T-Dawg
 * 
 */
public class Project implements Disposable {
	
	
	public static FileHandle	projectFile;
	public String				projectName;
	
	public static Element		root;
	public Array<Element>		floors;
	
	public Array<FloorProfile>	floorProfiles	= new Array<FloorProfile>();
	
	/** This is a git test. */
	public Project() {
		// testing pushing to remote
		System.out.println("Initializing Project...");
		
		
		try {
			
			projectFile = Gdx.files.internal("client/testProject.xml");
			root = new XmlReader().parse(projectFile);
			projectName = root.get("name");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads floor plan textures, stores the room elements, creates a new Floor
	 * Profile from the project xml file.
	 */
	public void loadFloorPlans() {
		
		floors = root.getChildrenByName("floor");
		for (Element floor : floors) {
			
			floorProfiles.add(new FloorProfile(floor.getAttribute("floorID"), floor));
		}
	}
	
	
	/** Builds floor profiles after textures all loaded. */
	public void initializeFloors() {
		
		for (FloorProfile floor : floorProfiles)
			floor.initializeFloor();
		
	}
	
	
	
	@Override public void dispose() {
	
		for (FloorProfile fp : floorProfiles)
			fp.floorPlan.dispose();
	}
}
