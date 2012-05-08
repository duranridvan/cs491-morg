package com.aselsan.targettracking;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import com.aselsan.targettracking.sensornetwork.SensorNetworkManager;
import com.aselsan.targettracking.sensornetwork.SimulationSensorNetwork;

public class CommandStop extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(!SensorNetworkManager.getInstance().isRunning()) return null;
		SensorNetworkManager.getInstance().stop();
		return null;
	}

	public boolean isEnabled() {
		return true;
		//return SensorNetworkManager.getInstance().isRunning();
	}


}
