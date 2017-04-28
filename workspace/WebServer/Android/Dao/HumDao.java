package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.MySqlStatus;

public class HumDao {

	public String getACS(String UID) {
		ResultSet rs = null;
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection conn = connection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql = "SELECT message FROM Message WHERE UID='" + UID + "' order by time desc;";
			rs = stmt.executeQuery(sql);
			while (rs.next() && rs != null) {
				System.out.println(rs.getString(1));
				return rs.getString(1);

			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0@0";
	}
}
