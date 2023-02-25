package com.run.arch.dao;

import com.run.arch.entity.Admin;

public interface AdminDao {

    Admin getAdminByNameAndPassword(String username, String password) throws Exception;
}
