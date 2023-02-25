package com.nnrs.socket.server.dao;

import java.util.List;

import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.User;

public interface UserDao {
	/*
	 * 用户登录
	 */
	User login(String userId,String userpassword) throws Exception;
	/*
	 * 用户注册
	 */
	int userRegister(User user)throws Exception;
	/*
	 * 修改密码
	 */
	int modifyPWD(String userID,String newPassword) throws Exception;
	
	/**
	 * 检测用户是否存在
	 * @param novel
	 * @return
	 */
	boolean isExistUser(String userId) throws Exception;
	/*
	 * 查看历史记录
	 */
	List<History> showHistory(User user) throws Exception;
	/*
	 * 历史记录录入
	 */
	int autoHistory(String[] data) throws Exception;
}
