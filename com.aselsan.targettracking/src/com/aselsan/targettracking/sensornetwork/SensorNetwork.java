package com.aselsan.targettracking.sensornetwork;

public abstract class SensorNetwork  {
	SensorEventManager eventManager;
	public SensorNetwork(){
		eventManager = SensorEventManager.getInstance();
	}
	public abstract void start();
}
