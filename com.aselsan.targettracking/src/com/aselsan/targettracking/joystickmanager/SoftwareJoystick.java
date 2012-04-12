package com.aselsan.targettracking.joystickmanager;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Shell;

public class SoftwareJoystick extends Joystick {
	private Shell joystickView;
	private boolean mousePressed;
	private boolean buttonPressed;
	public SoftwareJoystick(){
		super();	
		mousePressed = false;
		buttonPressed = true;
		joystickView = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		joystickView.setSize(207, 229);	
		joystickView.addPaintListener(new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				
	            e.gc.drawLine(0, 100, 200, 100);
	            e.gc.drawLine(100, 0, 100, 200);
			}
			
		});
		joystickView.addMouseListener(new MouseListener() {
				
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
		});
		
		joystickView.addMouseMoveListener(new MouseMoveListener(){

			@Override
			public void mouseMove(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mousePressed){
					int x = (e.x - 100) / 10;
					int y = (e.y - 100) / 10;
					joystickEventManager.move(x,y);
				}
			}
			
		});
		joystickView.open();
		
	}
	
	
	

}
