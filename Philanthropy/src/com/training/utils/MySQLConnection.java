package com.training.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnection {
	public static Connection getMyOracleConnection() {
		
		Connection conn = null;
		Properties props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(new File("DbConnection.properties"));
			props.load(in);
			Class.forName(props.getProperty("db.driverClass"));
			conn = DriverManager.getConnection(props.getProperty("db.driverURL"), props.getProperty("db.username"), props.getProperty("db.password"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
