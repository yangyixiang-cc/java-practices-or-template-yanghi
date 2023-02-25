package com.run.arch.service.Impl;

import com.run.arch.dao.Impl.TeacherDaoImpl;
import com.run.arch.dao.TeacherDao;
import com.run.arch.entity.Teacher;
import com.run.arch.service.TeacherService;
import com.run.arch.util.Md5Util;
import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public Teacher login(String username, String password) {
        try {
            Teacher teacherByNameAndPassword = teacherDao.getTeacherByUserNameAndPassword(username, Md5Util.encodeByMd5(password));
            if(!Objects.isNull(teacherByNameAndPassword)){
                return teacherByNameAndPassword;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher register(String username, String password) {
        Teacher teacher = new Teacher();
        try {
            teacher.setId(NumberUtil.getGeneratID().toString());
            teacher.setUsername(username);
            teacher.setPassword(Md5Util.encodeByMd5(password));
            teacher.setWorkTime(new Date());
            int i = teacherDao.addTeacher(teacher);
            if(i == 1){
                return teacher;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> searchTeacherByKeyword(String keyword) {
        try {
            Teacher teacherById = teacherDao.getTeacherById(keyword);
            List<Teacher> teacherByLikeTeacherName = teacherDao.getTeacherByLikeTeacherName(keyword);
            if(!Objects.isNull(teacherById)){
                teacherByLikeTeacherName.add(teacherById);
            }
            return teacherByLikeTeacherName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int removeTeacherById(String id) {
        int i = 0;
        try {
            i = teacherDao.removeTeacherById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        teacher.setId(NumberUtil.getGeneratID().toString());
        int i = 0;
        try {
            teacher.setPassword(Md5Util.encodeByMd5("123456"));
            i = teacherDao.addTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int modifyTeacher(Teacher teacher) {
        int i = 0;
        try {
            i = teacherDao.modifyTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Teacher>  showTeachers() {
        try {
            return teacherDao.getAllTeacherList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isExistUserName(String username) {
        try {
            Teacher teacherByUserName = teacherDao.getTeacherByUserName(username);
            if(!Objects.isNull(teacherByUserName)){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Teacher getTeacherById(String id) {
        try {
            return teacherDao.getTeacherById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
