package com.run.arch.service.Impl;

import com.run.arch.dao.CourseDao;
import com.run.arch.dao.Impl.CourseDaoImpl;
import com.run.arch.entity.Course;
import com.run.arch.service.CourseService;
import com.run.arch.util.NumberUtil;

import java.util.List;
import java.util.Objects;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> searchCourseByCourseKeyword(String keyword) {
        try {
            Course courseById = courseDao.getCourseById(keyword);
            List<Course> courseByLikeCourseName = courseDao.getCourseByLikeCourseName(keyword);
            if(!Objects.isNull(courseById)){
                courseByLikeCourseName.add(courseById);
            }
            return courseByLikeCourseName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int removeCourseById(String courseId) {
        int i = 0;
        try {
            i = courseDao.removeCourseById(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int addCourse(Course course) {
        course.setId(NumberUtil.getGeneratID().toString());
        int i = 0;
        try {
            i = courseDao.addCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int modifyCourse(Course course) {
        int i = 0;
        try {
            i = courseDao.modifyCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Course> showAllCourses() {
        try {
            return courseDao.getAllCourseList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Course> showAllCoursesByTeacherId(String teacherId) {
        try {
            return courseDao.getCourseListByTeacherId(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getCourseById(String id) {
        try {
            return courseDao.getCourseById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
