package com.echosun.database;


import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnection {
	static String URL = "jdbc:mysql://192.168.1.2:3306/IPV6Security";
	static String USERNAME = "IPV6SecurityWeb";
	static String PASSWORD = "test!!test";
	private static Connection con = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(URL, USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}

}
