package com.nnrs.socket.server.dao;

import java.util.List;

import com.nnrs.socket.entity.Novel;

public interface NovelDao {
	/**
	 * 检测图书是否存在
	 * @param novel
	 * @return
	 */
	boolean isExistNovel(String novelID) throws Exception;
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
	 * 获取书籍目录
	 */
	List<String> bookCatalogue(String novelId) throws Exception;
	/*
	 * 导入书籍后自动将书籍信息添加至数据库
	 */
	int autoAddnovels() throws Exception ;
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
	 * 书籍访问量
	 */
	int autoAddVisit(String novelId) throws Exception;
}
