package com.aselsan.targettracking.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


import com.aselsan.targettracking.Activator;
import com.aselsan.targettracking.gis.GISController;
import com.aselsan.targettracking.sensornetwork.Sensor;



public class GISView extends ViewPart{
	
	public static final String ID = "com.aselsan.targettracking.gisview";
	private Image bgimage,sensorImage,cursorImage;
	private Canvas canvas;
	private Collection<Sensor> sensors = null;
	private List<Point[]> lines;
	private Point cursorPosition;
	public GISView() throws IOException {
		sensorImage = Activator.getImageDescriptor("images/sensor.gif").createImage();
		bgimage = Activator.getImageDescriptor("images/grass2.jpg").createImage();
		cursorImage = Activator.getImageDescriptor("images/drawcursor.gif").createImage();
		sensors = new ArrayList<Sensor>();
		new GISController(this);
		lines = new ArrayList<Point[]>();
	}


	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new FillLayout());
		canvas = new Canvas(parent, 0);
		canvas.setBackgroundImage(bgimage);
	    canvas.addPaintListener(new PaintListener() {
	    	void placeSensor(GC gc, Sensor s){
	    		int destx = s.getLocation().x-15;
	    		int desty = s.getLocation().y-15;
	    		gc.drawImage(sensorImage, 0, 0, sensorImage.getBounds().width, sensorImage.getBounds().height,
	    				destx, desty, 30,30);
	    	}
			@Override
			public void paintControl(PaintEvent e) {

	            //Rectangle clientArea = parent.getClientArea();
	            for(Sensor s : sensors){
	            	placeSensor(e.gc, s);
	            }
	            for(Point[] line : lines){
	            	e.gc.setLineWidth(4);
	            	e.gc.drawLine(line[0].x, line[0].y, line[1].x, line[1].y);
	            	e.gc.setLineWidth(1);
	            }
	            int cx = cursorPosition.x - cursorImage.getBounds().width/2;
	            int cy = cursorPosition.y - cursorImage.getBounds().height/2;
	            e.gc.drawImage(cursorImage,cx,cy);
	            
	            
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

	public void drawLine(Point p1,Point p2){
		Point[] line = new Point[2];
		line[0]=p1; line[1]=p2;
		lines.add(line);
	}
	
	public void updateCursorPosition(Point p){
		cursorPosition = p;
	}
	public void updateSensorList(Collection<Sensor> sensorlist){
		sensors = sensorlist;	
	}
	public void update() {
		canvas.redraw();
		canvas.update();
		
	}

}