package com.echosun.database;

/*
 * Login_Add(Login_Model input) 新建用户 传入用户模型 无返回
 * Login_Del(String username) 删除用户 传入用户名 无返回
 * Login_Upd(String password, String username) 修改用户密码 传入用户名和密码 无返回
 * Login_Sel() 查询所有用户 无传入 返回所有用户
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Login {
	public void Login_Add(Login_Model input) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "insert into `LoginUser`(`username`, `password`, `info`) values(?,?,?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, input.getUsername());
		pre.setString(2, input.getPassword());
		pre.setString(3, input.getInfo());
		pre.execute();
	}

	public void Login_Del(String username) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `LoginUser` where (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, username);
		pre.execute();
	}

	public void Login_Upd(String password, String username) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `LoginUser` SET `password`=? WHERE (`username`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, username);
		pre.setString(2, password);
		pre.execute();
	}

	public List<Login_Model> Login_Sel() throws Exception {

		String sql = "select * from LoginUser;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<Login_Model> ress = new ArrayList<Login_Model>();
		Login_Model res = null;
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new Login_Model();
			res.setUsername(sqlres.getString("username"));
			res.setPassword(sqlres.getString("password"));
			ress.add(res);
		}

		return ress;
	}

}
