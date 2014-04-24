package com.VDIndustries.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;


public class PlanBox extends Sprite implements GestureListener {
	
	
	/* Outside edge of border */
	protected static final int		outerX		= 35;
	protected static final int		outerY		= 15;
	protected static final int		outerWidth	= Gdx.graphics.getWidth() - (35) - 10;
	protected static final int		outerHeight	= Gdx.graphics.getHeight() / 3 * 2 - 12;
	
	/* Inside edge of border */
	protected static final int		frameSize	= 10;
	public static final int			innerX		= outerX + frameSize;
	public static final int			innerY		= outerY + frameSize;
	protected static final int		innerWidth	= outerWidth - 2 * frameSize;
	protected static final int		innerHeight	= outerHeight - 2 * frameSize;
	
//	protected final ShapeRenderer	frame;
	
	
	protected final static Texture	blueFlag	= new Texture(Gdx.files.internal("gui/report flag blue tight.png"));
	protected final static Texture	redFlag		= new Texture(Gdx.files.internal("gui/report flag red tight.png"));
	protected final static Texture	greyFlag	= new Texture(Gdx.files.internal("gui/report flag done tight.png"));
	
	protected static float			scaleFactor;
	
	protected boolean				touchedDown;
	public static float				shiftX		= innerX;
	public static float				shiftY		= innerY;
	
	
	public PlanBox(Texture roomPlan) {
	
		super(roomPlan);
		
//		frame = new ShapeRenderer();
//		frame.setProjectionMatrix(Qdap.camera.combined);
		
		this.flip(false, true);
		this.setPosition(innerX, innerY);
		
		
	}
	
	
	/** Scales plan to inner Frame. */
	protected void scaleImage(Texture roomPlan) {
	
		int roomW = roomPlan.getWidth();
		int roomH = roomPlan.getHeight();
		
		if ((roomW == innerWidth && roomH < innerHeight)
			|| (roomH == innerHeight && roomW < innerWidth)) {
			System.out.println("Good enough");
			return;
		}
		// if both dimensions bigger than box
		if (roomW >= innerWidth && roomH >= innerHeight) {
			//find biggest edge
			//scale using that edge
			if (roomW - innerWidth >= roomH - innerHeight) {
				System.out.println("Shrink width");
				shrinkToBoxWidth(roomW, roomH);
			} else {
				System.out.println("Shrink height");
				shrinkToBoxHeight(roomW, roomH);
			}
		}
		// if only width bigger than box 
		else if (roomW >= innerWidth && roomH <= innerHeight) {
			System.out.println("shrink width");
			shrinkToBoxWidth(roomW, roomH);
			
		}
		
		// if only height bigger than box
		else if (roomH >= innerHeight && roomW <= innerWidth) {
			System.out.println("Shrink Height");
			shrinkToBoxHeight(roomW, roomH);
			
		}
		
		
		// if both dimensions smaller than box
		else if (roomW <= innerWidth && roomH <= innerHeight) {
			//find biggest edge
			//scale using that edge
			if (innerWidth - roomW <= innerHeight - roomH) {
				System.out.println("Grow width");
				shrinkToBoxWidth(roomW, roomH);
			} else {
				System.out.println("Grow Height");
				shrinkToBoxHeight(roomW, roomH);
			}
		}
		// if only width smaller than box 
		else if (roomW <= innerWidth) {
			System.out.println("roomW < innerWidth");
			shrinkToBoxWidth(roomW, roomH);
			
		}
		
		// if only height smaller than box
		else if (roomH <= innerHeight) {
			System.out.println("roomH < innerHeight");
			shrinkToBoxHeight(roomW, roomH);
			
		}
	}
	
	
	private void shrinkToBoxHeight(int roomW, int roomH) {
	
		
		float newWidth = (float) roomW / (float) roomH * innerHeight;
		scaleFactor = newWidth / innerWidth;
		System.out.println("scaleFactor: " + scaleFactor);
		this.setSize(newWidth, innerHeight);
	}
	
	
	private void shrinkToBoxWidth(int roomW, int roomH) {
	
		float newHeight = (float) roomH / (float) roomW * innerWidth;
		scaleFactor = newHeight / innerHeight;
		System.out.println("scaleFactor: " + scaleFactor);
		this.setSize(innerWidth, newHeight);
	}
	
	
	public void draw(SpriteBatch batch) {
	
		super.draw(batch);
	}
	
	/** Draws a frame to contain a plan. */
	public void drawFrame() {
	
		
//		frame.begin(ShapeType.Filled);
//		//outer frame
//		frame.setColor(Color.RED);
//		frame.rect(outerX, outerY, outerWidth, outerHeight);
//		
//		// inner frame, where plans should be
//		frame.setColor(0, 1, 1, 0);
//		frame.rect(innerX, innerY, innerWidth, innerHeight);
//		frame.end();
	}
	
	
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {
	
		
		if (touchedDown) {
			
			this.setPosition(this.getX() + deltaX, this.getY() + deltaY);
			
			if (this.getX() < -this.getWidth() / 2)
				this.setX(-this.getWidth() / 2);
			
			else if (this.getX() + this.getWidth() > Gdx.graphics.getWidth() + this.getWidth() / 2)
				this.setX(Gdx.graphics.getWidth() - this.getWidth() / 2);
			
			if (this.getY() > Gdx.graphics.getHeight() - this.getHeight() / 2) // move too far down
				this.setY(Gdx.graphics.getHeight() - this.getHeight() / 2);
			
			else if (this.getY() + this.getHeight() / 2 < 0)
				this.setY(-this.getHeight() / 2);
			
			return true;
			
		}
		return false;
	}
	
	
	@Override public boolean touchDown(float x, float y, int pointer, int button) {
	
		if (this.getBoundingRectangle().contains(x, y)) {
			System.out.println("touched!");
			touchedDown = true;
		}else
			touchedDown = false;
		return touchedDown;
	}
	
	
	@Override public boolean zoom(float initialDistance, float distance) {
	
		float ratio = initialDistance / distance;
		
		scaleFactor = scaleFactor / ratio;
		float newWidth = getWidth() + ratio;
		float newHeight = getHeight() + ratio;
		this.setSize(newWidth, newHeight);
		Gdx.graphics.requestRendering();
		return false;
	}
	
	
	@Override public boolean tap(float x, float y, int count, int button) {
	
		return false;
	}
	
	
	@Override public boolean longPress(float x, float y) {
	
		return false;
	}
	
	
	@Override public boolean fling(float velocityX, float velocityY, int button) {
	
		return false;
	}
	
	
	
	@Override public boolean panStop(float x, float y, int pointer, int button) {
	
		return false;
	}
	
	
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
	
		return false;
	}
}
