package com.aselsan.targettracking.sensornetwork;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.gis.GISEventListener;
import com.aselsan.targettracking.gis.GISEventManager;

public class SimulationSensorNetwork extends SensorNetwork implements
		GISEventListener {
	SensorManager sM = SensorManager.getInstance();
	public final double tresholdDistance = 200;
	private static SimulationSensorNetwork instance = null;
	public SimulationSensorNetwork() {
		
	}
	
	public static SimulationSensorNetwork getInstance(){
		if(instance == null)
			instance = new SimulationSensorNetwork();
		return instance;
	}

	@Override
	public void positionChanged(Point p) {
//		System.out.println("psiton");
		ArrayList<Sensor> list = (ArrayList<Sensor>) sM.getSensorList();
		double[] distancesToSensors = new double[list.size()];
		int pX = p.x;
		int pY = p.y;
		for(int i= 0; i<list.size(); i++){
			int sX = list.get(i).getLocation().x;
			int sY = list.get(i).getLocation().y;
			distancesToSensors[i] = Math.sqrt((sY-pY)*(sY-pY)+(sX-pX)*(sX-pX)); 
		}
		ArrayList<Alarm> alarms = new ArrayList<Alarm>();
		for(int i=0; i<distancesToSensors.length; i++){
			if(distancesToSensors[i] < tresholdDistance){
				System.out.println(p + " " + distancesToSensors[i]);
				list.get(i).isAlarm = true;
				Alarm a = new Alarm(list.get(i).getId(), (int)(1000/distancesToSensors[i]+1), System.currentTimeMillis());
				alarms.add(a);
			}else
				list.get(i).isAlarm = false;
		}
		eventManager.alarm(alarms);
	}
	
	public void start(){
		GISEventManager.getInstance().addListener(this);
	}

}
