package com.aselsan.targettracking;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.aselsan.targettracking.gis.GISController;
import com.aselsan.targettracking.gis.GISEventManager;
import com.aselsan.targettracking.joystickmanager.JoystickEventManager;
import com.aselsan.targettracking.joystickmanager.SoftwareJoystick;
import com.aselsan.targettracking.sensornetwork.SensorEventManager;
import com.aselsan.targettracking.sensornetwork.SensorManager;
import com.aselsan.targettracking.sensornetwork.SimulationSensorNetwork;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.aselsan.targettracking"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		GISController gis = GISController.getInstance();
		GISEventManager gem = GISEventManager.getInstance();
		SensorManager sm = SensorManager.getInstance();
		JoystickEventManager jem = JoystickEventManager.getInstance();
		SensorEventManager sem = SensorEventManager.getInstance();
		
		sm.addListener(gis);
		jem.addListener(gis);
		sem.addListener(gis);
		
		//gem.addListener(new SimulationSensorNetwork());
		
		
		
		//new SoftwareJoystick();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
