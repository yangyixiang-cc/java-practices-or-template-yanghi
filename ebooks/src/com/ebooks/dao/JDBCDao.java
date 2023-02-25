package com.ebooks.dao;

import java.util.List;

import com.ebooks.entity.Book;

public interface JDBCDao {
	public List<Book> showBooks() throws Exception;
}
