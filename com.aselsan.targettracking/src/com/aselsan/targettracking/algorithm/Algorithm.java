package com.aselsan.targettracking.algorithm;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.sensornetwork.Alarm;
import com.aselsan.targettracking.sensornetwork.Sensor;
import com.aselsan.targettracking.sensornetwork.SensorEventListener;
import com.aselsan.targettracking.sensornetwork.SensorManager;

public class Algorithm implements SensorEventListener {
	AlgorithmEventManager algorithmEventManager = AlgorithmEventManager.getInstance();
	@Override
	public void alarm(ArrayList<Alarm> alarms) {
		
		double x=0,y=0,sSum=0;
		Sensor s;
		
		
		for(Alarm a:alarms){
			s = SensorManager.getInstance().getSensor((a.sensorId));
			if(s!=null){
				/*x+=( a.strength)*s.getLocation().x;
				y+=(a.strength)*s.getLocation().y;
				sSum+=(a.strength);*/
				
				x+=Math.log( a.strength)*s.getLocation().x;
				y+=Math.log(a.strength)*s.getLocation().y;
				sSum+=Math.log(a.strength);
			}
					
			
		}
		if(sSum != 0){
			x=x/sSum;
			y=y/sSum;
			System.out.println(new Point((int)x, (int)y));
			algorithmEventManager.notifyPoint(new Point((int)x, (int)y));
		}
		
	}
	
	public Algorithm(){
	}
	
}
