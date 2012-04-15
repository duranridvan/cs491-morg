package com.aselsan.targettracking.gis;

import java.util.ArrayList;
import java.util.Collection;

public class GISEventManager {
	private Collection<GISEventListener> listeners;
	
	private GISEventManager(){
		listeners = new ArrayList<GISEventListener>();
	}
}
