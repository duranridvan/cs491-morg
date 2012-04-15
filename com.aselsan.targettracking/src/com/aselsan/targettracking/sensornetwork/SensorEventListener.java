package com.aselsan.targettracking.sensornetwork;

public interface SensorEventListener {
	public void alarm(int sensorId,double strength);
}
