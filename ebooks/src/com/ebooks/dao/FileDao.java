package com.ebooks.dao;

import java.util.List;

public interface FileDao {
	/*
	 * 显示书籍目录
	 */
	public List<String> showML(String bookName) throws Exception;
	/*
	 * 阅读书籍
	 */
	public StringBuilder readBooks(String bookName,int chapter) throws Exception;
	
}
