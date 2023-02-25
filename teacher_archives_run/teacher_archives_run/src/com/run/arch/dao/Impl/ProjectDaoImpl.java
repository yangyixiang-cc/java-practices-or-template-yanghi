package com.run.arch.dao.Impl;

import com.run.arch.dao.ProjectDao;
import com.run.arch.entity.Project;
import com.run.arch.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    @Override
    public List<Project> getAllProjectList() throws Exception {
        List<Project> projects = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from project";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ResultSet st = ps.executeQuery();
        while(st.next()) {
            projects.add(
                   new Project(
                           st.getString("id"),
                           st.getString("project_name"),
                           st.getString("category"),
                           st.getString("awards"),
                           st.getDate("create_time"),
                           st.getDate("end_time"),
                           st.getDate("review_time"),
                           st.getInt("flog"),
                           st.getString("review_comments"),
                           st.getString("teacher_id")
                           ));
        }
        JDBCUtil.close(conn, ps, st);
        return projects;
    }

    @Override
    public int addProject(Project project) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "insert into project values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, project.getId());
        ps.setString(2, project.getProjectName());
        ps.setString(3, project.getCategory());
        ps.setString(4, project.getAwards());
        ps.setDate(5, new java.sql.Date(project.getCreateTime().getTime()));
        ps.setDate(6, new java.sql.Date(project.getEndTime().getTime()));
        ps.setDate(7, new java.sql.Date(project.getReviewTime().getTime()));
        ps.setInt(8, project.getFlog());
        ps.setString(9, project.getReviewComments());
        ps.setString(10, project.getTeacherId());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn,ps,null);
        return result;
    }

    @Override
    public int modifyProject(Project project) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "update project set project_name=?,category=?,awards=?,create_time=?,end_time=?,review_time=?,flog=?,review_comments=?,teacher_id=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, project.getProjectName());
        ps.setString(2, project.getCategory());
        ps.setString(3, project.getAwards());
        ps.setDate(4, new java.sql.Date(project.getCreateTime().getTime()));
        ps.setDate(5, new java.sql.Date(project.getEndTime().getTime()));
        ps.setDate(6, new java.sql.Date(project.getReviewTime().getTime()));
        ps.setInt(7, project.getFlog());
        ps.setString(8, project.getReviewComments());
        ps.setString(9, project.getTeacherId());
        ps.setString(10, project.getId());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public int removeProjectById(String id) throws Exception {
        int result = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from project where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public Project getProjectById(String id) throws Exception {
        Project project = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from project where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            project = new Project();
            project.setId(st.getString("id"));
            project.setProjectName(st.getString("project_name"));
            project.setCategory(st.getString("category"));
            project.setAwards(st.getString("awards"));
            project.setCreateTime(st.getDate("create_time"));
            project.setEndTime(st.getDate("end_time"));
            project.setReviewTime(st.getDate("review_time"));
            project.setFlog(st.getInt("flog"));
            project.setReviewComments(st.getString("review_comments"));
            project.setTeacherId(st.getString("teacher_id"));
        }
        JDBCUtil.close(conn, ps, st);
        return project;
    }

    @Override
    public List<Project> getProjectByLikeProjectName(String projectName) throws Exception {
        List<Project> projects = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from project where project_name like binary ?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, '%'+projectName+'%');
        ResultSet st =  ps.executeQuery();
        while(st.next()) {
            projects.add(
                    new Project(
                            st.getString("id"),
                            st.getString("project_name"),
                            st.getString("category"),
                            st.getString("awards"),
                            st.getDate("create_time"),
                            st.getDate("end_time"),
                            st.getDate("review_time"),
                            st.getInt("flog"),
                            st.getString("review_comments"),
                            st.getString("teacher_id")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return projects;
    }

    @Override
    public List<Project> getProjectListByTeacherId(String teacherId) throws Exception {
        List<Project> projects = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from project where teacher_id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, teacherId);
        ResultSet st =  ps.executeQuery();
        while(st.next()) {
            projects.add(
                    new Project(
                            st.getString("id"),
                            st.getString("project_name"),
                            st.getString("category"),
                            st.getString("awards"),
                            st.getDate("create_time"),
                            st.getDate("end_time"),
                            st.getDate("review_time"),
                            st.getInt("flog"),
                            st.getString("review_comments"),
                            st.getString("teacher_id")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return projects;
    }
}
