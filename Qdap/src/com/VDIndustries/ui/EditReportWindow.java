package com.VDIndustries.ui;


import com.VDIndustries.app.Qdap;
import com.VDIndustries.app.RoomScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;


/** Change or make a new Deficiency Report. */
public class EditReportWindow extends Window {
	
	final String[]	objectsArray	= { "None", " ", "Drywall", "Tub/Showerbase", "Towel Bar", "Glass Door", "Slider Door", "Pocket Door", "Shower Niche",
									"Bench", "Door", "Closet", "Exterior Cladding", "Canopy", "Railing", "Shear-Wall", "Electrical Panel",
									"Communication Box", "Vanity", "Cabinet", "Desk Space" };
	Array<String>	objects			= new Array<String>();
	
	final String[]	itemsArray		= { "None", " ", "Backing", "Joist", "Plywood", "Wall", "Wing Wall", "Stud", "Plate", "Dimensions", "Rough-Opening",
									"Poly-Liner", "Header", "Hold-Down", "Anchor", "Jamb", "Cripple" };
	
	Array<String>	items			= new Array<String>();
	
	final String[]	verbsArray		= { "None", " ", "Missing", "Crowned", "Bowed", "Split", "Incorrect", "Not Plumb", "Not Square", "Not Level",
									"Not Installed", "Not Aligned", "Off", "Undersized", "Oversized", "Reduce", "Enlarge", "Box-out",
									"Replace", "Proud", "Extend", "Relocate", "Subtract", "Add", "Center", "To Be" };
	
	Array<String>	verbs			= new Array<String>();
	
	final String[]	directionsArray	= { "None", " ", "at", "Below", "Above", "Right of", "Left of", "Behind", "Infront", "To", "By" };
	Array<String>	directions		= new Array<String>();
	
	final String[]	locationsArray	= { "None", " ", "Corner", "Floor", "Ceiling", "Kitchen", "Living", "Dining", "Bathroom", "Entry",
									"Laundry", "Closet", "Storage", "Den", "Opposite Side", "TV Center", "Window", "Suite Entry" };
	Array<String>	locations		= new Array<String>();
	
	List<String>	listObjects;
	List<String>	listItems;
	List<String>	listVerbs;
	List<String>	listDirections;
	List<String>	listLocations;
	
	CheckBox		priorityCheck	= new CheckBox("Priority", Qdap.uiSkin);
	CheckBox		completedCheck	= new CheckBox("Task Complete", Qdap.uiSkin);
	TextButton		saveButton		= new TextButton("Save", Qdap.uiSkin);
	TextButton		closeButton		= new TextButton("X", Qdap.uiSkin);
	
	private Element	report;
	
	static private float	lastX;
	static private float	lastY;
	
	Window reportWindow;
	
	
	public EditReportWindow(String title, final float x, final float y) {
	
		super(title, Qdap.uiSkin);
		
		this.reportWindow = this;
		
		this.setModal(true);
		this.setPosition(lastX, lastY);
		
//		for (String str : objectsArray) {
//			objects.add(str);
//		}
		objects.addAll(objectsArray);
		items.addAll(itemsArray);
		verbs.addAll(verbsArray);
		directions.addAll(directionsArray);
		locations.addAll(locationsArray);
		
		listObjects = new List<String>(Qdap.uiSkin);
		listObjects.setItems(objects);
		listObjects.getSelection().setMultiple(false);
		listObjects.getSelection().setRequired(false);
		listObjects.setName("list");
		
		ScrollPane scrollerObj = new ScrollPane(listObjects);
		
		
		listItems = new List<String>(Qdap.uiSkin);
		listItems.setItems(items);
		listItems.getSelection().setMultiple(false);
		listItems.getSelection().setRequired(false);
		listItems.setName("item");
		
		ScrollPane scrollerItem = new ScrollPane(listItems);
		
		listVerbs = new List<String>(Qdap.uiSkin);
		listVerbs.setItems(verbs);
		listVerbs.getSelection().setMultiple(false);
		listVerbs.getSelection().setRequired(false);
		listVerbs.setName("verb");
		
		ScrollPane scrollerVerb = new ScrollPane(listVerbs);
		
		listDirections = new List<String>(Qdap.uiSkin);
		listDirections.setItems(directions);
		listDirections.getSelection().setMultiple(false);
		listDirections.getSelection().setRequired(false);
		listDirections.setName("direction");
		
		ScrollPane scrollerDir = new ScrollPane(listDirections);
		
		listLocations = new List<String>(Qdap.uiSkin);
		listLocations.setItems(locations);
		listLocations.getSelection().setMultiple(false);
		listLocations.getSelection().setRequired(false);
		listLocations.setName("location");
		
		ScrollPane scrollerLoc = new ScrollPane(listLocations);
		
		saveButton.addListener(new ClickListener() {
			
			private int	newX;
			private int	newY;
			
			@Override public void clicked(InputEvent event, float clickX, float clickY) {
			
				this.newX = (int) Math.floor(x);
				this.newY = (int) Math.floor(y);
				
				//Save to XML
//				StringWriter writer = new StringWriter();
//				XmlWriter xml = new XmlWriter(writer);
//				try {
//					xml.element("deficiency")
//						.attribute("reportID", newReportID())
//						.element("coordinates")
//						.attribute("x", "" + newX)
//						.attribute("y", "" + newY)
//						.element("description")
//						.element("object", listObjects.getSelected())
//						.element("item", listItems.getSelected())
//						.element("verb", listVerbs.getSelected())
//						.element("direction", listDirections.getSelected())
//						.element("location", listLocations.getSelected());
//					xml.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				
//				System.out.println(writer);
				
				/* Current strategy:
				 * Build element and add to the currently viewed trade element. Rebuild deficiency list,
				 * SAVE new deficiency list (to file??) and show updated tacks.
				 */
				if (report == null) {
					Element newReport = new Element("deficiency", RoomScreen.currentRoom.currentTrade);
					newReport.setAttribute("reportID", newReportID());
					
					Element completed = new Element("completed", newReport);
					completed.setText(completedCheck.isChecked() + "");
					
					Element priority = new Element("priority", newReport);
					priority.setText(priorityCheck.isChecked() + "");
					
					Element cords = new Element("coordinates", newReport);
					cords.setAttribute("x", "" + newX);
					cords.setAttribute("y", "" + newY);
					Element desc = new Element("description", newReport);
					Element obj = new Element("object", desc);
					obj.setText(listObjects.getSelected());
					Element item = new Element("item", desc);
					item.setText(listItems.getSelected());
					Element verb = new Element("verb", desc);
					verb.setText(listVerbs.getSelected());
					Element direction = new Element("direction", desc);
					direction.setText(listDirections.getSelected());
					Element location = new Element("location", desc);
					location.setText(listLocations.getSelected());
					
					
					desc.addChild(obj);
					desc.addChild(item);
					desc.addChild(verb);
					desc.addChild(direction);
					desc.addChild(location);
					
					newReport.addChild(completed);
					newReport.addChild(priority);
					newReport.addChild(cords);
					newReport.addChild(desc);
					
					
					RoomScreen.currentRoom.currentTrade.addChild(newReport);
					RoomScreen.currentRoom.buildReports();
					
					Qdap.roomScreen.defReport.loadDeficiency(newReport);
				} else { // Edit existing report
				
					report.getChildByName("completed").setText(completedCheck.isChecked() + "");
					report.getChildByName("priority").setText(priorityCheck.isChecked() + "");
					
					Element desc = report.getChildByName("description");
					desc.getChildByName("object").setText(listObjects.getSelected());
					desc.getChildByName("item").setText(listItems.getSelected());
					desc.getChildByName("verb").setText(listVerbs.getSelected());
					desc.getChildByName("direction").setText(listDirections.getSelected());
					desc.getChildByName("location").setText(listLocations.getSelected());
					
					Qdap.roomScreen.defReport.loadDeficiency(report);
				}
				
//				if (!Gdx.files.isLocalStorageAvailable()) {
//					System.out.println("CRITICAL ERROR: Local storage is unavailable!");
//					return;
//				}
				/*
				if (!Gdx.files.local("project/").isDirectory()) {
					
				}
				*/
//				
//				if (!Gdx.files.local("testProject.xml").exists()) {
//					System.out.println("Not a file");
//				}
				
				
				
				RoomPlanBox.placeFlags(RoomScreen.currentRoom.deficiencies);
				
				lastX = getX();
				lastY = getY();
				Qdap.roomScreen.removeWindow(reportWindow);
				
			}
		});
		
		closeButton.addListener(new ClickListener() {
			
			@Override public void clicked(InputEvent event, float clickX, float clickY) {
			
				lastX = getX();
				lastY = getY();
				Qdap.roomScreen.removeWindow(reportWindow);
			}
			
		});
		
		this.setSize(scrollerObj.getWidth() * 5 + priorityCheck.getWidth(),
				Gdx.graphics.getHeight() / 3);
		this.defaults().expand().fill().padBottom(12f);
		
		this.add(priorityCheck);
		this.add();
		this.add(completedCheck);
		this.add();
		this.add();
		this.add(closeButton);
		this.row();
		
		this.add(new Label("Specific Item", Qdap.uiSkin)).padLeft(12);
		this.add(new Label("Item", Qdap.uiSkin)).padLeft(12);
		this.add(new Label("Verb", Qdap.uiSkin)).padLeft(12);
		this.add(new Label("Direction", Qdap.uiSkin)).padLeft(12);
		this.add(new Label("Location", Qdap.uiSkin)).padLeft(12);
		
		this.row();
		this.add(scrollerObj).padLeft(12);
		this.add(scrollerItem).padLeft(12).width(scrollerObj.getWidth());
		this.add(scrollerVerb).padLeft(12);
		this.add(scrollerDir).padLeft(12);
		this.add(scrollerLoc).padLeft(12);
		
		
		
		this.add(saveButton);
		
	}
	
	/**
	 * Setups lists for editing
	 */
	public void setLists(Element rep) {
	
		
		this.report = rep;
		
		this.setTitle("Report #" + report.getAttribute("reportID")
						+ " : Room " + report.getParent().getParent().getAttribute("no"));
		
		Element desc = report.getChildByName("description");
		
		priorityCheck.setChecked(report.get("priority").equals("true"));
		completedCheck.setChecked(report.get("completed").equals("true"));
		
		setList(desc, listObjects, "object", objects);
		setList(desc, listItems, "item", items);
		setList(desc, listVerbs, "verb", verbs);
		setList(desc, listDirections, "direction", directions);
		setList(desc, listLocations, "location", locations);
	}
	
	private void setList(Element desc, List<String> list, String element, Array<String> array) {
	
		int x;
		String parse;
		
		try {
			parse = desc.get(element);
			x = array.indexOf(parse, false);
			if (x < 0)
				x = 0;
			list.setSelectedIndex(x);
		} catch (GdxRuntimeException gdxrt) {
			System.out.println("ERROR: no \"" + list.getName() + "\" item found in XML!");
			
			list.setSelectedIndex(0);
		}
	}
	
	
	private String newReportID() {
	
		return "r" + (int) Math.floor(Math.random() * 100000);
	}
}
