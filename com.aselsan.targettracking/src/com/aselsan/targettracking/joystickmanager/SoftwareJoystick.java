package com.aselsan.targettracking.joystickmanager;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Shell;

public class SoftwareJoystick extends Joystick {
	private Shell joystickView;
	private boolean mousePressed;
	public SoftwareJoystick(){
		super();	
		mousePressed = false;
		joystickView = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		joystickView.setSize(200, 200);		
		joystickView.addMouseListener(new MouseListener() {
				
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				joystickEventManager.buttonPressed();
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
		});
		
		joystickView.addMouseMoveListener(new MouseMoveListener(){

			@Override
			public void mouseMove(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mousePressed)
					joystickEventManager.move(1,1);
			}
			
		});
		joystickView.open();
		
	}
	
	
	

}
