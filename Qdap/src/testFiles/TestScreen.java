package testFiles;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.utils.Array;


public class TestScreen implements Screen {
	
	SelectBox<String>	testBox;
	Selection<String>	select	= new Selection<String>();
	
	TextButton testbtn = new TextButton("test", Qdap.uiSkin);
	
	Label testlbl = new Label("Woooork", Qdap.uiSkin);
	List<String> list = new List<String>(Qdap.uiSkin);
	
	public TestScreen() {
	
		Array<String> stuff = new Array<String>();
		stuff.add("One");
		stuff.add("Two");
		stuff.add("Three");
		
		
//		select.setActor(list);
//		select.addAll(stuff);
//		select.setMultiple(true);
		list.setItems("Drywall", "Tub/Showerbase", "Towel Bar", "Glass Door", "Slider Door", "Pocket Door", "Shower Niche",
                "Bench", "Door", "Closet", "Exterior Cladding", "Canopy", "Railing", "Shear-Wall", "Electrical Panel",
                "Communication Box", "Vanity", "Cabinet", "Desk Space");
		
//		list.setItems(stuff);
//		list.setBounds(250, 250, 250, 250);
		list.getSelection().setMultiple(false);
		list.getSelection().setRequired(false);
		
		ScrollPane scroller = new ScrollPane(list, Qdap.uiSkin);
		scroller.setPosition(250, 250);
//		scroller.setCullingArea();
//		scroller.setFlickScroll(true);
//		scroller.
		
//		testBox = new SelectBox<String>(Qdap.uiSkin);
//		testBox.setItems(stuff);
//		testBox.setBounds(250, 250, 250, 250);
		
//		Qdap.stage.addActor(testBox);
//		Qdap.stage.addActor(testlbl);
//		Qdap.stage.addActor(testbtn);
		Qdap.stage.addActor(scroller);
		
	}
	
	@Override public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Qdap.stage.draw();
		Qdap.stage.act();
		//Qdap.hud.draw();
		
	}
	
	@Override public void resize(int width, int height) {
	
	}
	
	@Override public void show() {
	
	}
	
	@Override public void hide() {
	
	}
	
	@Override public void pause() {
	
	}
	
	@Override public void resume() {
	
	}
	
	@Override public void dispose() {
	
	}
	
}
