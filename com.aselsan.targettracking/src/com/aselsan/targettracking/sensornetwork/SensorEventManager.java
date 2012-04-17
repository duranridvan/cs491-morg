package com.aselsan.targettracking.sensornetwork;

import java.util.ArrayList;
import java.util.Collection;

public class SensorEventManager {
	private Collection<SensorEventListener> listeners;
	private static SensorEventManager instance = null;
	private SensorEventManager(){
		listeners = new ArrayList<SensorEventListener>(); 
	}
	
	public static SensorEventManager getInstance(){
		if(instance==null)
			instance = new SensorEventManager();
		return instance;
	}
	
	public void addListener(SensorEventListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(SensorEventListener listener){
		listeners.remove(listener);
	}
	
	public void alarm(int sensorId,double strength, long time){
		for(SensorEventListener listener: listeners)
			listener.alarm(sensorId, strength, System.currentTimeMillis());
	}
	
}
