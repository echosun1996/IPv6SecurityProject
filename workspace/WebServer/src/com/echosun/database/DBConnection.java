package com.echosun.database;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class DBConnection {
	static String URL = "jdbc:mysql://192.168.1.204:3306/IPV6Security";
	static String USERNAME = "IPV6SecurityWeb";
	static String PASSWORD = "test!!test";
	private Connection con = null;

	public Connection getConnection() throws Exception {
	
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

		return con;
	}

}
