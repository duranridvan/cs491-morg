package com.aselsan.targettracking.gis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.Path;
import com.aselsan.targettracking.algorithm.AlgorithmEventListener;
import com.aselsan.targettracking.joystickmanager.JoystickEventListener;
import com.aselsan.targettracking.joystickmanager.JoystickEventManager;
import com.aselsan.targettracking.sensornetwork.Alarm;
import com.aselsan.targettracking.sensornetwork.SensorEventListener;
import com.aselsan.targettracking.sensornetwork.SensorEventManager;
import com.aselsan.targettracking.sensornetwork.SensorManager;
import com.aselsan.targettracking.sensornetwork.SimulationSensorNetwork;
import com.aselsan.targettracking.view.GISView;

public class GISController implements SensorManager.Listener, JoystickEventListener, SensorEventListener, AlgorithmEventListener {
	GISView view;
	private SensorManager sensorManager;
	public Path path;
	private boolean isButtonPressed = false;
	private Point currentPosition;
	private long ltime = 0;
	private GISEventManager em;
	private static GISController instance=null;

	
	
	public void clean(){
		path = new Path();
		currentPosition = new Point(0,0);
		view.update();
	}
	
	public static GISController getInstance(){
		if(instance == null){
			instance = new GISController();
		}
		return instance;
	}
	public GISController() {	
		this.view = null;
		sensorManager = SensorManager.getInstance();
		currentPosition = new Point(0, 0);
		em = GISEventManager.getInstance();
		path = new Path();
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
	@Override
	public void notifyPoint(Point p) {
		path.addPoint(p);
		if(view!=null){
			if(currentPosition.x !=0 || currentPosition.y!=0){
				view.drawLine(currentPosition, p);
			}
			view.updateCursorPosition(p);
			view.update();
			currentPosition = p;
		}
	}
	@Override
	public void notifyStart() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyFinish() {
		// TODO Auto-generated method stub
		
	}

	
}
