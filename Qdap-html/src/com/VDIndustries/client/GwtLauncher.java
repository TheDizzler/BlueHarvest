package com.VDIndustries.client;

import com.VDIndustries.app.Qdap;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1280 / 4 * 3, 800 / 4 * 3);
		
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new Qdap();
	}
}