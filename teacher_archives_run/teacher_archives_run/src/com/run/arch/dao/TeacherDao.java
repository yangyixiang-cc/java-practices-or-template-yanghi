package com.run.arch.dao;

import com.run.arch.entity.Teacher;

import java.util.List;

public interface TeacherDao {

    List<Teacher> getAllTeacherList() throws Exception;

    int addTeacher(Teacher teacher) throws Exception;

    int modifyTeacher(Teacher teacher) throws Exception;

    int removeTeacherById(String id) throws Exception;

    Teacher getTeacherById(String id) throws Exception;

    List<Teacher> getTeacherByLikeTeacherName(String teacherName) throws Exception;

    Teacher getTeacherByUserNameAndPassword(String username, String password) throws Exception;

    Teacher getTeacherByUserName(String username) throws Exception;

}
