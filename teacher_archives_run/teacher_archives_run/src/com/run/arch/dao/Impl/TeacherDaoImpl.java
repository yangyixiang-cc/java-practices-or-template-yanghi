package com.run.arch.dao.Impl;

import com.run.arch.dao.TeacherDao;
import com.run.arch.entity.Teacher;
import com.run.arch.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {


    @Override
    public List<Teacher> getAllTeacherList() throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from teacher";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ResultSet st = ps.executeQuery();
        while(st.next()) {
            teachers.add(
                    new Teacher(
                            st.getString("id"),
                            st.getString("name"),
                            st.getString("username"),
                            st.getString("password"),
                            st.getString("gender"),
                            st.getDate("work_time"),
                            st.getString("major"),
                            st.getString("education"),
                            st.getString("title")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return teachers;
    }

    @Override
    public int addTeacher(Teacher teacher) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "insert into teacher values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, teacher.getId());
        ps.setString(2, teacher.getName());
        ps.setString(3, teacher.getUsername());
        ps.setString(4, teacher.getPassword());
        ps.setString(5, teacher.getGender());
        ps.setDate(6, new java.sql.Date(teacher.getWorkTime().getTime()));
        ps.setString(7, teacher.getMajor());
        ps.setString(8, teacher.getEducation());
        ps.setString(9, teacher.getTitle());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn,ps,null);
        return result;
    }

    @Override
    public int modifyTeacher(Teacher teacher) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "update teacher set name=?,username=?,password=?,gender=?,work_time=?,major=?,education=?,title=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, teacher.getName());
        ps.setString(2,teacher.getUsername());
        ps.setString(3, teacher.getPassword());
        ps.setString(4, teacher.getGender());
        ps.setDate(5, new java.sql.Date(teacher.getWorkTime().getTime()));
        ps.setString(6, teacher.getMajor());
        ps.setString(7, teacher.getEducation());
        ps.setString(8, teacher.getTitle());
        ps.setString(9, teacher.getId());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public int removeTeacherById(String id) throws Exception {
        int result = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from teacher where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public Teacher getTeacherById(String id) throws Exception {
        Teacher teacher = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from teacher where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            teacher = new Teacher();
            teacher.setId(st.getString("id"));
            teacher.setName(st.getString("name"));
            teacher.setUsername(st.getString("username"));
            teacher.setPassword(st.getString("password"));
            teacher.setGender(st.getString("gender"));
            teacher.setWorkTime(st.getDate("work_time"));
            teacher.setMajor(st.getString("major"));
            teacher.setEducation(st.getString("education"));
            teacher.setTitle(st.getString("title"));
        }
        JDBCUtil.close(conn, ps, st);
        return teacher;
    }

    @Override
    public List<Teacher> getTeacherByLikeTeacherName(String teacherName) throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from teacher where name like binary ?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, '%'+teacherName+'%');
        ResultSet st =  ps.executeQuery();
        while(st.next()) {
            teachers.add(
                    new Teacher(
                            st.getString("id"),
                            st.getString("name"),
                            st.getString("username"),
                            st.getString("password"),
                            st.getString("gender"),
                            st.getDate("work_time"),
                            st.getString("major"),
                            st.getString("education"),
                            st.getString("title")

                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return teachers;
    }

    @Override
    public Teacher getTeacherByUserNameAndPassword(String username, String password) throws Exception {
        Teacher teacher = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from teacher where username=? and password=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            teacher = new Teacher();
            teacher.setId(st.getString("id"));
            teacher.setName(st.getString("name"));
            teacher.setUsername(st.getString("username"));
            teacher.setPassword(st.getString("password"));
            teacher.setGender(st.getString("gender"));
            teacher.setWorkTime(st.getDate("work_time"));
            teacher.setMajor(st.getString("major"));
            teacher.setEducation(st.getString("education"));
            teacher.setTitle(st.getString("title"));
        }
        JDBCUtil.close(conn, ps, st);
        return teacher;
    }

    @Override
    public Teacher getTeacherByUserName(String username) throws Exception {
        Teacher teacher = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from teacher where username=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            teacher = new Teacher();
            teacher.setId(st.getString("id"));
            teacher.setName(st.getString("name"));
            teacher.setUsername(st.getString("username"));
            teacher.setPassword(st.getString("password"));
            teacher.setGender(st.getString("gender"));
            teacher.setWorkTime(st.getDate("work_time"));
            teacher.setMajor(st.getString("major"));
            teacher.setEducation(st.getString("education"));
            teacher.setTitle(st.getString("title"));
        }
        JDBCUtil.close(conn, ps, st);
        return teacher;
    }
}

