package com.aselsan.targettracking.gis;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.graphics.Point;

public class GISEventManager {
	private Collection<GISEventListener> listeners;
	private static GISEventManager instance = null; 
	private GISEventManager(){
		listeners = new ArrayList<GISEventListener>();
	}
	
	public static GISEventManager getInstance(){
		if(instance==null)
			instance = new GISEventManager();
		return instance;
	}
	
	public void addListener(GISEventListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(GISEventListener listener){
		listeners.remove(listener);
	}
	
	public void positionChanged(Point p){
		System.out.println(p);
		for(GISEventListener l : listeners)
			l.positionChanged(p);
	}
}
