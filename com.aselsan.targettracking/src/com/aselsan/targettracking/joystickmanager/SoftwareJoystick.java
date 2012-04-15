package com.aselsan.targettracking.joystickmanager;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.view.SoftwareJoystickView;

public class SoftwareJoystick extends Joystick implements MouseListener, MouseMoveListener{
	private boolean mousePressed;
	private boolean buttonPressed;
	private SoftwareJoystickView view;
	private int width,height;
	public SoftwareJoystick(SoftwareJoystickView view){
		this.view = view;
		width = view.x;
		height = view.y;
		mousePressed = false;
		buttonPressed = true;
		//joystickView = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		//joystickView.setSize(207, 229);	
		//joystickView.open();
		
	}
	
	@Override
	public void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub
		if(mousePressed){
			int x = (e.x - width/2) / (width/10);
			int y = (e.y - height/2) / (height/10);
			joystickEventManager.move(x,y);
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
