package com.nnrs.socket.server.service.impl;

import java.util.List;

import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.server.dao.ShareDao;
import com.nnrs.socket.server.dao.impl.ShareDaoImpl;
import com.nnrs.socket.server.service.ShareService;

public class ShareServiceImpl implements ShareService {
	ShareDao shareDao = new ShareDaoImpl();
	@Override
	public List<Novel> selectNovel(String novel) throws Exception {
		return shareDao.selectNovel(novel);
	}

	@Override
	public List<Novel> rankingList() throws Exception {
		return shareDao.rankingList();
	}

}
