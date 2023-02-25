package com.nnrs.socket.server.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


import com.nnrs.socket.entity.Administrator;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.server.dao.AdministratorDao;
import com.nnrs.socket.util.DirConst;
import com.nnrs.socket.util.FileUtil;
import com.nnrs.socket.util.JDBCUtil;
import com.nnrs.socket.util.Md5Util;

public class AdministratorDaoImpl implements AdministratorDao {

	@Override
	public Administrator login(String adminNumber, String adminPwd) throws Exception {
		Administrator admin = null;
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from administrator where a_id=? and a_password=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, adminNumber);
		ps.setString(2, adminPwd);
		ResultSet st =  ps.executeQuery();
		if(st.next()) {
			admin = new Administrator();
			admin.setAdminNumber(st.getString("a_id"));
			admin.setAdminName(st.getString("a_name"));
			admin.setAdminPwd(st.getString("a_password"));
		}
		JDBCUtil.close(conn, ps, st);
		return admin;
	}

	@Override
	public int insertUser(User user) throws Exception {
		int result = DirConst.ADD_USER_FAIL;
		if(!isExistUser(user.getUserId())) {
			Connection conn =  JDBCUtil.getConnection();
			String sql = "insert into user values(?,?,?,?,?)";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setBoolean(4, true);
			ps.setDate(5, (Date) user.getDate());
			result =  ps.executeUpdate();
			result = result == 1 ? DirConst.ADD_USER_SUCCESS : DirConst.ADD_USER_FAIL;
			JDBCUtil.close(conn, ps, null);
		}
		return result;
	}

	@Override
	public int offUser(String UserID) throws Exception {
		int result = DirConst.OFF_USER_FAIL;
		if(isExistUser(UserID)) {
			Connection conn =  JDBCUtil.getConnection();
			String sql = "update user set status=? where u_id = ?";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(2, UserID);
			result =  ps.executeUpdate();
			result = result == 1 ? DirConst.OFF_USER_SUCCESS : DirConst.OFF_USER_FAIL;
			JDBCUtil.close(conn, ps, null);
		}
		return result;

	}

	@Override
	public List<User> showUsers() throws Exception {
		List<User> users = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from user";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet st = ps.executeQuery();
		while(st.next()) {
			users.add(
					new User(
					st.getString("u_id"),
					st.getString("u_name"),
					st.getString("u_password"),
					st.getBoolean("status"),
					st.getDate("date")));
		}
		JDBCUtil.close(conn, ps, st);
		return users;
	}

	@Override
	public boolean isExistAdmin(String adminNumber) throws Exception {
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from administrator where a_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, adminNumber);
		ResultSet st =  ps.executeQuery();
		if(st.next()) {
			JDBCUtil.close(conn, ps, st);
			return true;
		}
		return false;
	}


	@Override
	public boolean isExistUser(String userId) throws Exception {
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from user where u_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, userId);
		ResultSet st =  ps.executeQuery();
		if(st.next()) {
			JDBCUtil.close(conn, ps, st);
			return true;
		}
		return false;
	}

	@Override
	public List<String> showLog() throws Exception {
		List<String> list = new ArrayList<>();
		BufferedReader re = new BufferedReader(new FileReader(FileUtil.getPath()+"bin/journal.log"));
		String read = "";
		while((read = re.readLine()) != null) {
			list.add(read);
		}
		re.close();
		return list;
	}
}
