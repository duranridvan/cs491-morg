package com.aselsan.targettracking;

public class Alarm {
	private int eventid;
	private int sensorid;
	private int strength;
	
	public Alarm(int eventid,int sensorid,int strength){
		this.eventid = eventid;
		this.sensorid = sensorid;
		this.strength = strength;
	}
	
	public String toString(){
		return "A alarm with eventid = " + eventid + " sensor id = " + sensorid + " strength = " + strength;
	}

}
