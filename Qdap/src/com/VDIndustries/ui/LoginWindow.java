package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;


public class LoginWindow extends Window {
	
	static final Label		userLbl		= new Label("User name", Qdap.uiSkin);
	static final Label		pwLbl		= new Label("Password", Qdap.uiSkin);
	static final Label		projLbl		= new Label("Project", Qdap.uiSkin);
	
	// How should save login info?
	TextField				userField;
	TextField				pwField;
	TextField				projectField;
	
	
	
	public static final TextButton	buttonGo	= new TextButton("Go", Qdap.uiSkin);
	
	public LoginWindow() {
		super("Login", Qdap.uiSkin);
		
		userField = new TextField("", Qdap.uiSkin);
		pwField = new TextField("", Qdap.uiSkin);
		projectField = new TextField("", Qdap.uiSkin);
		
		buttonGo.pad(10, 30, 10, 30);
		
//		this.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/Qdap-logob.png")))));
		
		buttonGo.addListener(Qdap.universalBtnListener);
		
		this.setBounds(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16,
				Gdx.graphics.getWidth() * 3 / 4, Gdx.graphics.getHeight() / 2.5f);
		this.setMovable(false);
		
		this.row();
		this.add(userField);
		this.row();
		this.add(userLbl);
		this.row();
		this.add(pwField);
		this.row();
		this.add(pwLbl);
		this.row();
		this.add(projectField);
		this.row();
		this.add(projLbl);
		this.row();
		this.add(buttonGo).align((int) (this.getWidth() - buttonGo.getWidth() / 2));
	}
}
