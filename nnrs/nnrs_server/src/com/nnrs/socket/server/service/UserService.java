package com.nnrs.socket.server.service;

import java.util.List;

import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.User;

public interface UserService {
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
	/*
	 * 查看历史记录
	 */
	List<History> showHistory(User user) throws Exception;
	
	/*
	 * 书籍在线阅读 
	 */
	StringBuilder readNovel(String novelId,int chapter) throws Exception;
	
	
	/*
	 * 下载图书
	 */
	List<StringBuilder> downloadNovel(String novelId) throws Exception;
	/*
	 * 统计书籍章节数
	 */
	int novelChapterNum(String novelId) throws Exception;
	/*
	 * 获取书籍目录
	 */
	List<String> bookCatalogue(String novelId) throws Exception;
	/*
	 * 历史记录录入
	 */
	int autoHistory(String[] data) throws Exception;
}
