package com.nnrs.socket.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.server.dao.ShareDao;
import com.nnrs.socket.util.JDBCUtil;

public class ShareDaoImpl implements ShareDao {

	
	@Override
	public List<Novel> selectNovel(String novel) throws Exception {
		List<Novel> novels = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from novel_information where n_id like binary ? or n_name like binary ? order by visit_num desc";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, '%'+novel+'%');
		ps.setString(2, '%'+novel+'%');
		ResultSet st =  ps.executeQuery();
		while(st.next()) {
			novels.add(
					new Novel(st.getString("n_id"),
							st.getString("n_name"),
							st.getInt("chapter_num"),
							st.getInt("visit_num")));
		}
		JDBCUtil.close(conn, ps, st);
		return novels; 
	}

	@Override
	public List<Novel> rankingList() throws Exception {
		List<Novel> novels = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from novel_information order by visit_num desc limit 10";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet st =  ps.executeQuery();
		while(st.next()) {
			novels.add(
					new Novel(st.getString("n_id"),
							st.getString("n_name"),
							st.getInt("chapter_num"),
							st.getInt("visit_num")));
		}
		JDBCUtil.close(conn, ps, st);
		return novels;
	}



	
}
