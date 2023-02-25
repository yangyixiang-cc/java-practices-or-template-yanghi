package com.nnrs.socket.server.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.server.dao.NovelDao;
import com.nnrs.socket.util.DirConst;
import com.nnrs.socket.util.FileUtil;
import com.nnrs.socket.util.JDBCUtil;
import com.nnrs.socket.util.NumberUtil;

public class NovelDaoImpl implements NovelDao {

	@Override
	public boolean isExistNovel(String novelID) throws Exception {
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from novel_information where n_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, novelID);
		ResultSet st =  ps.executeQuery();
		if(st.next()) {
			JDBCUtil.close(conn, ps, st);
			return true;
		}
		return false;
	}

	@Override
	public int addNovel(String novelName, int chapters) throws Exception {
		int result = DirConst.ADD_NOVEL_FAIL;
		List<String> list1 = new ArrayList<>();
		List<Long> list2 = new ArrayList<>();
		File file = new File(FileUtil.getPath()+"resources/");
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select n_id,n_name from novel_information";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet st =  ps.executeQuery();
		while(st.next()) {
			list1.add(st.getString(2));
			list2.add(Long.valueOf(st.getString(1)));
		}
		
		if(!list1.contains(novelName)) {
			long num = Collections.max(list2);
			String sql2 = "insert into novel_information values(?,?,?,?)";
			ps = conn.prepareStatement(sql2);
			long n;
			while(true) {
				n = NumberUtil.getGeneratID();
				if(n > num) {
					break;
				}
			}
			ps.setString(1, String.valueOf(n));
			ps.setString(2,novelName);
			ps.setInt(3, chapters);
			ps.setInt(4, 0);
			int eu = ps.executeUpdate();
			result = eu == 1 ? DirConst.ADD_NOVEL_SUCCESS:DirConst.ADD_NOVEL_FAIL;
			JDBCUtil.close(conn, ps, st);
		}else {
			JDBCUtil.close(conn, ps, st);
			result = DirConst.ADD_NOVEL_FAIL;
		}
		return result;
	}

	@Override
	public int removeNovel(String novelID) throws Exception {
		int result = DirConst.DEL_NOVEL_FAIL;
		if(isExistNovel(novelID)) {
			Connection conn = JDBCUtil.getConnection();
			String sql = "delete from novel_information where n_id=?";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setString(1, novelID);
			result =  ps.executeUpdate();
			result = result == 1 ? DirConst.DEL_NOVEL_SUCCESS: DirConst.DEL_NOVEL_FAIL;
			JDBCUtil.close(conn, ps, null);
		}
		return result;
	}

	@Override
	public int modifyNovel(String novelID,String novelName,int chapterNum) throws Exception {
		int result = DirConst.MODIFY_NOVEL_FAIL;
		if(isExistNovel(novelID)) {
			Connection conn =  JDBCUtil.getConnection();
			String sql1 = "select * from novel_information where n_id=?";
			PreparedStatement ps =  conn.prepareStatement(sql1);
			ps.setString(1, novelID);
			ResultSet st =  ps.executeQuery();
			int visitNum = 0;
			if(st.next()) {
				if("".equals(novelName)) {
					novelName = st.getString("n_name");
					visitNum = st.getInt("visit_num");
				}
				if(chapterNum==0) {
					chapterNum = st.getInt("chapter_num");
				}
			}
			
			String sql2 = "update novel_information set n_name=?,chapter_num=?,visit_num=? where n_id=?";
            ps = conn.prepareStatement(sql2);
			ps.setString(1, novelName);
			ps.setInt(2, chapterNum);
			ps.setInt(3, visitNum);  //修改后置为0
			ps.setString(4, novelID);
			result =  ps.executeUpdate();
			JDBCUtil.close(conn, ps, st);
			result = result == 1 ? DirConst.MODIFY_NOVEL_SUCCESS: DirConst.MODIFY_NOVEL_FAIL;
		}
		return result;
	}
	@Override
	public List<Novel> onlineNovelList() throws Exception {
		List<Novel> novels = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select * from novel_information";
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

	

	@Override
	public StringBuilder readNovel(String novelId,int chapter) throws Exception {
		autoAddVisit(novelId);
		StringBuilder chapter_word = new StringBuilder();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select n_name from novel_information where n_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, novelId);
		ResultSet st =  ps.executeQuery();
		String fileName = "";
		if(st.next()) {
			fileName = st.getString("n_name");
		}
		JDBCUtil.close(conn, ps, st);
		File dir = new File(FileUtil.getPath()+"resources/"+fileName);
		File[] files = dir.listFiles();
		String chapterName = "";
		for(File f : files) {
			int i = NumberUtil.nTranferC(f.getName().substring(1, f.getName().indexOf("章")));
			if(chapter == i) {
				chapterName = f.getName();
				break;
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir.getCanonicalPath()+"/"+chapterName),"GBK"));
		String read = "";		    
        while((read = br.readLine()) != null){		    	
            chapter_word.append(read+"\n");
        }
        br.close();
		return chapter_word;
	}



	@Override
	public List<StringBuilder> downloadNovel(String novelId) throws Exception {
		List<StringBuilder> list = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select n_name from novel_information where n_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, novelId);
		ResultSet st =  ps.executeQuery();
		String fileName = null;
		BufferedReader fr = null;
		if(st.next()) {
			fileName = st.getString("n_name");
		}
		JDBCUtil.close(conn, ps, st);
		String dirPath = FileUtil.getPath()+"resources/"+fileName;
		File file = new File(dirPath);
		StringBuilder sb = null;
		String[] files = file.list(); //拿到文件名
		String read = "";
		for(int i = 0; i < files.length; i++) {
			String filepath = dirPath+"/"+files[i];
			fr = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"GBK"));
			sb = new StringBuilder();
			while((read = fr.readLine()) != null) {
				sb.append(read);
				sb.append("\r");
			}
			list.add(sb);
		}
		if(fr != null) {
			fr.close();
		}
		return list;
	}

	@Override
	public int novelChapterNum(String novelId) throws Exception {
		int result = 0;
		if(isExistNovel(novelId)) {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select chapter_num from novel_information where n_id=?";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setString(1, novelId);
			ResultSet st =  ps.executeQuery();
			if(st.next()) {
				result = st.getInt("chapter_num");
			}
			JDBCUtil.close(conn, ps, st);
		}
		return result;
	}

	@Override
	public List<String> bookCatalogue(String novelId) throws Exception {
		List<String> list = new ArrayList<>();
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select n_name from novel_information where n_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, novelId);
		ResultSet st =  ps.executeQuery();
		String fileName = null;
		BufferedReader fr = null;
		if(st.next()) {
			fileName = st.getString("n_name");
		}
		JDBCUtil.close(conn, ps, st);
		String dirPath = FileUtil.getPath()+"resources/"+fileName;
		File file = new File(dirPath);
		String[] files = file.list(); //拿到文件名
		for(String s : files) {
			list.add(s);
		}
		return list;
	}
	@Override
	public int autoAddnovels() throws Exception {
		int result = DirConst.AUTO_FAIL;
		List<String> list1 = new ArrayList<>();
		List<Long> list2 = new ArrayList<>();
		File file = new File(FileUtil.getPath()+"resources/");
		Connection conn =  JDBCUtil.getConnection();
		String sql = "select n_id,n_name from novel_information";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet st =  ps.executeQuery();
		while(st.next()) {
			list1.add(st.getString(2));
			list2.add(Long.valueOf(st.getString(1)));
		}
		File[] files = file.listFiles();
		String sql2 = "insert into novel_information values(?,?,?,?)";
		ps = conn.prepareStatement(sql2);
		if(list1.isEmpty()) {
			for(File f : files) {
				ps.setString(1, NumberUtil.getGeneratID().toString());
				ps.setString(2, f.getName());
				ps.setInt(3, FileUtil.chapterNum(f.getName()));
				ps.setInt(4, 0);
				ps.addBatch();
			}
			ps.executeBatch();
			result = DirConst.AUTO_SUCCESS;
			JDBCUtil.close(conn, ps, st);
		}else {
			long num = Collections.max(list2);
			for(File f : files) {
				if(!list1.contains(f.getName())) {
					long n;
					while(true) {
						n = NumberUtil.getGeneratID();
						if(n > num) {
							break;
						}
					}
					ps.setString(1, String.valueOf(n));
					ps.setString(2, f.getName());
					ps.setInt(3, FileUtil.chapterNum(f.getName()));
					ps.setInt(4, 0);
					ps.addBatch();
				}
			}
			ps.executeBatch();
			result = DirConst.AUTO_SUCCESS;
			JDBCUtil.close(conn, ps, st);
		}
		return result;
	}

	@Override
	public int autoAddVisit(String novelId) throws Exception {
		List<String> list1 = new ArrayList<>();
		List<Long> list2 = new ArrayList<>();
		File file = new File(FileUtil.getPath()+"resources/");
		Connection conn =  JDBCUtil.getConnection();
		String sql = "update novel_information set visit_num=visit_num+1 where n_id=?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, novelId);
		int ep = ps.executeUpdate();
		JDBCUtil.close(conn, ps, null);
		return  ep == 1? DirConst.ADD_USER_SUCCESS : DirConst.AUTO_FAIL;
	}

}
