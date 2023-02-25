package com.nnrs.socket.server.service.impl;

import java.util.List;

import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.server.dao.NovelDao;
import com.nnrs.socket.server.dao.UserDao;
import com.nnrs.socket.server.dao.impl.NovelDaoImpl;
import com.nnrs.socket.server.dao.impl.UserDaoImpl;
import com.nnrs.socket.server.service.UserService;
import com.nnrs.socket.util.Md5Util;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();
	NovelDao novelDao = new NovelDaoImpl();
	@Override
	public User login(String userId, String userpassword) throws Exception {
		return userDao.login(userId, userpassword);
	}

	@Override
	public int userRegister(User user) throws Exception {
		return userDao.userRegister(user);
	}

	@Override
	public int modifyPWD(String userID, String newPassword) throws Exception {
		return userDao.modifyPWD(userID, Md5Util.encodeByMd5(newPassword));
	}

	@Override
	public List<History> showHistory(User user) throws Exception {
		return userDao.showHistory(user);
	}

	@Override
	public StringBuilder readNovel(String novelId, int chapter) throws Exception {
		return novelDao.readNovel(novelId, chapter);
	}

	@Override
	public List<StringBuilder> downloadNovel(String novelId) throws Exception {
		
		return novelDao.downloadNovel(novelId);
	}

	@Override
	public int novelChapterNum(String novelId) throws Exception {
		
		return novelDao.novelChapterNum(novelId);
	}

	@Override
	public List<String> bookCatalogue(String novelId) throws Exception {
		
		return novelDao.bookCatalogue(novelId);
	}

	@Override
	public int autoHistory(String[] data) throws Exception {
		return userDao.autoHistory(data);
	}


}
