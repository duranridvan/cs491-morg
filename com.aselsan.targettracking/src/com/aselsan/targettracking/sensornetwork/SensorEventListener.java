package com.aselsan.targettracking.sensornetwork;

public interface SensorEventListener {

	void alarm(int sensorId, double strength, long time);
}
