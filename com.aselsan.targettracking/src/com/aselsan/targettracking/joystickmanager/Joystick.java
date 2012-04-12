package com.aselsan.targettracking.joystickmanager;

public class Joystick {
	protected JoystickEventManager joystickEventManager;
	public Joystick(){
		joystickEventManager = JoystickEventManager.getInstance();
	}
}
