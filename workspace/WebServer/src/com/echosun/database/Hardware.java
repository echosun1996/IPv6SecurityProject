package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Hardware {

	public void Hardware_Del(String uid) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `Information` where (`uid`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, uid);
		pre.execute();
	}

	public int Hardware_Init(Hardware_Model in) 
	{
		String sql = "INSERT INTO `Information` (`UID` ,`name`,`category`,`status`,`address` ,`check`)VALUES (?,?,?,?,?,?)";
		DBConnection connection = new DBConnection();
		Connection con;
		try {
			con = connection.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, in.getUID());
			pre.setString(2, in.getName());
			pre.setInt(3, in.getCategory());
			pre.setInt(4, in.getStatus());
			pre.setString(5, in.getAddress());
			pre.setString(6, in.getCheck());
			pre.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	public boolean Hardware_check(String uid,String check) throws Exception {
		
		String sql = "SELECT `check` FROM `Information` WHERE `uid`=? ";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, uid);
		ResultSet sqlres = pre.executeQuery(); 
		String res=null;
		while (sqlres.next()) {
			res=sqlres.getString("check");
			break;
		}
		if(res==null) return false;
		else if(res.equals(check))return true;
		else return false;
	}
	
	public int Hardware_GetUID(String category) throws Exception {
		String res=null;
		String sql = "SELECT uid FROM Information WHERE category=? ORDER BY uid DESC;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, category);
		ResultSet sqlres = pre.executeQuery(); 
		
		while (sqlres.next()) {
			res=sqlres.getString("UID");
			break;
		}
		if(res==null)
		{
			res=category+"000";
		}
		
		return Integer.parseInt(res)+1;
	}
	
	public String Hardware_GetSta(String uid) throws Exception {
		String res=null;
		String sql = "SELECT status FROM Information WHERE UID=?;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, uid);
		ResultSet sqlres = pre.executeQuery(); 
		
		while (sqlres.next()) {
			res=sqlres.getString("status");
			break;
		}

		
		return res;
	}
	
	
	public void Hardware_CSta(String UID, String status) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `status`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, status);
		pre.setString(2, UID);
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
	
	public void Hardware_SetNormal() throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `status`=2 WHERE `status`=3;";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.execute();
	}
	
	
	public int Hardware_Sum() throws Exception {
		String sql = "select count(*)sum from Information where `status`=1 OR `status`=2 OR `status`=3;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			return (sqlres.getInt("sum"));
			
		}
		return 0;
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
	public List<Hardware_Model> Hardware_Warn() throws Exception {
		String sql = "select `UID`,`name` from Information where `status`=3;";
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
			ress.add(res);
		}
		return ress;
	}

}
