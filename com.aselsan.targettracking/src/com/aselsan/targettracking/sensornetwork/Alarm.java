package com.aselsan.targettracking.sensornetwork;

public class Alarm {
	public int sensorId;
	public int strength;
	public long timestamp;
	
	public Alarm(int sid, int d,long ts){
		sensorId=sid;
		strength=d;
		timestamp=ts;
	}
	

}
