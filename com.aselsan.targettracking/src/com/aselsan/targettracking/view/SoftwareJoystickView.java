package com.aselsan.targettracking.view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.joystickmanager.SoftwareJoystick;

public class SoftwareJoystickView extends ViewPart{

	public static final String ID = "com.aselsan.targettracking.softwarejoystickview";
	SoftwareJoystick joystick;
	public int x,y;
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
		System.out.println(parent.getSize());
		x = 200;
		y = 200;
		joystick = SoftwareJoystick.getInstance();
		joystick.setview(this);
		parent.addPaintListener(new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				
	            e.gc.drawLine(0, x/2, x, x/2);
	            e.gc.drawLine(x/2, 0, x/2, x);
			}
			
		});
		parent.addMouseListener(joystick);
		parent.addMouseMoveListener(joystick);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
