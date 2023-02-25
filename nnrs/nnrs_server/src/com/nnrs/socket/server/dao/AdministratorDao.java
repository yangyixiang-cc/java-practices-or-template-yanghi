package com.nnrs.socket.server.dao;

import java.util.List;

import com.nnrs.socket.entity.Administrator;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;


/**
 * 管理员数据访问接口
 * @author Administrator
 *
 */
public interface AdministratorDao {
	
	/**
	 * 管理员登录
	 * @param adminNumber
	 * @param adminPwd
	 * @return
	 * @throws Exception
	 */
	Administrator login(String adminNumber,String adminPwd)throws Exception;
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int insertUser(User user) throws Exception;
	/**
	 * 注销用户
	 * @return
	 */
	int offUser(String userID) throws Exception;
	/*
	 * 查看用户列表
	 */
	List<User> showUsers() throws Exception;
	/*
	 * 查看日志
	 */
	List<String> showLog() throws Exception;
	/**
	 * 检测管理员是否存在
	 * @return
	 */
	boolean isExistAdmin(String adminNumber) throws Exception;
	/**
	 * 检测用户是否存在
	 * @return
	 */
	boolean isExistUser(String userId) throws Exception;
}
