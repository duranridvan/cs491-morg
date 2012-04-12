package com.aselsan.targettracking.sensornetwork;

import java.util.ArrayList;
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
	private SensorManager(){
		sensorList = new HashMap<Integer,Sensor>();
		count=0;
	}
	
	public Sensor getSensor(int id){
		return sensorList.get(id);
	}
	
	public int addSensor(String mac,Point location){
		sensorList.put(count,new Sensor(count,mac,location));
		
		return count++;
	}
	
	public void removeSensor(int id){
		sensorList.remove(id);
	}
	
	public List<Sensor> getSensorList(){
		return new ArrayList<Sensor>(sensorList.values());
	}
	
	static public SensorManager getInstance(){
		if(instance == null)
			return instance = new SensorManager();
		return instance;
	}
}
