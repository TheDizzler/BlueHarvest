package com.VDIndustries.app;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Listener for report flags.
 * @author T-Dawg
 */
public class EditReportListener extends ClickListener {
	
	public Element	report;
	
	@Override public void clicked(InputEvent event, float x, float y) {
	
		Qdap.roomScreen.makeReportWindow(x, y, report);

	}
}
