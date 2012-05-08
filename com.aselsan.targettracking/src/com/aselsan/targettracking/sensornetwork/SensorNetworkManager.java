package com.aselsan.targettracking.sensornetwork;

public class SensorNetworkManager {
	private static SensorNetworkManager sNetworkManager = null;
	SensorNetwork sN;
	String com;
	boolean isRun;
	int option;
	public SensorNetworkManager(){
		isRun = false;
		option = 0;
		
	}
	public static SensorNetworkManager getInstance(){
		if(sNetworkManager == null)
			sNetworkManager = new SensorNetworkManager();
		return sNetworkManager;
	}
	
	public void setOption(int option){
		this.option = option;
	}
	
	public void setCom(String com){
		this.com = com;
	}
	public void start(){
		if(option==0){
			sN = new SimulationSensorNetwork();
			isRun = true;
		}else{
			if(com != null){
				sN = new RealSensorNetwork(com);
				sN.start();
				isRun = true;
			}
		}
	}
	public boolean isRunning(){
		return isRun; 
	}
	public void stop(){
		if(option == 1){
			((RealSensorNetwork)sN).stop();
		}
		isRun = false;
	}
}
