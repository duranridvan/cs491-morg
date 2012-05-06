package com.aselsan.targettracking.sensornetwork;

public class SensorNetworkManager {
	private static SensorNetworkManager sNetworkManager = null;
	SensorNetwork sN;
	String com;
	boolean isRun;
	int o;
	public SensorNetworkManager(){
		isRun = false;
		
	}
	public static SensorNetworkManager getInstance(){
		if(sNetworkManager == null)
			sNetworkManager = new SensorNetworkManager();
		return sNetworkManager;
	}
	
	public void setCom(String com){
		this.com = com;
	}
	public void start(int option){
		if(option==0){
			sN = new SimulationSensorNetwork();
			isRun = true;
			o = option;
		}else{
			if(com != null){
				sN = new RealSensorNetwork(com);
				isRun = true;
				o = option;
			}
		}
	}
	public boolean isRunning(){
		return isRun; 
	}
	public void stop(){
		if(o == 1)
			((RealSensorNetwork)sN).stop();
	}
}
