package com.run.arch.entity;

import java.util.Objects;

/**
 * @author 泗安
 * 管理员类 可进行教师 课程 论文 项目管理
 */
public class Admin {

    private String id;

    private String name;

    private String password;

    public Admin() {
    }

    public Admin(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) && Objects.equals(name, admin.name) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @Override
    public String toString() {
        return "ID：'" + id + '\'' +
                ", 用户名：'" + name + '\'' +
                ", 密码：'" + password + '\'';
    }
}
