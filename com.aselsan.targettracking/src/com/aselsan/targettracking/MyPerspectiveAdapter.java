package com.aselsan.targettracking;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PerspectiveAdapter;

import com.aselsan.targettracking.sensornetwork.SensorNetworkManager;

public class MyPerspectiveAdapter extends PerspectiveAdapter {
	public void perspectiveActivated(IWorkbenchPage page,
			IPerspectiveDescriptor perspectiveDescriptor){
		super.perspectiveActivated(page, perspectiveDescriptor);
		if(perspectiveDescriptor.getId().equals("com.aselsan.targettracking.realperspective"))
			SensorNetworkManager.getInstance().setOption(1);
		else
			SensorNetworkManager.getInstance().setOption(0);
		System.out.println(perspectiveDescriptor.getId());
	}
}
