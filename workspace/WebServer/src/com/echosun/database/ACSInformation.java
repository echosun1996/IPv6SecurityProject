package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ACSInformation {
	
	public int ACSInformation_GetSta(String id) throws Exception {
		int res=0;
		String sql = "SELECT `status` FROM `ACSInformation` WHERE `id`=? ;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, id);
		ResultSet sqlres = pre.executeQuery(); 
		
		while (sqlres.next()) {
			res=sqlres.getInt("status");
			break;
		}
		return res;
	}
	
	public int ACSInformation_CSta(String id) throws Exception {
		int res=0;
		String sql = "SELECT `status` FROM `ACSInformation` WHERE `id`=? ;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, id);
		ResultSet sqlres = pre.executeQuery(); 
		
		while (sqlres.next()) {
			res=sqlres.getInt("status");
			break;
		}
		
		
		if(res==0)res=1;
		else res=0;
		
		
		sql = "UPDATE `ACSInformation` SET `status`=? WHERE (`ID`=?)";
		pre = con.prepareStatement(sql);
		pre.setInt(1, res);
		pre.setString(2, id);
		pre.execute();
		
		
		return res;
	}
	
	public void ACSInformation_CName(String id,String name) throws Exception {
		String sql = "UPDATE `ACSInformation` SET `name`=? WHERE (`ID`=?)";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, name);
		pre.setString(2, id);
		pre.execute();
	}
	
	
	public int ACSInformation_Init(ACSInformation_Model in) 
	{
		String sql = "INSERT INTO `ACSInformation` (`ID` ,`status`,`time`,`name`)VALUES (?,?,?,?)";
		DBConnection connection = new DBConnection();
		Connection con;
		try {
			con = connection.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, in.getID());
			pre.setInt(2, in.getStatus());
			pre.setString(3, in.getTime());
			pre.setString(4, in.getName());
			pre.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	public List<ACSInformation_Model> ACSInformation_SelALL() throws Exception {

		String sql = "select `ID`,`name`,`status`,`time` from ACSInformation;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<ACSInformation_Model> ress = new ArrayList<ACSInformation_Model>();
		ACSInformation_Model res = null;

		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new ACSInformation_Model();
			res.setID(sqlres.getString("ID"));
			res.setName(sqlres.getString("name"));
			res.setStatus(sqlres.getInt("status"));
			res.setTime(sqlres.getString("time"));
			ress.add(res);
		}

		return ress;
	}

}
