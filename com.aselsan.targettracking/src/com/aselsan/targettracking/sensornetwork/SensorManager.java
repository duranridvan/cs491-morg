package com.aselsan.targettracking.sensornetwork;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.databasemanager.DatabaseManager;

public class SensorManager {
	private Map<Integer,Sensor> sensorList;
	//TODO: database integration
	private DatabaseManager db;
	private static SensorManager instance = null;
	private Collection<Listener> listeners;
	private SensorManager(){
		db = DatabaseManager.getInstance();		
		sensorList = new HashMap<Integer,Sensor>();
		int size = db.getSensors().size();
		List<Sensor> list = db.getSensors(); 
		for(int i = 0 ; i < size ; i++)
			sensorList.put(list.get(i).getId(),list.get(i));
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
	
	public synchronized int addSensor(String mac,Point location) throws SQLException{
		
		int id = db.addSensor(mac,location);
		Sensor s = new Sensor(id,mac,location);
		sensorList.put(id,s);
		notifyListeners();
		return id;
	}
	
	public synchronized int deleteSensor(int sensorId) throws SQLException{
		
		int id = db.deleteSensor(sensorId);
		sensorList.remove(id);
		notifyListeners();
		return id;
	}
	
	public synchronized void removeSensor(int id){
		sensorList.remove(id);
		notifyListeners();
	}
	
	public List<Sensor> getSensorList(){
		return new ArrayList<Sensor>(sensorList.values());
	}
	
	
	static public SensorManager getInstance() {
		if(instance == null)
			return instance = new SensorManager();
		return instance;
	}
	
	
	public interface Listener{
		public void sensorManagerUpdate();
	}
}
