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
	
	 
	


