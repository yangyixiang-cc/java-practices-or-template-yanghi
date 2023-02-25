package com.run.arch.dao.Impl;

import com.run.arch.dao.AdminDao;
import com.run.arch.entity.Admin;
import com.run.arch.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin getAdminByNameAndPassword(String username, String password) throws Exception {
        Admin admin = null;
        Connection conn =  JDBCUtil.getConnection();
        String sql = "select * from admin where name=? and password=?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet st =  ps.executeQuery();
        if(st.next()) {
            admin = new Admin();
            admin.setId(st.getString("id"));
            admin.setName(st.getString("name"));
            admin.setPassword(st.getString("password"));
        }
        JDBCUtil.close(conn, ps, st);
        return admin;
    }


}
