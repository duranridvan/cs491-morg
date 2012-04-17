package com.aselsan.targettracking.sensornetwork;

public class Alarm {
	int sensorId;
	double strength;
	long timestamp;
	
	public Alarm(int sid, double s,long ts){
		sensorId=sid;
		strength=s;
		timestamp=ts;
	}

}
