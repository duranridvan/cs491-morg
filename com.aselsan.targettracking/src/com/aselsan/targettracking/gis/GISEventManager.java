package com.aselsan.targettracking.gis;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.graphics.Point;

public class GISEventManager {
	private Collection<GISEventListener> listeners;
	
	private GISEventManager(){
		listeners = new ArrayList<GISEventListener>();
	}
	
	public void addListener(GISEventListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(GISEventListener listener){
		listeners.remove(listener);
	}
	
	public void positionChanged(Point p){
		for(GISEventListener l : listeners)
			l.positionChanged(p);
	}
}
