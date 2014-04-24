package com.VDIndustries.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class LoadingScreen implements Screen {
	
	
	private final Texture		emptyT;
	private final Texture		fullT;
	private final NinePatch		emptybar;
	private final NinePatch		fullbar;
	private final BitmapFont	font;
	
	private final Qdap			qdap;
	
	
	public LoadingScreen(Qdap q) {
	
		qdap = q;
		
		font = new BitmapFont(true);
		
		emptyT = new Texture(Gdx.files.internal("gui/loading/empty.png"));
		
		emptybar = new NinePatch(new TextureRegion(emptyT, 24, 24), 8, 8, 8, 8);
		
		fullT = new Texture(Gdx.files.internal("gui/loading/full.png"));
		fullbar = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		
	}
	
	
	@Override public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Qdap.batch.setProjectionMatrix(Qdap.camera.combined);
		
		Qdap.batch.begin();
		{
			emptybar.draw(Qdap.batch, Gdx.graphics.getWidth() / 8,
					Gdx.graphics.getHeight() / 2,
					Gdx.graphics.getWidth() - Gdx.graphics.getWidth() /4, 45);
			
			
			fullbar.draw(Qdap.batch, Gdx.graphics.getWidth() / 8,
					Gdx.graphics.getHeight() / 2,
					Qdap.am.getProgress() * (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4), 45);
			
			
			font.drawMultiLine(Qdap.batch,
					(int) (Qdap.am.getProgress() * 100) + "% loaded", 400,
					Gdx.graphics.getHeight() / 2 + emptyT.getHeight() / 3, 0,
					BitmapFont.HAlignment.CENTER);
		}
		Qdap.batch.end();
		
		Gdx.graphics.requestRendering();
		
		if (Qdap.am.update()) {
			qdap.setScreen(Qdap.mainScreen);
			Qdap.mainScreen.projectWindow();
			Qdap.mainScreen.projectInitialize();
		}
		
	}
	
	@Override public void resize(int width, int height) {
	
	}
	
	@Override public void show() {
	
	}
	
	@Override public void hide() {
	
		dispose();
	}
	
	@Override public void pause() {
	
	}
	
	@Override public void resume() {
	
	}
	
	@Override public void dispose() {
	
		emptyT.dispose();
		fullT.dispose();
	}
	
}
