package com.comcast.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection con;
	public void getDbconnection(String url , String username, String password) throws Throwable
	{
		try {
		Driver driver= new Driver();// u have to get the driver from the my sql
		DriverManager.registerDriver(driver);// registration of driver
		
		 con = DriverManager.getConnection(url, username, password);
		
		
	}
		catch(Exception e) {
			
		}
}
	public void getDbconnection() throws Throwable
	{
		try {
		Driver driver= new Driver();// u have to get the driver from the my sql
		DriverManager.registerDriver(driver);// registration of driver
		
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1999");
		
		
	}
		catch(Exception e) {
			
		}
}
	public void closeDbconnection() throws SQLException  {
		try {
			
		}
		catch(Exception e) {
			
		}
		con.close();
	}
	
	public ResultSet  executeSelectQuery(String query) throws SQLException {
		ResultSet  result = null;
		try {
		Statement state = con.createStatement();
		  result = state.executeQuery(query);// we have a entire table
		}catch(Exception e){
			// we have complete table
		
	  
	}
		return result;// going to return table in the form of result
}
	public int  executenonSelectQuery(String query) throws SQLException {
		int  result = 0;
		try {
		Statement state = con.createStatement();
		 result =state.executeUpdate(query);
		}catch(Exception e){
}
		return result;
	}
}
	
