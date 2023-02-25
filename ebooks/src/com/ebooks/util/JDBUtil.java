package com.ebooks.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBUtil {
	
	final static String DRIVER="com.mysql.jdbc.Driver";
	final static String URL="jdbc:mysql://localhost:3306/ebooks?useUnicode=true&characterEncoding=utf-8";
	//mysql8.0
//	final static String DRIVER="com.mysql.cj.jdbc.Driver";
//	final static String URL="jdbc:mysql://localhost:3306/wsrd?useSSL=false&serverTimezone=UTC";
	


	final static String USER="root";
	
	final static String PASSWORD="root";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JDBUtil() {}
	/**
	 * 打开数据库连�?
	 * @return
	 * @throws RuntimeException
	 */
	public static Connection getConnection()  {
		Connection conn = null;
        try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			System.out.println("数据库连接出�?."+e.getMessage());
		}
        return conn;
    }
	/**
	 * 关闭数据库连�?
	 * @param rs
	 * @param ps
	 * @param conn
	 * @throws RuntimeException
	 */
	public static void closeConnection(ResultSet rs,PreparedStatement ps,Connection conn) 
	{
		try {			
			if(rs!=null)
			{
				rs.close();
			}
			if(ps!=null)
			{
				ps.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		}catch(Exception e)
		{
			System.out.println("关闭数据库连接出�?."+e.getMessage());
		}
	}
	
	
	
	
	
}
