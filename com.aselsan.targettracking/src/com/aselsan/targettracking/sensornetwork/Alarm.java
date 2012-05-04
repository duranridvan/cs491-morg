package com.aselsan.targettracking.sensornetwork;

public class Alarm {
	public int sensorId;
	public int strength;
	public long timestamp;
	
	public Alarm(int sid, int s,long ts){
		sensorId=sid;
		strength=s;
		timestamp=ts;
	}

}
