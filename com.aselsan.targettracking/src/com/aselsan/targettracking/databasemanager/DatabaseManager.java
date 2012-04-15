package com.aselsan.targettracking.databasemanager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.aselsan.targettracking.sensornetwork.Sensor;

public class DatabaseManager {
	
	private Connection connection;
	private static DatabaseManager instance = null;
	private Statement st;
	
	private DatabaseManager(){
		connection = null;
		st = null;
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
	
	public int addSensor(Sensor sensor){
		connectDatabase("String host", "String username","String password","String dbname");
        try {

            st = connection.createStatement();
                String query = "INSERT INTO Sensor(sensorId) VALUES(sensor.getId(),sensor.getMac(),sensor.getLocation())";
                st.executeUpdate(query);
                
        } catch (SQLException ex) {
           // Logger lgr = Logger.getLogger(Prepared.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
               return sensor.getId();

            } catch (SQLException ex) {
               // Logger lgr = Logger.getLogger(NotPrepared.class.getName());
               //lgr.log(Level.SEVERE, ex.getMessage(), ex);
            	return 0;
            }
        }
    }
	static public DatabaseManager getInstance() {
		if(instance == null)
			return instance = new DatabaseManager();
		return instance;
	}
	
	}
	
	 
	


