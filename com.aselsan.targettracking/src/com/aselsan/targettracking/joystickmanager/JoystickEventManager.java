package com.aselsan.targettracking.joystickmanager;

import java.util.ArrayList;
import java.util.Collection;

public class JoystickEventManager {
	private Collection<JoystickEventListener> listeners;
	private static JoystickEventManager instance = null;
	private JoystickEventManager(){
		listeners = new ArrayList<JoystickEventListener>();
	}
	public static JoystickEventManager getInstance(){
		if(instance == null)
			return instance = new JoystickEventManager();
		return instance;
	}
	
	public void addListener(JoystickEventListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(JoystickEventListener listener){
		listeners.remove(listener);
	}
	
	public void buttonPressed(){
		for(JoystickEventListener l : listeners)
			l.buttonPressed();
	}
	public void buttonReleased(){
		for(JoystickEventListener l : listeners)
			l.buttonReleased();
	}
	public void move(int x,int y){
		for(JoystickEventListener l : listeners)
			l.move(x,y);
	}
}
