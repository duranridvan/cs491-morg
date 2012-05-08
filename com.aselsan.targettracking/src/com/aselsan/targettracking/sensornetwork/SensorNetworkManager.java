package com.aselsan.targettracking.sensornetwork;

import com.aselsan.targettracking.gis.GISController;
import com.aselsan.targettracking.gis.GISEventManager;
import com.aselsan.targettracking.joystickmanager.JoystickEventManager;

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
			sN.start();
			isRun = true;
			JoystickEventManager.getInstance().addListener(GISController.getInstance());
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
		else{
			isRun = false;
			JoystickEventManager.getInstance().removeListener(GISController.getInstance());
		}
	}
}
