package com.run.arch.dao.Impl;

import com.run.arch.dao.PaperDao;
import com.run.arch.entity.Paper;
import com.run.arch.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaperDaoimpl implements PaperDao {
    @Override
    public List<Paper> getAllPaperList() throws Exception {
        List<Paper> papers = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from paper";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ResultSet st = ps.executeQuery();
        while(st.next()) {
            papers.add(
                    new Paper(
                            st.getString("id"),
                            st.getString("paper_name"),
                            st.getDate("pub_time"),
                            st.getString("pub_journal"),
                            st.getString("journal_level"),
                            st.getString("index_situation"),
                            st.getString("teacher_id"),
                            st.getDate("review_time"),
                            st.getInt("flog"),
                            st.getString("review_comments")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return papers;
    }

    @Override
    public int addPaper(Paper paper) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "insert into paper values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, paper.getId());
        ps.setString(2, paper.getPaperName());
        ps.setDate(3, new java.sql.Date(paper.getPubTime().getTime()));
        ps.setString(4, paper.getPubJournal());
        ps.setString(5, paper.getJournalLevel());
        ps.setString(6, paper.getIndex());
        ps.setString(7, paper.getTeacherId());
        ps.setDate(8,new java.sql.Date(paper.getReviewTime().getTime()));
        ps.setString(9,paper.getReviewComments());
        ps.setInt(10,paper.getFlog());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn,ps,null);
        return result;
    }

    @Override
    public int modifyPaper(Paper paper) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "update paper set paper_name=?,pub_time=?,pub_journal=?,journal_level=?,index_situation=?,review_time=?,flog=?,review_comments=?,teacher_id=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, paper.getPaperName());
        ps.setDate(2, new java.sql.Date(paper.getPubTime().getTime()));
        ps.setString(3, paper.getPubJournal());
        ps.setString(4, paper.getJournalLevel());
        ps.setString(5, paper.getIndex());
        ps.setDate(6, new java.sql.Date(paper.getReviewTime().getTime()));
        ps.setInt(7, paper.getFlog());
        ps.setString(8, paper.getReviewComments());
        ps.setString(9, paper.getTeacherId());
        ps.setString(10, paper.getId());
        result = ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public int removePaperById(String id) throws Exception {
        int result = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from paper where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public Paper getPaperById(String id) throws Exception {
        Paper paper = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from paper where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            paper = new Paper();
            paper.setId(st.getString("id"));
            paper.setPaperName(st.getString("paper_name"));
            paper.setPubTime(st.getDate("pub_time"));
            paper.setPubJournal(st.getString("pub_journal"));
            paper.setJournalLevel(st.getString("journal_level"));
            paper.setIndex(st.getString("index_situation"));
            paper.setTeacherId(st.getString("teacher_id"));
            paper.setFlog(st.getInt("flog"));
            paper.setReviewComments(st.getString("review_comments"));
            paper.setReviewTime(st.getDate("review_time"));
        }
        JDBCUtil.close(conn, ps, st);
        return paper;
    }

    @Override
    public List<Paper> getPaperByLikePaperName(String paperName) throws Exception {
        List<Paper> papers = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from paper where paper_name like binary ?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, '%'+paperName+'%');
        ResultSet st =  ps.executeQuery();
        while(st.next()) {
            papers.add(
                    new Paper(
                            st.getString("id"),
                            st.getString("paper_name"),
                            st.getDate("pub_time"),
                            st.getString("pub_journal"),
                            st.getString("journal_level"),
                            st.getString("index_situation"),
                            st.getString("teacher_id"),
                            st.getDate("review_time"),
                            st.getInt("flog"),
                            st.getString("review_comments")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return papers;
    }

    @Override
    public List<Paper> getPaperListByTeacherId(String teacherId) throws Exception {
        List<Paper> papers = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from paper where teacher_id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, teacherId);
        ResultSet st =  ps.executeQuery();
        while(st.next()) {
            papers.add(
                    new Paper(
                            st.getString("id"),
                            st.getString("paper_name"),
                            st.getDate("pub_time"),
                            st.getString("pub_journal"),
                            st.getString("journal_level"),
                            st.getString("index_situation"),
                            st.getString("teacher_id"),
                            st.getDate("review_time"),
                            st.getInt("flog"),
                            st.getString("review_comments")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return papers;
    }
}
