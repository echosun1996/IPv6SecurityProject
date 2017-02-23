package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBSystem {
	public void System_Add(DBSystem_Model input) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "insert into `System`(`key`, `value`) values(?,?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input.getKey());
		pre.setString(2, input.getValue());
		pre.execute();
	}

	public void System_Del(String input) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `System` where (`key`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input);
		pre.execute();
	}

	public void System_Upd(String key, String value) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `System` SET `value`=? WHERE (`key`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, value);
		pre.setString(2, key);
		pre.execute();
	}

	public List<DBSystem_Model> System_Sel() throws Exception {

		String sql = "select * from System;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<DBSystem_Model> ress = new ArrayList<DBSystem_Model>();
		DBSystem_Model res = null;
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new DBSystem_Model();
			res.setKey(sqlres.getString("key"));
			res.setValue(sqlres.getString("value"));
			ress.add(res);
		}
		return ress;
	}

}
