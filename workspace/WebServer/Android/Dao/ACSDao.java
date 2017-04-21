package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import utils.MySqlStatus;

public class ACSDao {

	public JSONObject getACS(String UID) {
		ResultSet rs = null;
		int i = 1;
		JSONObject jsonObject = new JSONObject();
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection conn = connection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql = "select * from ACSInformation";
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				JSONObject infomation = new JSONObject();
				infomation.put("UID", rs.getString(1));
				infomation.put("name", rs.getString(2));
				infomation.put("status", rs.getString(3));
				infomation.put("time", rs.getString(4));
				jsonObject.put("infomation" + i, infomation);
				i++;
			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
