package com.aselsan.targettracking.joystickmanager;

public interface JoystickEventListener {
	public void buttonPressed();
	public void buttonReleased();
	public void move(int x,int y);
}
