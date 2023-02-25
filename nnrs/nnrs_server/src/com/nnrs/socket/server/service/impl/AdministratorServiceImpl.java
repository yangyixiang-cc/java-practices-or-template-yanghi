package com.nnrs.socket.server.service.impl;

import java.util.List;

import com.nnrs.socket.entity.Administrator;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.server.dao.AdministratorDao;
import com.nnrs.socket.server.dao.NovelDao;
import com.nnrs.socket.server.dao.impl.AdministratorDaoImpl;
import com.nnrs.socket.server.dao.impl.NovelDaoImpl;
import com.nnrs.socket.server.service.AdministratorService;
import com.nnrs.socket.util.Md5Util;

public class AdministratorServiceImpl implements AdministratorService {
	AdministratorDao adminDao =new AdministratorDaoImpl();
	NovelDao novelDao = new NovelDaoImpl();
	@Override
	public Administrator login(String adminNumber, String adminPwd) throws Exception {
		return adminDao.login(adminNumber, adminPwd);
	}

	@Override  
	public int insertuser(User user) throws Exception {		
		return adminDao.insertUser(user);
	}

	@Override
	public int offUser(String userID) throws Exception {
		return adminDao.offUser(userID);
	}

	@Override
	public List<User> showUsers() throws Exception {
		return adminDao.showUsers();
	}

	@Override
	public List<String> showLog() throws Exception {
		return adminDao.showLog();
	}

	@Override
	public int addNovel(String novelName, int chapters) throws Exception {
		return novelDao.addNovel(novelName, chapters);
	}

	@Override
	public int removeNovel(String novelID) throws Exception {
		return novelDao.removeNovel(novelID);
	}

	@Override
	public int modifyNovel(String novelID, String novelName, int chapterNum) throws Exception {
		return novelDao.modifyNovel(novelID, novelName, chapterNum);
	}

	@Override
	public List<Novel> onlineNovelList() throws Exception {
		return novelDao.onlineNovelList();
	}

	@Override
	public int autoAddnovels() throws Exception {
		return novelDao.autoAddnovels();
	}

}
