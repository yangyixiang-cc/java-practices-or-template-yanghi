package com.run.arch.entity;

import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author 泗安
 * 课程类
 */
public class Course {

    private String id;

    private String courseName;

    private Integer classHours;

    private Date createTime;

    private Integer peopleNum;

    private String teacherId;

    public Course() {
    }

    public Course(String id, String courseName, Integer classHours, Date createTime, Integer peopleNum, String teacherId) {
        this.id = id;
        this.courseName = courseName;
        this.classHours = classHours;
        this.createTime = createTime;
        this.peopleNum = peopleNum;
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getClassHours() {
        return classHours;
    }

    public void setClassHours(Integer classHours) {
        this.classHours = classHours;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(courseName, course.courseName) && Objects.equals(classHours, course.classHours) && Objects.equals(createTime, course.createTime) && Objects.equals(peopleNum, course.peopleNum) && Objects.equals(teacherId, course.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, classHours, createTime, peopleNum, teacherId);
    }

    @Override
    public String toString() {
        return "ID：'" + id + '\'' +
                ", 课程：'" + courseName + '\'' +
                ", 学时：" + classHours +
                ", 创建时间：" + NumberUtil.dateToStr(createTime) +
                ", 课程人数：" + peopleNum +
                ", 教师ID：'" + teacherId + '\'';
    }
}
