package com.echosun.database;

/*
 * 消息功能类
 * Hardware_Del(String uid) 删除对应设备 传入uid 无返回
 * Hardware_Init(Hardware_Model in) 初始化设备 传入设备模型 成功返回1，否则0
 * Hardware_check(String uid, String check) 核对设备密码 传入uid和密码 返回真假
 * Hardware_GetUID(String category) 查询对应类别的下一个uid 传入类别 返回可初始化的uid(从N001开始,N为类别)
 * Hardware_GetSta(String uid) 查询设备状态 传入uid 返回设备状态
 * Hardware_CSta(String UID, String status) 修改设备状态 传入uid和状态 无返回
 * Hardware_CName(String UID, String name) 修改设备名字 传入uid和名字 无返回
 * Hardware_SetNormal() 设置所有设备为正常 无传入 无返回
 * Hardware_Sum() 查询设备总数 无传入 返回设备总数
 * Hardware_Sel() 查询所有设备 无传入 返回所有设备
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Hardware {

	public void Hardware_Del(String uid) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "delete from `Information` where (`uid`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, uid);
		pre.execute();
		con.close();
	}

	public int Hardware_Init(Hardware_Model in) {
		String sql = "INSERT INTO `Information` (`UID` ,`name`,`category`,`status`,`address` ,`check`,`IbeaconID`)VALUES (?,?,?,?,?,?,?)";
		DBConnection connection = new DBConnection();
		Connection con;
		try {
			con = connection.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, in.getUID());
			pre.setString(2, in.getName());
			pre.setInt(3, in.getCategory());
			pre.setInt(4, in.getStatus());
			pre.setString(5, in.getAddress());
			pre.setString(6, in.getCheck());
			pre.setString(7, in.getIbeaconID());
			pre.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public boolean Hardware_check(String uid, String check) throws Exception {

		String sql = "SELECT `check` FROM `Information` WHERE `uid`=? ";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, uid);
		ResultSet sqlres = pre.executeQuery();
		String res = null;
		while (sqlres.next()) {
			res = sqlres.getString("check");
			break;
		}
		con.close();
		if (res == null)
			return false;
		else if (res.equals(check))
			return true;
		else
			return false;
	}

	public int Hardware_GetUID(String category) throws Exception {
		String res = null;
		String sql = "SELECT uid FROM Information WHERE category=? ORDER BY uid DESC;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, category);
		ResultSet sqlres = pre.executeQuery();

		while (sqlres.next()) {
			res = sqlres.getString("UID");
			break;
		}
		if (res == null) {
			res = category + "000";
		}
		con.close();
		return Integer.parseInt(res) + 1;
	}

	public String Hardware_GetSta(String uid) throws Exception {
		String res = null;
		String sql = "SELECT status FROM Information WHERE UID=?;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, uid);
		ResultSet sqlres = pre.executeQuery();
		while (sqlres.next()) {
			res = sqlres.getString("status");
			break;
		}
		con.close();
		return res;
	}

	public void Hardware_CSta(String UID, String status) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `status`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, status);
		pre.setString(2, UID);
		pre.execute();
		con.close();
	}

	public void Hardware_CName(String UID, String name) throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `name`=? WHERE (`UID`=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, name);
		pre.setString(2, UID);
		pre.execute();
		con.close();
	}

	public void Hardware_SetNormal() throws Exception {
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		String sql = "UPDATE `Information` SET `status`=2 WHERE `status`=3;";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.execute();
		con.close();
	}

	public int Hardware_Sum() throws Exception {
		int ret = -1;
		String sql = "select count(*)sum from Information where `status`=1 OR `status`=2 OR `status`=3;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			ret = sqlres.getInt("sum");
		}
		con.close();
		return ret;
	}

	public List<Hardware_Model> Hardware_AfterSel(int page) throws Exception {
		List<Hardware_Model> temp = Hardware_Sel();
		int pageSum = (temp.size() % 6 == 0) ? (temp.size() / 6) : (temp.size() / 6 + 1);
		if (page > pageSum)
			return null;

		List<Hardware_Model> ret = new ArrayList<Hardware_Model>();

		for (int i = page * 6 - 6; i < page * 6 && i < temp.size(); i++) {
			ret.add(temp.get(i));
		}
		for (int i = 0; i < ret.size(); i++)
			System.out.println(ret.get(i).getUID());
		return ret;
	}

	public List<Hardware_Model> Hardware_Sel() throws Exception {
		String sql = "select * from Information;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<Hardware_Model> ress = new ArrayList<Hardware_Model>();
		Hardware_Model res = null;
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new Hardware_Model();
			res.setUID(sqlres.getString("UID"));
			res.setName(sqlres.getString("name"));
			res.setCategory(sqlres.getInt("category"));
			res.setStatus(sqlres.getInt("status"));
			ress.add(res);
		}
		con.close();
		return ress;
	}

	public List<Hardware_Model> Hardware_Warn() throws Exception {
		String sql = "select `UID`,`name` from Information where `status`=3;";
		DBConnection connection = new DBConnection();
		Connection con = connection.getConnection();
		List<Hardware_Model> ress = new ArrayList<Hardware_Model>();
		Hardware_Model res = null;
		Statement st = (Statement) con.createStatement();
		ResultSet sqlres = st.executeQuery(sql);
		while (sqlres.next()) {
			res = new Hardware_Model();
			res.setUID(sqlres.getString("UID"));
			res.setName(sqlres.getString("name"));
			ress.add(res);
		}
		return ress;
	}


}
