package com.aselsan.targettracking.gis;

import com.aselsan.targettracking.sensornetwork.SensorManager;
import com.aselsan.targettracking.view.GISView;

public class GISController implements SensorManager.Listener {
	GISView view;
	private SensorManager sensorManager;
	public GISController(GISView view){
		this.view = view;
		sensorManager = SensorManager.getInstance();
		sensorManager.addListener(this);
	}
	@Override
	public void sensorManagerUpdate() {
		view.updateSensorList(sensorManager.getSensorList());
		view.update();
		
	}
}
