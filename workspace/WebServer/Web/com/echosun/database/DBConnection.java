package com.echosun.database;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

/*
 * 数据库连接类
 * getConnection() 返回Connection型连接
 */
public class DBConnection {
	static String URL = "jdbc:mysql://127.0.0.1:3306/IPV6Security?characterEncoding=utf8&useSSL=true";
	static String USERNAME = "IPV6SecurityWeb";
	static String PASSWORD = "==test!!test==";
	private Connection con = null;

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return con;
	}

}
