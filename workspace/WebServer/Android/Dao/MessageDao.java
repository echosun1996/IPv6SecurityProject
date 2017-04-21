package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import utils.MySqlStatus;

public class MessageDao {
	public JSONObject getMessage(String UID) {
		ResultSet rs = null;
		JSONObject jsonObject = new JSONObject();
		int i = 1;
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection conn = connection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql = "select * from Message where UID='" + UID + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				JSONObject message = new JSONObject();
				message.put("time", rs.getString(2));
				message.put("message", rs.getString(3));
				message.put("status", rs.getString(4));
				jsonObject.put("message" + i, message);
				i++;
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}

	public int updateStatus(String time) {
		int roll = 0;
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection conn = connection.getConnection();
			Statement stmt = (Statement) conn.createStatement();

			String sql = "update Message set status ='0' where time='" + time + "'";
			roll = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}
}
