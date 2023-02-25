package com.run.arch.service.Impl;

import com.run.arch.dao.AdminDao;
import com.run.arch.dao.Impl.AdminDaoImpl;
import com.run.arch.entity.Admin;
import com.run.arch.service.AdminService;
import com.run.arch.util.Md5Util;

import java.util.Objects;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public boolean login(String username, String password) {
        try {
            Admin adminByNameAndPassword = adminDao.getAdminByNameAndPassword(username, Md5Util.encodeByMd5(password));
            if(!Objects.isNull(adminByNameAndPassword)){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
