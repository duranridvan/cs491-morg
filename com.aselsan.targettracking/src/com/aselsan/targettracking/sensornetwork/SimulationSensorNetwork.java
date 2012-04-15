package com.aselsan.targettracking.sensornetwork;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.gis.GISEventListener;

public class SimulationSensorNetwork extends SensorNetwork implements
		GISEventListener {
	SensorManager sM = SensorManager.getInstance();
	public final double tresholdDistance = 50;
	public SimulationSensorNetwork(SensorEventManager eM) {
		super(eM);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void positionChanged(Point p) {
		// TODO Auto-generated method stub
		ArrayList<Sensor> list = (ArrayList<Sensor>) sM.getSensorList();
		double[] distancesToSensors = new double[list.size()];
		int pX = p.x;
		int pY = p.y;
		for(int i= 0; i<list.size(); i++){
			int sX = list.get(i).getLocation().x;
			int sY = list.get(i).getLocation().y;
			distancesToSensors[i] = Math.sqrt((sY-pY)*(sY-pY)-(sX-pX)*(sX-pX)); 
		}
		for(int i=0; i<distancesToSensors.length; i++){
			if(distancesToSensors[i] < tresholdDistance){
				eventManager.alarm(list.get(i).getId(), 1000/distancesToSensors[i]+1);
			}
		}
	}
	
	public void start(){
		//eventManager.addListener(..);
	}

}
