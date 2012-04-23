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
import org.postgresql.geometric.PGpoint;

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
	
	public int addSensor(Sensor sensor){	
		try{
			st = connection.prepareStatement("INSERT INTO sensor(sensorid,mac,location) VALUES(?,?,?)");
			st.setObject(1, sensor.getId());
			st.setObject(2, sensor.getMac());
			int x = sensor.getLocation().x;
			int y = sensor.getLocation().y;
			st.setObject(3, new PGpoint(x,y));
			st.executeUpdate();
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the sensor ");
			e.printStackTrace();
			disconnect();
			return 0;
		}
		disconnect();
		return sensor.getId();               
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
			disconnect();
			return 0;
		}
		disconnect();
		return id;
	}
	
	public int addRoute(int id, List<Point> route, boolean isReal){
		try{
			st = connection.prepareStatement("INSERT INTO route(id,route,isReal) VALUES(?,?,?)");
			st.setObject(1, id);
			st.setObject(2, route);
			st.setObject(3, isReal);
			st.executeUpdate();
			st.close();
		}catch(SQLException e){
			System.out.println(" Could not add the alarm ");
			e.printStackTrace();
			disconnect();
			return 0;
		}
		disconnect();
		return id;
		
	}
	
	public List<Sensor> getSensors() throws SQLException{
			
		Statement stmt = connection.createStatement();
		ResultSet rs1 = stmt.executeQuery("SELECT sensorid FROM sensor");
		int i = 0;
		Object o = rs1.getObject(i);
		System.out.println("kk");
		while(o != null)
		{
			System.out.println("Sensor id =  "+ o.toString());
			i++;
			o = rs1.getObject(i);		
		}	
		return null;
	
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
	
	 
	


