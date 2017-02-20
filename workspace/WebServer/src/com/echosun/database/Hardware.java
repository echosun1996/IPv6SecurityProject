package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Hardware {

	public void Hardware_Del(String input) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `Information` where (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input);
		pre.execute();
	}

	public void Hardware_Upd(String key, String value) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `password`=? WHERE (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, value);
		pre.setString(2, key);
		pre.execute();
	}

	public void Hardware_CName(String UID, String name) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `name`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, name);
		pre.setString(2, UID);
		pre.execute();
	}

	public List<Hardware_Model> Hardware_Sel() throws Exception {

		String sql = "select * from Information;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<Hardware_Model> ress = new ArrayList<Hardware_Model>();
		Hardware_Model res = null;
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new Hardware_Model();
			res.setUID(sqlres.getString("UID"));
			res.setName(sqlres.getString("name"));
			res.setCategory(sqlres.getInt("category"));
			res.setStatus(sqlres.getInt("status"));
			ress.add(res);
		}

		return ress;
	}

}
