package com.aselsan.targettracking.view;

import java.io.IOException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.sensornetwork.SensorManager;



public class GISView extends ViewPart{
	
	public static final String ID = "com.aselsan.targettracking.gisview";
	SensorManager sensors;
	
	public GISView() throws IOException {
		
	}


	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

}