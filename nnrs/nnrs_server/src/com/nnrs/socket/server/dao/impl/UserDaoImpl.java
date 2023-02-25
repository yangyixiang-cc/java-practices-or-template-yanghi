package com.nnrs.socket.server.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.server.dao.UserDao;
import com.nnrs.socket.util.DirConst;
import com.nnrs.socket.util.JDBCUtil;
import com.nnrs.socket.util.Md5Util;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String userId, String userpassword) throws Exception {
		User user = null;
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from user where u_id=? and u_password=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, userpassword);
		ResultSet st =  ps.executeQuery();
		if(st.next()) {
			user = new User();
			user.setUserId(st.getString("u_id"));
			user.setUserName(st.getString("u_name"));
			user.setUserPassword(st.getString("u_password"));
			user.setStatus(st.getBoolean("status"));
			user.setDate(st.getDate("date"));
		}
		JDBCUtil.close(conn, ps, st);
		return user;
	}

	@Override
	public int userRegister(User user) throws Exception {
		int result = DirConst.REGISTER_USER_FAIL;
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
			result = result == 1 ? DirConst.REGISTER_USER_SUCCESS : DirConst.REGISTER_USER_FAIL;
			JDBCUtil.close(conn, ps, null);
		}
		return result;
	}

	@Override
	public int modifyPWD(String userID,String newPassword) throws Exception {
		int result = DirConst.MODIFY_PWD_FAIL;
		if(isExistUser(userID)) {
			Connection conn =  JDBCUtil.getConnection();
			String sql = "update user set u_password=? where u_id=?";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, userID);
			result =  ps.executeUpdate();
			result = result == 1 ? DirConst.MODIFY_PWD_SUCCESS : DirConst.MODIFY_PWD_FAIL;
			JDBCUtil.close(conn, ps, null);
		}
		return result;
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
	public List<History> showHistory(User user) throws Exception {
		List<History> historys = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();	
		String sql = "select n.n_id,n.n_name,h.read_chapters from history h,novel_information n,user u " + 
				"where "+ 
				"h.n_id = n.n_id and "+ 
				"h.u_id = u.u_id and "+ 
				"u.u_id = ?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, user.getUserId());
		ResultSet st =  ps.executeQuery();
		while(st.next()) {	
			historys.add(
					new History(st.getString("n.n_id"),
							st.getString("n.n_name"),
							st.getString("h.read_chapters")
					));
		}
		JDBCUtil.close(conn, ps, st);
		return historys;
	}

	@Override
	public int autoHistory(String[] data) throws Exception {
		int result = DirConst.AUTO_FAIL;
		Connection conn =  JDBCUtil.getConnection();	
		String sql = "insert into history values(?,?,?)";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, data[2]);
		ps.setString(2,data[0]);
		ps.setString(3,data[1]);
		int st =  ps.executeUpdate();
		if(st == 1) {
			result = DirConst.AUTO_SUCCESS;
		}
		JDBCUtil.close(conn, ps, null);
		return result;
	}

}
