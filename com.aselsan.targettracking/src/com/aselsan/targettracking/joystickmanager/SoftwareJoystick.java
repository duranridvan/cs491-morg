package com.aselsan.targettracking.joystickmanager;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.sensornetwork.Alarm;
import com.aselsan.targettracking.view.SoftwareJoystickView;

public class SoftwareJoystick extends Joystick implements MouseListener, MouseMoveListener{
	private boolean mousePressed;
	private boolean buttonPressed;
	private SoftwareJoystickView view;
	private int width,height;
	private static SoftwareJoystick instance = null;
	public static int curX, curY;
	public static SoftwareJoystick getInstance(){
		if(instance == null){
			instance = new SoftwareJoystick();
			curX = 0;
			curY = 0;
			new Timer().scheduleAtFixedRate(new TimerTask() {				
				@Override
				public void run() {
					joystickEventManager.move(curX,curY);
				}
			}, 0,500);
		}
		return instance;
	}
	public void setview(SoftwareJoystickView view){
		this.view = view;
		width = view.x;
		height = view.y;
		mousePressed = false;
		buttonPressed = true;

	}
	public SoftwareJoystick(){
		//joystickView = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		//joystickView.setSize(207, 229);	
		//joystickView.open();
		
	}
	
	@Override
	public void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub
		if(mousePressed){
			curX = (e.x - width/2) / (width/10);
			curY = (e.y - height/2) / (height/10);
		}
	}

	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		if(buttonPressed)
		{	
			joystickEventManager.buttonPressed();
			buttonPressed = false;
		}
		else
		{	
			joystickEventManager.buttonReleased();
			buttonPressed = true;
		}
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = true;
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = false;
	}
	

}
