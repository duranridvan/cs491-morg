package com.aselsan.targettracking.algorithm;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.graphics.Point;


public class AlgorithmEventManager {
	private Collection<AlgorithmEventListener> listeners;
	private static AlgorithmEventManager instance;
	private AlgorithmEventManager(){
		listeners=new ArrayList<AlgorithmEventListener>();

	}
	public static AlgorithmEventManager getInstance(){
		if(instance==null)
			instance = new AlgorithmEventManager();
		return instance;
	}

	public void addListener(AlgorithmEventListener a){
		listeners.add(a);
	}

	public void notifyPoint(Point p){
		for(AlgorithmEventListener l:listeners){
			l.notifyPoint(p);
		}
	}

}

