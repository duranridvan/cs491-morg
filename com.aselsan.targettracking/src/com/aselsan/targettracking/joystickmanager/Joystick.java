package com.aselsan.targettracking.joystickmanager;

public class Joystick {
	protected static JoystickEventManager joystickEventManager;
	public Joystick(){
		joystickEventManager = JoystickEventManager.getInstance();
	}
}
