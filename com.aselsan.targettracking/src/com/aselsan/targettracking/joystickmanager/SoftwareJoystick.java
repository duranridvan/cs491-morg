package com.aselsan.targettracking.joystickmanager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.aselsan.targettracking.View;

public class SoftwareJoystick extends Joystick {
	private JoystickEventManager joystickEventManager;
	private Shell joystickView;
	
	public SoftwareJoystick(){
		super(joystickEventManager);	
		joystickView = new Shell();
		
		joystickView.setVisible(false);
		joystickView.setLayout(new GridLayout(2, false));
	
		joystickView.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				joystickEventManager.button
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	

}
