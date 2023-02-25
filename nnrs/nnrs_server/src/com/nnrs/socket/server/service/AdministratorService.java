package com.nnrs.socket.server.service;

import java.util.List;

import com.nnrs.socket.entity.Administrator;
import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;

public interface AdministratorService {
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
	int insertuser(User user) throws Exception;
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
	 * 增加图书
	 * @param novel
	 * @return
	 */
	int addNovel(String novelName, int chapters) throws Exception;
	/**
	 * 删除图书
	 * @param novel
	 * @return
	 */
	int removeNovel(String novelID) throws Exception;
	/**
	 * 修改图书
	 * @param novel
	 * @return
	 */
	int modifyNovel(String novelID,String novelName,int chapterNum) throws Exception;
	
	
	/*
	 * 查看在线书籍列表
	 */
	List<Novel> onlineNovelList() throws Exception;
	
	
	/*
	 * 导入书籍后自动将书籍信息添加至数据库
	 */
	int autoAddnovels() throws Exception ;
}
