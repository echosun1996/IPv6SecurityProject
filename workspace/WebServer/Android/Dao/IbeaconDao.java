package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.MySqlStatus;

public class IbeaconDao {

	public int reset(String UID) {
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection con = connection.getConnection();
			String sql = "UPDATE `Information` SET `status`=2 WHERE (`UID`=?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, UID);
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

	public int rename(String UID, String name) {
		try {
			MySqlStatus connection = new MySqlStatus();
			Connection con = connection.getConnection();
			String sql = "UPDATE `Information` SET `name`=? WHERE (`UID`=?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, UID);
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
