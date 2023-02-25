package com.run.arch.entity;

import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author 泗安
 * 教师类
 */
public class Teacher {

    private String id;

    private String name;

    private String username;

    private String password;

    private String gender;

    private Date workTime;

    private String major;

    private String education;

    private String title;

    public Teacher() {
    }

    public Teacher(String id, String name, String username, String password, String gender, Date workTime, String major, String education, String title) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.workTime = workTime;
        this.major = major;
        this.education = education;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name) && Objects.equals(username, teacher.username) && Objects.equals(password, teacher.password) && Objects.equals(gender, teacher.gender) && Objects.equals(workTime, teacher.workTime) && Objects.equals(major, teacher.major) && Objects.equals(education, teacher.education) && Objects.equals(title, teacher.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, gender, workTime, major, education, title);
    }

    @Override
    public String toString() {
        return "ID：'" + id + '\'' +
                ", 登录名称：'" + username + '\'' +
                ", 登陆密码（MD5加密）：'" + password + '\'' +
                ", 教师姓名：'" + name + '\'' +
                ", 性别：'" + gender + '\'' +
                ", 参见工作时间：" + NumberUtil.dateToStr(workTime) +
                ", 从事专业：'" + major + '\'' +
                ", 学历：'" + education + '\'' +
                ", 职称：'" + title + '\'';
    }
}
