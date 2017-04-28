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
			jsonObject.put("amounts", i - 1);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public int reset(String ID, int status) {
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection con = connection.getConnection();
			String sql = "UPDATE `ACSInformation` SET `status`=" + status + " WHERE (`ID`=?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, ID);
			int ret = pre.executeUpdate();
			if (ret == 0)
				System.out.println("update failed!");
			con.close();
			return ret;
		} catch (Exception e) {
			System.out.println("Updatename Error!");
		}
		return 0;

	}

	public int rename(String ID, String name) {
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection con = connection.getConnection();
			String sql = "UPDATE `ACSInformation` SET `name`=? WHERE (`ID`=?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, ID);
			int ret = pre.executeUpdate();
			if (ret == 0)
				System.out.println("update failed!");
			con.close();
			return ret;
		} catch (Exception e) {
			System.out.println("Updatename Error!");
		}
		return 0;

	}
}
