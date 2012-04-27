package com.aselsan.targettracking.sensornetwork;

public class Alarm {
	public int sensorId;
	public double strength;
	public long timestamp;
	
	public Alarm(int sid, double s,long ts){
		sensorId=sid;
		strength=s;
		timestamp=ts;
	}

}
