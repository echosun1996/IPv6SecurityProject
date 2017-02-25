package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Message {

	public void Message_Del(String UID) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `Message` where (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, UID);
		pre.execute();
	}

	public void Message_Init(String UID, String msg,int status) throws Exception {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String df = dateFormat.format( now ); 
		
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "INSERT INTO `Message`(`UID` ,`time`,`message`,`status`)VALUES (?,?,?,?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, UID);
		pre.setString(2, df);
		pre.setString(3, msg);
		pre.setInt(4, status);
		pre.execute();
	}
	public void Message_SetNormal() throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Message` SET `status`=0 WHERE `status`=3 OR `status`=2;";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.execute();
	}
	
	public void Message_CMessage(String UID, String newmsg) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Message` SET `message`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, newmsg);
		pre.setString(2, UID);
		pre.execute();
	}

	
	public List<Message_Model> Message_SelALL() throws Exception {

		String sql = "select `uid`,`status`,`message`,`time`  from Message;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<Message_Model> ress = new ArrayList<Message_Model>();
		Message_Model res = null;

		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new Message_Model();
			res.setUID(sqlres.getString("UID"));
			res.setStatus(sqlres.getInt("status"));
			res.setMessage(sqlres.getString("message"));
			res.setTime(sqlres.getString("time"));
			ress.add(res);
		}

		return ress;
	}
	public List<Message_Model> Message_Sel() throws Exception {

		String sql = "select `uid`,`status`,`message`,`time`  from Message where `status`!=0;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<Message_Model> ress = new ArrayList<Message_Model>();
		Message_Model res = null;

		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new Message_Model();
			res.setUID(sqlres.getString("UID"));
			res.setStatus(sqlres.getInt("status"));
			res.setMessage(sqlres.getString("message"));
			res.setTime(sqlres.getString("time"));
			ress.add(res);
		}

		return ress;
	}

}
