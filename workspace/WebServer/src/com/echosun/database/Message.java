package com.echosun.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Message {


	public void Message_Del(String UID) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "delete from `Message` where (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, UID);
		pre.execute();
	}

	public void Message_CMessage(String UID, String newmsg) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sql = "UPDATE `Message` SET `message`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, newmsg);
		pre.setString(2, UID);
		pre.execute();
	}
	public List<Message_Model> Message_Sel() {

		String sql = "select * from Message;";
		Connection con = DBConnection.getConnection();
		List<Message_Model> ress = new ArrayList<Message_Model>();
		Message_Model res = null;
		try {
			Statement st = (Statement) con.createStatement();
			ResultSet sqlres = st.executeQuery(sql);
			while (sqlres.next()) {
				res = new Message_Model();
				res.setUID(sqlres.getString("UID"));
				res.setMessage(sqlres.getString("message"));
				res.setTime(sqlres.getString("time"));
				ress.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ress;
	}

}
