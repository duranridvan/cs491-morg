package com.aselsan.targettracking.sensornetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Point;

public class SensorManager {
	private Map<Integer,Sensor> sensorList;
	//TODO: database integration
	//private DatabaseManager db;
	int count;
	private static SensorManager instance = null;
	private Collection<Listener> listeners;
	private SensorManager(){
		sensorList = new HashMap<Integer,Sensor>();
		count=0;
		listeners = new ArrayList<SensorManager.Listener>();
	}
	
	public void addListener(Listener l){
		listeners.add(l);
	}
	
	private void notifyListeners(){
		for(Listener l:listeners)
			l.sensorManagerUpdate();
	}
	
	public Sensor getSensor(int id){
		return sensorList.get(id);
	}
	
	public synchronized int addSensor(String mac,Point location){
		sensorList.put(count,new Sensor(count,mac,location));
		int ret = count++;
		notifyListeners();
		return ret;
	}
	
	public synchronized void removeSensor(int id){
		sensorList.remove(id);
		notifyListeners();
	}
	
	public List<Sensor> getSensorList(){
		return new ArrayList<Sensor>(sensorList.values());
	}
	
	
	static public SensorManager getInstance(){
		if(instance == null)
			return instance = new SensorManager();
		return instance;
	}
	
	
	public interface Listener{
		public void sensorManagerUpdate();
	}
}
