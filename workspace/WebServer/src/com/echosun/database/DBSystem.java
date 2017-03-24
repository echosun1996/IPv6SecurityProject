package com.echosun.database;

/*
 * 系统设置条目类
 * System_Add 新建系统设置条目 传入系统设置条目模型 无返回
 * System_Del(String key) 删除系统设置条目 传入设置项名 无返回
 * System_Upd(String key, String value) 修改系统设置条目 传入设置项名和对应值 无返回
 * System_Sel() 查询所有设置 无传入 返回所有设置
 */
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

	public void System_Del(String key) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `System` where (`key`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, key);
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
