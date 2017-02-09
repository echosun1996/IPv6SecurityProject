package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Hardware {


	public void Hardware_Del(String input) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "delete from `LoginUser` where (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input);
		pre.execute();
	}

	public void Hardware_Upd(String key, String value) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "UPDATE `LoginUser` SET `password`=? WHERE (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, value);
		pre.setString(2, key);
		pre.execute();
	}
	public void Hardware_CName(String UID, String name) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "UPDATE `LoginUser` SET `name`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, name);
		pre.setString(2, UID);
		pre.execute();
	}
	public List<Hardware_Model> Hardware_Sel() {

		String sql = "select * from Information;";
		Connection con = DBConnection.getConnection();
		List<Hardware_Model> ress = new ArrayList<Hardware_Model>();
		Hardware_Model res = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ress;
	}

}
