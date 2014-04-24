package com.VDIndustries.app;

import com.VDIndustries.clientInfo.Project;
import com.VDIndustries.ui.HUD;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Qdap extends Game {
	
	public static Project					project;
	
	/* One listener to rule them all
	 * and in the darkness bind them. */
	public static UniversalButtonListener	universalBtnListener;
	
	/* For the Room Buttons. */
	public static RoomButtonListener		roomBtnListener;
	
	public static OrthographicCamera		camera;
	public static SpriteBatch				batch;
	
	static InputMultiplexer					im				= new InputMultiplexer();
	
	public static Stage						stage;
	public static HUD						hud;
	
	public static MainScreen				mainScreen;
	public static FloorScreen				floorScreen;
	public static RoomScreen				roomScreen;
	public static Screen					lastScreen;
	
	public static Skin						uiSkin;
	public static Image						logo;
	
	
	public static AssetManager				am;
	
	public static boolean					projectLoaded	= false;
	
	public static int						btnWdth			= 100;
	public static int						btnHeight		= 66;
	
	public static ReportsScreen				reportsScreen;
	
	
	@Override public void create() {
	
//		Texture.setEnforcePotImages(false);
		
		
		
		uiSkin = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		
		logo = new Image(new Texture(Gdx.files.internal("gui/qdap-logob.png")));
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		
		batch = new SpriteBatch();
		
		universalBtnListener = new UniversalButtonListener(this);
		roomBtnListener = new RoomButtonListener(this);
		
		stage = new Stage();
		hud = new HUD(this);
		
		
		im.addProcessor(hud);
		im.addProcessor(stage);
//		im.addProcessor(new GestureDetector(hud));
//		im.addProcessor(new GestureDetector(mainScreen));
		Gdx.input.setInputProcessor(im);
		
		Gdx.input.setCatchBackKey(true);
		
		Qdap.am = new AssetManager();
		
		mainScreen = new MainScreen(this);
		floorScreen = new FloorScreen();
		roomScreen = new RoomScreen(this);
//		reportsScreen = new ReportsScreen(project);
		
		/* These calls prevent graphics from continuously rendering. */
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering(); // Use this whenever screen needs to be refreshed.
		/* 
		 * If continuous rendering is set to false, the render() method will be
		 * called only when the following things happen:
		 * An input event is triggered
		 * Gdx.graphics.requestRendering() is called
		 * Gdx.app.postRunnable() is called
		 * 
		 * More info at: http://bitiotic.com/blog/2012/10/01/enabling-non-continuous-rendering-in-libgdx/
		 */
		
		setScreen(mainScreen);
//		TestScreen tt = new TestScreen();
		
//		TestRoom tt = new TestRoom();
//		setScreen(tt);
	}
	
	@Override public void dispose() {
	
		
		batch.dispose();
		uiSkin.dispose();
		stage.dispose();
		hud.dispose();
		mainScreen.dispose();
		floorScreen.dispose();
	}
}
