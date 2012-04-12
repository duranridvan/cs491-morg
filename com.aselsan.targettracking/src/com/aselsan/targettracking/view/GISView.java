package com.aselsan.targettracking.view;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


import com.aselsan.targettracking.Activator;
import com.aselsan.targettracking.sensornetwork.SensorManager;
import com.aselsan.targettracking.sensornetwork.Sensor;



public class GISView extends ViewPart{
	
	public static final String ID = "com.aselsan.targettracking.gisview";
	private Image bgimage,sensorImage;
	private SensorManager sensorManager;
	public GISView() throws IOException {
		sensorImage = Activator.getImageDescriptor("images/sensor.gif").createImage();
		bgimage = Activator.getImageDescriptor("images/grass2.jpg").createImage();
	}


	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		parent.setBackgroundImage(bgimage);
		parent.setLayout(new FillLayout());
		//final Canvas canvas = new Canvas(parent, 0);
	    parent.addPaintListener(new PaintListener() {
	    	void placeSensor(GC gc, Sensor s){
	    		int destx = s.getLocation().x-15;
	    		int desty = s.getLocation().y-15;
	    		gc.drawImage(sensorImage, 0, 0, sensorImage.getBounds().width, sensorImage.getBounds().height,
	    				destx, desty, 30,30);
	    	}
			@Override
			public void paintControl(PaintEvent e) {

	            Rectangle clientArea = parent.getClientArea();
	            
	            //e.gc.drawImage(sensorImage, 10, 10, 10, 10);
	            //e.gc.drawLine(0,0,clientArea.width,clientArea.height); 
			}
		});
	    

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

}