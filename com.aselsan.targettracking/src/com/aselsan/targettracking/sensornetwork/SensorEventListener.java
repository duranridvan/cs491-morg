package com.aselsan.targettracking.sensornetwork;

import java.util.ArrayList;

public interface SensorEventListener {

	void alarm(ArrayList<Alarm> alarms);
}
