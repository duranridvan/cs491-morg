package com.aselsan.targettracking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.graphics.Point;

public class Path {
	
	ArrayList<Point> points;
	String startTime, finishTime;
	
	public Path(){
		points = new ArrayList<Point>();
		startTime = DateUtils.now();
	}
	
	public void addPoint(Point p){
		points.add(p);
	}
	public void finishPath(){
		finishTime = DateUtils.now();
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



