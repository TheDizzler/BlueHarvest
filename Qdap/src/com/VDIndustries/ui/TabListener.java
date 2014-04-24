package com.VDIndustries.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public abstract class TabListener extends ClickListener {
	
	/* Reference to the Widget that owns the button that this listener
	 * belongs to. */
	protected WorkWindow	this_;
	
	
	public TabListener(WorkWindow ww) {
	
		this_ = ww;
		
	}
	
	@Override public abstract void touchDragged(InputEvent event, float x, float y, int pointer);
	
	public abstract void openTab();
	
	public abstract void closeTab();
	
	@Override public void clicked(InputEvent event, float x, float y) {
	
		
		if (!this_.opening) {
			if (this_.isOpen)
				closeTab();
			else
				openTab();
		}
	}
	
	/** Actions to perform when touchUp while dragging */
	@Override public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		super.touchUp(event, x, y, pointer, button);
	}
}

/**
 * Listener for opening/closing dock.
 */
class TabListenerLeft extends TabListener {
	
	
	public TabListenerLeft(WorkWindow ww) {
	
		super(ww);
	}
	
	@Override public void touchDragged(InputEvent event, float x, float y, int pointer) {
	
		this_.currentTab.setX(Gdx.input.getX());
		this_.dock.setX(this_.currentTab.getX() - this_.dockWidth);
		this_.opening = true;
		
		if (this_.dock.getX() > 0) {
			openTab();
		} else if (this_.dock.getX() < -this_.dockWidth) {
			closeTab();
		}
		
	}
	
	@Override public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		
		if (this_.opening) {
			if (this_.isClosed)
				openTab();
			if (this_.dock.getX() > -this_.dockWidth / 2)
				closeTab();
			else
				openTab();
		}
		super.touchUp(event, x, y, pointer, button);
	}
	
	@Override public void openTab() {
	
		this_.dock.setPosition(this_.dockOpenX, this_.dockOpenY);
		this_.currentTab.setPosition(this_.tabOpenX, this_.tabOpenY);
		this_.tabClose.setPosition(this_.tabOpenX, this_.tabOpenY);
		this_.tabOpen.setVisible(false);
		this_.currentTab = this_.tabClose;
		this_.tabClose.setVisible(true);
		this_.isOpen = true;
		this_.opening = false;
		this_.isClosed = false;
	}
	
	
	@Override public void closeTab() {
	
		this_.dock.setPosition(this_.dockClosedX, this_.dockClosedY);
		this_.currentTab.setPosition(this_.tabClosedX, this_.tabClosedY);
		this_.tabOpen.setPosition(this_.tabClosedX, this_.tabClosedY);
		this_.tabClose.setVisible(false);
		this_.currentTab = this_.tabOpen;
		this_.tabOpen.setVisible(true);
		this_.isOpen = false;
		this_.opening = false;
		this_.isClosed = true;
	}
}

/**
 * Listener for opening/closing dock.
 */
class TabListenerRight extends TabListener {
	
	
	public TabListenerRight(WorkWindow ww) {
	
		super(ww);
	}
	
	@Override public void touchDragged(InputEvent event, float x, float y, int pointer) {
	
		this_.currentTab.setX(Gdx.input.getX());
		this_.dock.setX(this_.currentTab.getX() + this_.tabWidth);
		this_.opening = true;
		
		if (this_.dock.getX() < this_.dockOpenX) {
			openTab();
		} else if (this_.dock.getX() > this_.dockClosedX ) {
			closeTab();
		}
		
	}
	
	@Override public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		
		if (this_.opening) {
			if (this_.isClosed)
				openTab();
			if (this_.dock.getX() > this_.dockClosedX - this_.dockWidth / 2)
				closeTab();
			else
				openTab();
		}
		super.touchUp(event, x, y, pointer, button);
	}
	
	@Override public void openTab() {
	
		this_.dock.setPosition(this_.dockOpenX, this_.dockOpenY);
		this_.currentTab.setPosition(this_.tabOpenX, this_.tabOpenY);
		this_.tabClose.setPosition(this_.tabOpenX, this_.tabOpenY);
		this_.tabOpen.setVisible(false);
		this_.currentTab = this_.tabClose;
		this_.tabClose.setVisible(true);
		this_.isOpen = true;
		this_.opening = false;
		this_.isClosed = false;
	}
	
	
	@Override public void closeTab() {
	
		this_.dock.setPosition(this_.dockClosedX, this_.dockClosedY);
		this_.currentTab.setPosition(this_.tabClosedX, this_.tabClosedY);
		this_.tabOpen.setPosition(this_.tabClosedX, this_.tabClosedY);
		this_.tabClose.setVisible(false);
		this_.currentTab = this_.tabOpen;
		this_.tabOpen.setVisible(true);
		this_.isOpen = false;
		this_.opening = false;
		this_.isClosed = true;
	}
}


/**
 * Listener for bottom edge tab.
 */
class TabListenerBottom extends TabListener {
	
	
	public TabListenerBottom(WorkWindow ww) {
	
		super(ww);
	}
	
	
	@Override public void touchDragged(InputEvent event, float x, float y, int pointer) {
	
		
		this_.currentTab.setY(Gdx.graphics.getHeight() - Gdx.input.getY());
		this_.dock.setPosition(this_.dockOpenX, this_.currentTab.getY() - this_.dockHeight);
		this_.opening = true;
		
		if (this_.dock.getY() < -this_.dockHeight) {
			closeTab();
		} else if (this_.dock.getY() > this_.dockOpenY) {
			openTab();
			
		}
		
	}
	
	
	@Override public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	
		if (this_.opening) {
			if (this_.dock.getY() + this_.dockHeight > this_.dockHeight / 2)
				closeTab();
			else
				openTab();
		}
		super.touchUp(event, x, y, pointer, button);
	}
	

	
	@Override public void openTab() {
		
		
		this_.dock.setPosition(this_.dockOpenX, this_.dockOpenY);
		this_.currentTab.setPosition(this_.tabOpenX, this_.tabOpenY);
		this_.tabClose.setPosition(this_.tabOpenX, this_.tabOpenY);
		this_.tabOpen.setVisible(false);
		this_.currentTab = this_.tabClose;
		this_.tabClose.setVisible(true);
		this_.isOpen = true;
		this_.opening = false;
		this_.isClosed = false;
	}
	
	
	@Override public void closeTab() {
	
		this_.dock.setPosition(this_.dockClosedX, this_.dockClosedY);
		this_.currentTab.setPosition(this_.tabClosedX, this_.tabClosedY);
		this_.tabOpen.setPosition(this_.tabClosedX, this_.tabClosedY);
		this_.tabClose.setVisible(false);
		this_.currentTab = this_.tabOpen;
		this_.tabOpen.setVisible(true);
		this_.isOpen = false;
		this_.opening = false;
		this_.isClosed = true;
	}
}
