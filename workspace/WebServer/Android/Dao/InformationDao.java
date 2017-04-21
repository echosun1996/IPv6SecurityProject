package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import utils.MySqlStatus;

public class InformationDao {
	public JSONObject getInfomation() {
		ResultSet rs = null;
		JSONObject jsonObject = new JSONObject();
		int i = 1;
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection conn = connection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String category = "";
			String status = "";
			String sql = "select * from Information where status!=1";
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				switch (rs.getString(3)) {
				case "1":
					category = "火警";
					break;
				case "2":
					category = "人体红外";
					break;
				case "3":
					category = "窗磁";
					break;
				case "4":
					category = "门禁";
					break;
				case "5":
					category = "空气质量";
					break;
				default:
					break;
				}
				switch (rs.getString(4)) {
				case "2":
					status = "正常";
					break;
				case "3":
					status = "警告";
					break;
				default:
					break;
				}
				JSONObject infomation = new JSONObject();
				infomation.put("UID", rs.getString(1));
				infomation.put("name", rs.getString(2));
				infomation.put("category", category);
				infomation.put("status", status);
				infomation.put("IbeaconID", rs.getString(7));
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
