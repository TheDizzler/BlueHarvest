package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class ProjectWindow extends Table {
	
	public static TextButton	reports;
	public static TextButton	checklists;
	public static TextButton	photogal;
	public static TextButton	notes;
	
	public ProjectWindow() {
	
//		super("Projects", Qdap.uiSkin);
		
		int pad = 10;
		
		this.setBounds(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16,
				Gdx.graphics.getWidth() * 3 / 4, Gdx.graphics.getHeight() / 2.5f);
//		this.setMovable(false);
		
		this.add(reports = new TextButton("Reports", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		this.add(checklists = new TextButton("Checklists", Qdap.uiSkin)).space(pad).width(
				Qdap.btnWdth).height(Qdap.btnHeight);
		this.row();
		this.add(photogal = new TextButton("Photo Gallery", Qdap.uiSkin)).space(pad).width(
				Qdap.btnWdth).height(Qdap.btnHeight);
		this.add(notes = new TextButton("Notes", Qdap.uiSkin)).space(pad).width(Qdap.btnWdth).height(
				Qdap.btnHeight);
		
		
		
		reports.addListener(Qdap.universalBtnListener);
		checklists.addListener(Qdap.universalBtnListener);
		photogal.addListener(Qdap.universalBtnListener);
		notes.addListener(Qdap.universalBtnListener);
	}
}
