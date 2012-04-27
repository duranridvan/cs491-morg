package com.aselsan.targettracking.gis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.joystickmanager.JoystickEventListener;
import com.aselsan.targettracking.joystickmanager.JoystickEventManager;
import com.aselsan.targettracking.sensornetwork.Alarm;
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
	private static GISController instance=null;
	
	public static GISController getInstance(){
		if(instance == null){
			instance = new GISController();
		}
		return instance;
	}
	public GISController() {	
		this.view = null;
		sensorManager = SensorManager.getInstance();
		points = new ArrayList<Point>();
		currentPosition = new Point(0, 0);
		em = GISEventManager.getInstance();
	}
	
	public void setView(GISView view){
		this.view = view;
		view.updateCursorPosition(currentPosition);
	}
	@Override
	public void sensorManagerUpdate() {
		if(view!=null){
			view.updateSensorList(sensorManager.getSensorList());
			view.update();	
		}
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
			if(view!=null)
			view.drawLine(currentPosition,newPoint);
			if(ctime - ltime >= 500){
				em.positionChanged(newPoint);
				ltime = ctime;
			}
		}
		if(view!=null)
			view.updateCursorPosition(newPoint);
		currentPosition = newPoint;
		if(view!=null)
			view.update();
	}
	@Override
	public void alarm(ArrayList<Alarm> alarms) {
		// TODO Auto-generated method stub
		
	}

	
}
