package com.run.arch.dao.Impl;

import com.run.arch.dao.CourseDao;
import com.run.arch.entity.Course;
import com.run.arch.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> getAllCourseList() throws Exception {
        List<Course> courses = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from course";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ResultSet st = ps.executeQuery();
        while(st.next()) {
           courses.add(
                   new Course(
                           st.getString("id"),
                           st.getString("course_name"),
                           st.getInt("class_hours"),
                           st.getDate("create_time"),
                           st.getInt("people_num"),
                           st.getString("teacher_id")
                   ));
        }
        JDBCUtil.close(conn, ps, st);
        return courses;
    }

    @Override
    public int addCourse(Course course) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "insert into course values(?,?,?,?,?,?)";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, course.getId());
        ps.setString(2, course.getCourseName());
        ps.setInt(3, course.getClassHours());
        ps.setDate(4, new java.sql.Date(course.getCreateTime().getTime()));
        ps.setInt(5, course.getPeopleNum());
        ps.setString(6, course.getTeacherId());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn,ps,null);
        return result;
    }

    @Override
    public int modifyCourse(Course course) throws Exception {
        int result = 0;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "update course set course_name=?,class_hours=?,create_time=?,people_num=?,teacher_id=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, course.getCourseName());
        ps.setInt(2, course.getClassHours());
        ps.setDate(3, new java.sql.Date(course.getCreateTime().getTime()));
        ps.setInt(4, course.getPeopleNum());
        ps.setString(5, course.getTeacherId());
        ps.setString(6, course.getId());
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public int removeCourseById(String id) throws Exception {
        int result = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from course where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        result =  ps.executeUpdate();
        JDBCUtil.close(conn, ps, null);
        return result;
    }

    @Override
    public Course getCourseById(String id) throws Exception {
        Course course = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from course where id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            course = new Course();
            course.setId(st.getString("id"));
            course.setCourseName(st.getString("course_name"));
            course.setClassHours(st.getInt("class_hours"));
            course.setCreateTime(st.getDate("create_time"));
            course.setPeopleNum(st.getInt("people_num"));
            course.setTeacherId(st.getString("teacher_id"));
        }
        JDBCUtil.close(conn, ps, st);
        return course;
    }

    @Override
    public List<Course> getCourseByLikeCourseName(String courseName) throws Exception {
        List<Course> courses = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from course where course_name like binary ?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, '%'+courseName+'%');
        ResultSet st =  ps.executeQuery();
        while(st.next()) {
            courses.add(
                    new Course(
                            st.getString("id"),
                            st.getString("course_name"),
                            st.getInt("class_hours"),
                            st.getDate("create_time"),
                            st.getInt("people_num"),
                            st.getString("teacher_id")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return courses;
    }

    @Override
    public List<Course> getCourseListByTeacherId(String teacherId) throws Exception {
        List<Course> courses = new ArrayList<>();
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from course where teacher_id=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1,teacherId);
        ResultSet st = ps.executeQuery();
        while(st.next()) {
            courses.add(
                    new Course(
                            st.getString("id"),
                            st.getString("course_name"),
                            st.getInt("class_hours"),
                            st.getDate("create_time"),
                            st.getInt("people_num"),
                            st.getString("teacher_id")
                    ));
        }
        JDBCUtil.close(conn, ps, st);
        return courses;
    }
}
