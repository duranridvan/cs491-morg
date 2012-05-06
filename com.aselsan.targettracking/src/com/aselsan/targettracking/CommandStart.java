package com.aselsan.targettracking;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import com.aselsan.targettracking.sensornetwork.SensorNetworkManager;

public class CommandStart extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SensorNetworkManager.getInstance().start(1);
		System.out.println("asdfafdada");
		return null;
	}

	@Override
	public boolean isEnabled() {
	//	System.out.println(!SensorNetworkManager.getInstance().isRunning());
		return true;
	//	return !SensorNetworkManager.getInstance().isRunning();
	}
	
	public boolean isHandled(){
		return true;
		
	}

}
