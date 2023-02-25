package com.run.arch.dao;

import com.run.arch.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourseList() throws Exception;

    int addCourse(Course course) throws Exception;

    int modifyCourse(Course course) throws Exception;

    int removeCourseById(String id) throws Exception;

    Course getCourseById(String id) throws Exception;

    List<Course> getCourseByLikeCourseName(String courseName) throws Exception;

    List<Course> getCourseListByTeacherId(String teacherId) throws Exception;
}
