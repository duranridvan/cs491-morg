package com.aselsan.targettracking.sensornetwork;

import org.eclipse.swt.graphics.Point;

public class Sensor {
	private int id;
	private String mac;
	private Point location;
	public boolean isAlarm;
	public Sensor(int id,String mac, Point location){
		this.id = id;
		this.mac = mac;
		this.location=location;
		isAlarm = false;
	}
	
	public Sensor(int id,String mac,int x,int y){
		this(id,mac,new Point(x,y));
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getMac(){
		return this.mac;
	}
	
	public Point getLocation(){
		return this.location;
	}
	
	public void addAlarm(){
		isAlarm = true;
	}
	
	public String toString(){
		return id + "-) (" + location.x + "," +location.y + ")";
	}
}
