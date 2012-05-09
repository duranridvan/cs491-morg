package com.aselsan.targettracking.databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.swt.graphics.Point;
import org.postgresql.geometric.PGpath;
import org.postgresql.geometric.PGpoint;

import com.aselsan.targettracking.Path;
import com.aselsan.targettracking.sensornetwork.Alarm;
import com.aselsan.targettracking.sensornetwork.Sensor;

public class DatabaseManager {
	
	private Connection connection;
	private static DatabaseManager instance = null;
	private PreparedStatement st;
	private Statement stmt = null;
	
	private DatabaseManager() {
		connection = null;
		st = null;
		connectDatabase("jdbc:postgresql://127.0.0.1:5432/", "postgres", "bric26", "postgres");
	}
	
	public void connectDatabase(String host,String username,String password,String dbname) 
	{
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not registered!!!");
			e.printStackTrace();
			return;
		}
		try {
			connection = DriverManager.getConnection(host+""+dbname,username,password);
		} 
		catch (SQLException e) {
			System.out.println(" Database Connection Failed! ");
			e.printStackTrace();
			return;
		}	
	}
	
	public void disconnect(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(" Database Disconnection Failed! ");
			e.printStackTrace();
		}
	}
	
	private int idcnt = 0;
	public int addSensor(String mac, Point location){	
		
		int id = idcnt++;
		try{
			st = connection.prepareStatement("INSERT INTO sensor(mac,location) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
			st.setObject(1, mac);
			int x = location.x;
			int y = location.y;
			st.setObject(2, new PGpoint(x,y));
			st.executeUpdate();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			id = res.getInt(1);
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the sensor ");
			e.printStackTrace();
			return 0;
		}
		return id;               
    }
	
	
	public int deleteSensor(int sensorId){	
		try{
			String sql = "DELETE FROM sensor WHERE sensorid =" + sensorId;
			st = connection.prepareStatement(sql);
			st.executeUpdate();
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not delete the sensor ");
			e.printStackTrace();
			return 0;
		}
		return sensorId;               
    }
	
	
	public int addAlarm(long time,int sensorId,int strength){
		int id = 0;
		Timestamp t = new Timestamp(time);
		try{
			st = connection.prepareStatement("INSERT INTO alarm(sensorId,strength,time) VALUES(?,?,?)");
			st.setObject(1, sensorId);
			st.setObject(2, strength);
			st.setObject(3, t);
			st.executeUpdate();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			id = res.getInt(1);
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the alarm ");
			e.printStackTrace();
			return 0;
		}
		return id;
	}
	
	public void addRoute( Path path, boolean isReal){
		try{
			
			st = connection.prepareStatement("INSERT INTO route(route,isreal,starttime,finishtime) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ArrayList<Point> pointsList = path.getPoints();
			int size = pointsList.size();
			PGpoint[] points = new PGpoint[size];
			for(int i = 0; i < size ; i++)
				points[i] = new PGpoint(pointsList.get(i).x,pointsList.get(i).y);
			PGpath pathData = new PGpath(points,true);
			st.setObject(1, pathData);
			st.setObject(2, isReal);
			st.setObject(3, path.getStartTime());
			st.setObject(4, path.getFinishTime());
			st.executeUpdate();
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the route ");
			e.printStackTrace();
		}
		
	}
	
public List<Path> getRoutes(){
		
		List<Path> list = new ArrayList();
		try {
			stmt = connection.createStatement();
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			PreparedStatement stmt1 = connection.prepareStatement("SELECT starttime FROM route ");
			rs1 = stmt1.executeQuery();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			PreparedStatement stmt2 = connection.prepareStatement("SELECT route FROM route ");
			rs2 = stmt2.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ResultSet rs3 = null;
		try {
			PreparedStatement stmt3 = connection.prepareStatement("SELECT isreal FROM route ");
			rs3 = stmt3.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs4 = null;
		try {
			PreparedStatement stmt4 = connection.prepareStatement("SELECT finishtime FROM route ");
			rs4 = stmt4.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		
		while(rs1.next() && rs2.next() && rs3.next() && rs4.next())
		{			
			Path path = new Path();
			PGpath pathData = (PGpath) rs2.getObject(1);
			PGpoint[] pointArray = pathData.points;
			int size = pointArray.length;
			for(int i = 0; i< size ; i++)
				path.addPoint(new Point((int)pointArray[i].x,(int) pointArray[i].y));
			path.setFinishTime((String)rs4.getObject(1));
			path.setStartTime((String)rs1.getObject(1));		
			list.add(path);
							
		}
		}
		catch(SQLException e){	
			e.printStackTrace();
		}
		return list;
	
}
	
	public List<Sensor> getSensors(){
		
		ArrayList<Sensor> list = new ArrayList();
		//return list;/*
		try {
			stmt = connection.createStatement();
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			PreparedStatement stmt1 = connection.prepareStatement("SELECT sensorid FROM sensor ");
			rs1 = stmt1.executeQuery();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			PreparedStatement stmt2 = connection.prepareStatement("SELECT mac FROM sensor ");
			rs2 = stmt2.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ResultSet rs3 = null;
		try {
			PreparedStatement stmt3 = connection.prepareStatement("SELECT location FROM sensor ");
			rs3 = stmt3.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		
		while(rs1.next() && rs2.next() && rs3.next())
		{
			int x = (int) ((PGpoint)rs3.getObject(1)).x;
			int y = (int) ((PGpoint)rs3.getObject(1)).y;
			Sensor s = new Sensor(rs1.getInt(1), rs2.getString(1), new Point(x,y));
			list.add(s);				
		}
		}
		catch(SQLException e){	
			e.printStackTrace();
		}
		return list;
	
}
	
public List<Alarm> getAlarms(){
		
		ArrayList<Alarm> list = new ArrayList();
		try {
			stmt = connection.createStatement();
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			PreparedStatement stmt1 = connection.prepareStatement("SELECT time FROM alarm ");
			rs1 = stmt1.executeQuery();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			PreparedStatement stmt2 = connection.prepareStatement("SELECT sensorid FROM alarm ");
			rs2 = stmt2.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ResultSet rs3 = null;
		try {
			PreparedStatement stmt3 = connection.prepareStatement("SELECT strength FROM alarm ");
			rs3 = stmt3.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		
		while(rs1.next() && rs2.next() && rs3.next())
		{			
			Alarm a = new Alarm(rs2.getInt(1), rs3.getInt(1), rs1.getTimestamp(1).getTime());
			System.out.println(a);
			list.add(a);				
		}
		}
		catch(SQLException e){	
			e.printStackTrace();
		}
		return list;  
	
	}
	static public DatabaseManager getInstance() {
		if(instance == null)
			return instance = new DatabaseManager();
		return instance;
	}
	
}
	
	 
	


