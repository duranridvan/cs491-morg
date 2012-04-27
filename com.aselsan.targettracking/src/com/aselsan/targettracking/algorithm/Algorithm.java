package com.aselsan.targettracking.algorithm;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.sensornetwork.Alarm;
import com.aselsan.targettracking.sensornetwork.Sensor;
import com.aselsan.targettracking.sensornetwork.SensorEventListener;
import com.aselsan.targettracking.sensornetwork.SensorManager;

public class Algorithm implements SensorEventListener {
	ArrayList<Alarm> alarms;
	AlgorithmEventManager algorithmEventManager = AlgorithmEventManager.getInstance();
	@Override
	public void alarm(ArrayList<Alarm> alarms) {
		
		double x=0,y=0,sSum=0;
		Sensor s;
		Alarm a;
		
		for(int i=0;i<alarms.size();i++){
			a=alarms.get(i);
			s = SensorManager.getInstance().getSensor((a.sensorId));
			x+=Math.log10(a.strength)*s.getLocation().x;
			y+=Math.log10(a.strength)*s.getLocation().y;
			sSum+=Math.log10(a.strength);
					
			
		}
		x=x/sSum;
		y=y/sSum;
		algorithmEventManager.notifyPoint(new Point((int)x, (int)y));
		
	}
	
	public Algorithm(){
		alarms=new ArrayList<Alarm>();
	}
	
}
