package com.aselsan.targettracking.gis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.joystickmanager.JoystickEventListener;
import com.aselsan.targettracking.joystickmanager.JoystickEventManager;
import com.aselsan.targettracking.sensornetwork.SensorEventListener;
import com.aselsan.targettracking.sensornetwork.SensorManager;
import com.aselsan.targettracking.view.GISView;

public class GISController implements SensorManager.Listener, JoystickEventListener {
	GISView view;
	private SensorManager sensorManager;
	private List<Point> points;
	private boolean isButtonPressed = false;
	private Point currentPosition;
	private long lastTime=0;
	public GISController(GISView view) {	
		this.view = view;
		sensorManager = SensorManager.getInstance();
		sensorManager.addListener(this);
		points = new ArrayList<Point>();
		currentPosition = new Point(0, 0);
		view.updateCursorPosition(currentPosition);
		JoystickEventManager.getInstance().addListener(this);
	}
	@Override
	public void sensorManagerUpdate() {
		view.updateSensorList(sensorManager.getSensorList());
		view.update();	
	}
	@Override
	public void buttonPressed() {
		isButtonPressed = true;
	}
	@Override
	public void buttonReleased() {
		isButtonPressed = false;
	}
	@Override
	public void move(int x, int y) {
		long ctime = System.currentTimeMillis();
		
		Point newPoint = new Point(currentPosition.x+x,currentPosition.y+y);
		if(isButtonPressed){
			view.drawLine(currentPosition,newPoint);
			if(ctime - lastTime > 500){
				GISEventManager.getInstance().positionChanged(newPoint);
				lastTime = ctime;
			}
		}
		view.updateCursorPosition(newPoint);
		currentPosition = newPoint;
		view.update();
	}

	
}
