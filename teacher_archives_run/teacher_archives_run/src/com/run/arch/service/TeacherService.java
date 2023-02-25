package com.run.arch.service;

import com.run.arch.entity.Teacher;

import java.util.List;


public interface TeacherService {

    Teacher login(String username, String password);

    Teacher register(String username, String password);

    List<Teacher> searchTeacherByKeyword(String keyword);

    int removeTeacherById(String id);

    int addTeacher(Teacher teacher);

    int modifyTeacher(Teacher teacher);

    List<Teacher> showTeachers();

    boolean isExistUserName(String username);

    Teacher getTeacherById(String id);
}
