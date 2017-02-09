package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Login {
	public void Login_Add(Login_Model input) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "insert into `LoginUser`(`username`, `password`, `info`) values(?,?,?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input.getUsername());
		pre.setString(2, input.getPassword());
		pre.setString(3, input.getInfo());
		pre.execute();
	}

	public void Login_Del(String input) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "delete from `LoginUser` where (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input);
		pre.execute();
	}

	public void Login_Upd(String key, String value) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "UPDATE `LoginUser` SET `password`=? WHERE (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, value);
		pre.setString(2, key);
		pre.execute();
	}

	public List<Login_Model> Login_Sel() {

		String sql = "select * from LoginUser;";
		Connection con = DBConnection.getConnection();
		List<Login_Model> ress = new ArrayList<Login_Model>();
		Login_Model res = null;
		try {
			Statement st = (Statement) con.createStatement();
			ResultSet sqlres = st.executeQuery(sql);
			while (sqlres.next()) {
				res = new Login_Model();
				res.setUsername(sqlres.getString("username"));
				res.setPassword(sqlres.getString("password"));
				ress.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ress;
	}

}
