package com.aselsan.targettracking.algorithm;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.sensornetwork.Alarm;
import com.aselsan.targettracking.sensornetwork.SensorEventListener;

public class Algorithm implements SensorEventListener {
	ArrayList<Alarm> alarms;
	AlgorithmEventManager algorithmEventManager = AlgorithmEventManager.getInstance();
	@Override
	public void alarm(int sensorId, double strength, long time) {
		alarms.add(new Alarm(sensorId, strength, time));
		
	}
	
	public Algorithm(){
		alarms=new ArrayList<Alarm>();
	}
	
	public void alarmFinished(){
		Point p=new Point(5 ,10);
		algorithmEventManager.notifyPoint(p);
		alarms.clear();
	}

}
