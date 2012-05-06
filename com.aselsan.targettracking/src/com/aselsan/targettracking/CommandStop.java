package com.aselsan.targettracking;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import com.aselsan.targettracking.sensornetwork.SensorNetworkManager;
import com.aselsan.targettracking.sensornetwork.SimulationSensorNetwork;

public class CommandStop implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SensorNetworkManager.getInstance().stop();
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
		//return SensorNetworkManager.getInstance().isRunning();
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
