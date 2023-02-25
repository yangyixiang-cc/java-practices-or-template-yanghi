package com.nnrs.socker.client;

import java.util.List;

public interface LocalUser {
	/*
	 * 阅读书籍
	 */
	StringBuilder readNovel(String fileName,int chapter) throws Exception;
	/*
	 * 查看本地书籍列表
	 */
	List<String> showNovelList() throws Exception;
	/*
	 *  本地书籍历史
	 */
	List<String> showHistory() throws Exception;

	/*
	 * 统计书籍章节数
	 */
	int novelChapterNum(String fileName) throws Exception;
	/*
	 * 查看书籍目录
	 */
	List<String> showCatalogue(String fileName) throws Exception;
}
