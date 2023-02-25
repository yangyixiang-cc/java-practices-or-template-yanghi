package com.run.arch.service;

import com.run.arch.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> searchCourseByCourseKeyword(String keyword);

    int removeCourseById(String courseId);

    int addCourse(Course course);

    int modifyCourse(Course course);

    List<Course> showAllCourses();

    List<Course> showAllCoursesByTeacherId(String teacherId);

    Course getCourseById(String id);
}
