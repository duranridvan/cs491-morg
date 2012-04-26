package com.aselsan.targettracking.databasemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.postgresql.geometric.PGpath;
import org.postgresql.geometric.PGpoint;

import com.aselsan.targettracking.Alarm;
import com.aselsan.targettracking.sensornetwork.Sensor;

public class DatabaseManager {
	
	private Connection connection;
	private static DatabaseManager instance = null;
	private PreparedStatement st;
	private Statement stmt = null;
	
	private DatabaseManager() throws SQLException{
		connection = null;
		st = null;
		connectDatabase("jdbc:postgresql://127.0.0.1:5432/", "postgres", "bric26", "postgres");
	}
	
	public void connectDatabase(String host,String username,String password,String dbname) throws SQLException
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
	
	public int addSensor(String mac, Point location){	
		int id = 0;
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
	
	
	public int addAlarm(int id,int sensorId,int strength){
		try{
			st = connection.prepareStatement("INSERT INTO alarm(eventId,sensorId,strength) VALUES(?,?,?)");
			st.setObject(1, id);
			st.setObject(2, sensorId);
			st.setObject(3, strength);
			st.executeUpdate();
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the alarm ");
			e.printStackTrace();
			return 0;
		}
		return id;
	}
	
	public int addRoute(int id, List<Point> route, boolean isReal){
		try{
			
			st = connection.prepareStatement("INSERT INTO route(eventid,route,isreal) VALUES(?,?,?)");
			st.setObject(1, id);
			int size = route.size();
			PGpoint[] points = new PGpoint[size];
			for(int i = 0; i < size ; i++)
				points[i] = new PGpoint(route.get(i).x,route.get(i).y);
			PGpath path = new PGpath(points,true);
			st.setObject(2, path);
			st.setObject(3, isReal);
			st.executeUpdate();
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the route ");
			e.printStackTrace();
			return 0;
		}
		return id;
		
	}
	
public List<List<Point>> getRoutes(){
		
		List<List<Point>> list = new ArrayList();
		try {
			stmt = connection.createStatement();
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			PreparedStatement stmt1 = connection.prepareStatement("SELECT eventid FROM route ");
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
		try{
		
		while(rs1.next() && rs2.next() && rs3.next())
		{			
			ArrayList<Point> pointList = new ArrayList();
			PGpath path = (PGpath) rs2.getObject(1);
			PGpoint[] pointArray = path.points;
			int size = pointArray.length;
			for(int i = 0; i< size ; i++)
				pointList.add(new Point((int)pointArray[i].x,(int) pointArray[i].y));
			list.add(pointList);
							
		}
		}
		catch(SQLException e){	
			e.printStackTrace();
		}
		return list;
	
}
	
	public List<Sensor> getSensors(){
		
		ArrayList<Sensor> list = new ArrayList();
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
			PreparedStatement stmt1 = connection.prepareStatement("SELECT eventid FROM alarm ");
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
			Alarm a = new Alarm(rs1.getInt(1), rs2.getInt(1), rs3.getInt(1));
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
			try {
				return instance = new DatabaseManager();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return instance;
	}
	
	}
	
	 
	


