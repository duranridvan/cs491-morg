package com.aselsan.targettracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import com.aselsan.targettracking.algorithm.AlgorithmEventManager;
import com.aselsan.targettracking.sensornetwork.SensorNetworkManager;

public class CommandStart extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		if(SensorNetworkManager.getInstance().isRunning()) return null;
		SensorNetworkManager.getInstance().start();
		System.out.println("asdfafdada");
		return null;
		
		/*
		try {
			Scanner scan = new Scanner(new File("C:\\Users\\ridvan\\workspace\\com.aselsan.targettracking\\gupguzel.txt"));
			while(scan.hasNext()){
				int x = scan.nextInt();
				int y = scan.nextInt();
				AlgorithmEventManager.getInstance().notifyPoint(new Point(x, y));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
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
