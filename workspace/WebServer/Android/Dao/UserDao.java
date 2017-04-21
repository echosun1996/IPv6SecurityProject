package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.MySqlStatus;

public class UserDao {
	public boolean login(String username, String password) {
		ResultSet rs = null;
		boolean flag = false;
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection conn = connection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			//String check = "f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
			//System.out.println(DigestUtils.sha1Hex(password + check));
			String sql = "select * from LoginUser where username='" + username + "' and password='"
					+ password  + "'";
			rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) {
				flag = true;
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
