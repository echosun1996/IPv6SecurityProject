package utils;

import com.echosun.database.DBConnection;
import com.mysql.jdbc.Connection;

public class MySqlStatus {
	/*
	 * static String URL =
	 * "jdbc:mysql://address=(protocol=tcp)(host=fe80::e717:d038:2dc6:4e15)(port=3306)/IPV6Security?characterEncoding=utf8&useSSL=true";
	 * static String USERNAME = "IPV6SecurityWeb"; static String PASSWORD =
	 * "==test!!test=="; private Connection con = null;
	 */

	public Connection getConnection() throws Exception {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.getConnection();
	}

}