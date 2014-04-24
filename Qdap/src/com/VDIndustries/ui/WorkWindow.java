package com.VDIndustries.ui;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public abstract class WorkWindow extends Stage {
	
	public static final int	LEFT			= 0, TOP = 1, RIGHT = 2, BOTTOM = 3;
	
	protected final Qdap	qdap;
	
	protected int			dockWidth;
	protected int			dockHeight;
	protected int			dockOpenX;
	protected int			dockOpenY;
	protected int			dockClosedX;
	protected int			dockClosedY;
	
	
	protected int			tabWidth;
	protected int			tabHeight;
	protected int			tabOpenX;
	protected int			tabOpenY;
	protected int			tabClosedX;
	protected int			tabClosedY;
	
	
	protected boolean		isOpen			= false;
	protected boolean		opening			= false;
	protected boolean		isClosed		= true;
	
	public final TextButton	hudBackBtn		= new TextButton("Back", Qdap.uiSkin);
	public final TextButton	hudMainBtn		= new TextButton("Main", Qdap.uiSkin);
	public final TextButton	hudDummyBtn		= new TextButton("Dummy", Qdap.uiSkin);
	public final TextButton	hudLogOutBtn	= new TextButton("LogOut", Qdap.uiSkin);
	
	public Table			dock;
	
	
	public ImageButton		currentTab;
	ImageButton				tabOpen;
	ImageButton				tabClose;
	TextureRegionDrawable	tabOpenTexture;
	TextureRegionDrawable	tabCloseTexture;
	
	/* A WorkWindow self-reference for use by listeners*/
	protected WorkWindow	this_;
	
	protected TabListener	tabListener;
	
	
	/**
	 * @param qd
	 * @param edge Which side of screen to attach to
	 */
	public WorkWindow(Qdap qd, int edge) {
	
		this.this_ = this;
		qdap = qd;
		
		setEdge(edge);
		initializeDock();
		
		
		tabOpen = new ImageButton(tabOpenTexture);
		tabClose = new ImageButton(tabCloseTexture);
		
		initializeTabs();
		
		
		/* Add the dock and tab to the stage. */
		addActor(tabClose);
		addActor(tabOpen);
		addActor(currentTab);
		addActor(dock);
	}
	
	
	
	protected abstract void initializeTabs();
	
	
	
	/**
	 * Here you create the dock as a something that is a Table.
	 * A dock of some type that inherits from Table MUST be
	 * initialized here.
	 */
	protected abstract void initializeDock();
	
	/***/
	@Override public void draw() {
	
		super.draw();
	}
	

	
	
	public void openTab() {
	
		this.tabListener.openTab();
	}
	
	public void closeTab() {
	
		this.tabListener.closeTab();
	}
	
	
	private void setEdge(int edge) {
	
		/* An attempt at adaptive design */
		switch (edge) {
			case LEFT:
				
				dockWidth = Gdx.graphics.getWidth() / 8;
				dockHeight = Gdx.graphics.getHeight() / 2;
				dockOpenX = 0;
				dockOpenY = Gdx.graphics.getHeight() / 2 - dockHeight / 2;
				dockClosedX = -dockWidth;
				dockClosedY = dockOpenY;
				
				
				
				if (Gdx.graphics.getWidth() <= 960) {
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab horizontal open (16x64).png"))));
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab horizontal close (16x64).png"))));
					tabWidth = 16;
					tabHeight = 64;
				} else {
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab horizontal open (32x128).png"))));
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab horizontal close (32x128).png"))));
					tabWidth = 32;
					tabHeight = 128;
				}
				
				tabOpenX = dockWidth;
				tabOpenY = (dockOpenY + dockHeight) / 2 + tabHeight / 2;
				tabClosedX = 0;
				tabClosedY = tabOpenY;
				
				tabListener = new TabListenerLeft(this);
				break;
			
			
			
			case TOP:
				if (Gdx.graphics.getWidth() <= 960) {
					
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical close (16x64).png"))));
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical open (16x64).png"))));
				} else {
					
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical close (32x128).png"))));
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical open (32x128).png"))));
				}
				break;
			
			
			case BOTTOM:
				
				
				dockWidth = Gdx.graphics.getWidth() / 2;
				dockHeight = Gdx.graphics.getHeight() / 2;
				dockOpenX = Gdx.graphics.getWidth() / 8;
				dockOpenY = 0;
				dockClosedX = dockOpenX;
				dockClosedY = -dockHeight;
				
				if (Gdx.graphics.getWidth() <= 960) {
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical open (64x16).png"))));
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical close (64x16).png"))));
					tabWidth = 64;
					tabHeight = 16;
				} else {
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical open (128x32).png"))));
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab vertical close (128x32).png"))));
					tabWidth = 128;
					tabHeight = 32;
				}
				
				tabOpenX = (dockOpenX + dockWidth) / 2 - tabWidth / 2;
				tabOpenY = dockOpenY + dockHeight;
				tabClosedX = tabOpenX;
				tabClosedY = 0;
				tabListener = new TabListenerBottom(this);
				break;
			
			
			
			case RIGHT:
				
				dockWidth = Gdx.graphics.getWidth() / 3;
				dockHeight = Gdx.graphics.getHeight() / 4;
				dockOpenX = Gdx.graphics.getWidth() - dockWidth;
				dockOpenY = Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/3 - dockHeight/2;
				dockClosedX = Gdx.graphics.getWidth();
				dockClosedY = dockOpenY;
				
				if (Gdx.graphics.getWidth() <= 960) {
					
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab right horizontal open (16x64).png"))));
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab right horizontal close (16x64).png"))));
					
					tabWidth = 16;
					tabHeight = 64;
				} else {
					
					tabOpenTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab right horizontal open (32x128).png"))));
					tabCloseTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gui/widgets/tab right horizontal close (32x128).png"))));
					tabWidth = 32;
					tabHeight = 128;
				}
				
				tabOpenX = dockOpenX - tabWidth;
				tabOpenY = dockOpenY + dockHeight / 2 - tabHeight / 2;
				tabClosedX = Gdx.graphics.getWidth()- tabWidth;
				tabClosedY = tabOpenY;
				
				tabListener = new TabListenerRight(this);
				break;
		}
	}
}
