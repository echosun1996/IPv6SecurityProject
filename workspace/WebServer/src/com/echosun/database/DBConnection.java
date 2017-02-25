package com.echosun.database;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class DBConnection {
	static String URL = "jdbc:mysql://address=(protocol=tcp)(host=fe80::e717:d038:2dc6:4e15)(port=3306)/IPV6Security?characterEncoding=utf8&useSSL=true";
	static String USERNAME = "IPV6SecurityWeb";
	static String PASSWORD = "==test!!test==";
	private Connection con = null;

	public Connection getConnection() throws Exception {
	
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return con;
	}

}
