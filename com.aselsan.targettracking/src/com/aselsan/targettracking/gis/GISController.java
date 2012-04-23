package com.aselsan.targettracking.gis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;

import com.aselsan.targettracking.joystickmanager.JoystickEventListener;
import com.aselsan.targettracking.joystickmanager.JoystickEventManager;
import com.aselsan.targettracking.sensornetwork.SensorEventListener;
import com.aselsan.targettracking.sensornetwork.SensorEventManager;
import com.aselsan.targettracking.sensornetwork.SensorManager;
import com.aselsan.targettracking.sensornetwork.SimulationSensorNetwork;
import com.aselsan.targettracking.view.GISView;

public class GISController implements SensorManager.Listener, JoystickEventListener, SensorEventListener {
	GISView view;
	private SensorManager sensorManager;
	private List<Point> points;
	private boolean isButtonPressed = false;
	private Point currentPosition;
	private long ltime = 0;
	private GISEventManager em;
	public GISController(GISView view) throws SQLException {	
		this.view = view;
		sensorManager = SensorManager.getInstance();
		sensorManager.addListener(this);
		points = new ArrayList<Point>();
		currentPosition = new Point(0, 0);
		view.updateCursorPosition(currentPosition);
		JoystickEventManager.getInstance().addListener(this);
		new SimulationSensorNetwork();
		SensorEventManager.getInstance().addListener(this);
		em = GISEventManager.getInstance();
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
			if(ctime - ltime >= 500){
				em.positionChanged(newPoint);
				ltime = ctime;
			}
		}
		view.updateCursorPosition(newPoint);
		currentPosition = newPoint;
		view.update();
	}
	@Override
	public void alarm(int sensorId, double strength, long time) {
		// TODO Auto-generated method stub
		
	}

	
}
