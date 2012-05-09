package com.aselsan.targettracking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

public class Path {
	
	private ArrayList<Point> points;
	private String startTime;
	private String finishTime;
	
	public Path(){
		setPoints(new ArrayList<Point>());
		setStartTime(DateUtils.now());
	}
	
	public void addPoint(Point p){
		getPoints().add(p);
		setFinishTime(DateUtils.now());
	}
	public void finishPath(){
		
	}
	
	public void draw(GC gc){
		Point pre = null;
		for(Point p : getPoints()){
			if(pre!=null){
				gc.setLineWidth(4);
            	gc.drawLine(pre.x, pre.y, p.x, p.y);
            	gc.setLineWidth(1);
			}
			pre = p;
		}
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	public String toString(){
		return "Start = " + startTime + " Finish = " + finishTime;
	}

	public static class DateUtils {
		  public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

		  public static String now() {
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		    return sdf.format(cal.getTime());

		  }

		//  public static void  main(String arg[]) {
		  //  System.out.println("Now : " + DateUtils.now());
		  //}
		}

	

	
}



