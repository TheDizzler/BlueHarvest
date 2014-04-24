package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * 
 * @author T-Dawg
 */
public class RoomJobWindow extends WorkWindow {
	
//	private final Array<TextButton>	roughInsBtns	= new Array<TextButton>();
	
	
//	private final Label		title			= new Label(" ", Qdap.uiSkin);
	
	
	
	private static final Table	roughinsTable	= new Table();
	private static final Table	finishesTable	= new Table();
	
//	private final boolean		buttonsCreated	= false;
	
	int							pad				= 1;
	
	
	public RoomJobWindow(Qdap qd) {
	
		super(qd, BOTTOM);
		
	}
	
	
	@Override protected void initializeTabs() {
	
		tabOpen.setPosition(tabOpenX, tabOpenY);
		tabOpen.addListener(tabListener);
		tabClose.setPosition(tabOpenX, tabOpenY);
		tabClose.addListener(tabListener);
		
		
		currentTab = tabClose;
		currentTab.setPosition(tabOpenX, tabOpenY);
		
		isOpen = true;
	}
	
	@Override protected void initializeDock() {
	
		dock = new Window("", Qdap.uiSkin);
		((Window) dock).setKeepWithinStage(false);
		((Window) dock).setMovable(false);
		
		
		dock.row();
		
//		this.add(title).center().top().colspan(3);
//		title.setColor(Color.BLUE);
		
		dock.row();
		dock.add(roughinsTable).padRight(25);
		dock.add(finishesTable);
		
		dock.setBounds(dockOpenX, dockOpenY, dockWidth, dockHeight);
//		System.out.println("dockClosedY: " + dockClosedY);
		
		createJobBtns();
		
//		if (dock.getWidth() > 6.5 * (Qdap.btnWdth + pad))
//			dock.setWidth((6.5f * (Qdap.btnWdth + pad)));
//		else if (dock.getWidth() < 6 * (Qdap.btnWdth + pad))
//			dock.setWidth((6.5f * (Qdap.btnWdth + pad)));
		
		dockWidth = (int) (6.5f * (Qdap.btnWdth + pad));
//		dockWidth = Gdx.graphics.getWidth() * 7 / 8 - dockOpenX * 2;
		dockOpenX = Gdx.graphics.getWidth() / 2 - dockWidth / 2;
		tabOpenX = (dockOpenX + dockWidth) / 2 - tabWidth / 2;
		tabClosedX = tabOpenX;
		dock.setBounds(dockOpenX, dockOpenY, dockWidth, dockHeight);
		
	}
	
	/**
	 * Parses XML for room numbers and creates buttons.
	 * 
	 * @param currentFloor
	 */
	public void createJobBtns() {
	
//		if (!buttonsCreated ) {
		
		
		roughinsTable.add(new Label("ROUGH-INS", Qdap.uiSkin)).colspan(3);
		roughinsTable.row();
		roughinsTable.add(new TextButton("Framing", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		roughinsTable.add(new TextButton("Mechanical", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		roughinsTable.add(new TextButton("Electrical", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		roughinsTable.row();
		roughinsTable.add(new TextButton("Security", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		roughinsTable.add(new TextButton("HVAC", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.row();
		roughinsTable.add(new TextButton("Insulation", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		
		finishesTable.add(new Label("FINISHES", Qdap.uiSkin)).colspan(3);
		finishesTable.row();
		finishesTable.add(new TextButton("Drywall", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.add(new TextButton("Paint", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.add(new TextButton("Mechanical", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.row();
		finishesTable.add(new TextButton("Electrical", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.add(new TextButton("Flooring", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.add(new TextButton("Cabinets", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.row();
		finishesTable.add(new TextButton("Countertops", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		finishesTable.add(new TextButton("Finish Carp.", Qdap.uiSkin)).space(pad).width(
				Qdap.btnWdth).height(Qdap.btnHeight);
		
		
		
//		buttonsCreated = true;
//		}
	}
	
	
	
//	@Override public void setTitle(String string) {
//
//		title.setText(string);
//	}
	
	
}
