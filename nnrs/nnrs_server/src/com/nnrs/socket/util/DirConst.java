package com.nnrs.socket.util;


public class DirConst {
	public static final int ADD_USER_SUCCESS = 1;
	public static final int ADD_USER_FAIL = 2;
	public static final int OFF_USER_SUCCESS = 3;
	public static final int OFF_USER_FAIL = 4;
	public static final int REGISTER_USER_SUCCESS = 5;
	public static final int REGISTER_USER_FAIL = 6;
	public static final int MODIFY_PWD_SUCCESS = 7;
	public static final int MODIFY_PWD_FAIL = 8;
	public static final int ADD_NOVEL_SUCCESS = 9;
	public static final int ADD_NOVEL_FAIL = 10;
	public static final int DEL_NOVEL_SUCCESS = 11;
	public static final int DEL_NOVEL_FAIL = 12;
	public static final int MODIFY_NOVEL_SUCCESS = 13;
	public static final int MODIFY_NOVEL_FAIL = 14;
	public static final int COMPRESS_NOVEL_SUCCESS = 15;
	public static final int COMPRESS_NOVEL_FAIL = 16;
	public static final int DECOMPRESS_NOVEL_SUCCESS = 17;
	public static final int DECOMPRESS_NOVEL_FAIL = 18;
	public static final int AUTO_SUCCESS = 19;
	public static final int AUTO_FAIL = 20;
	public static String getCode()
	{
		int d=(int)(Math.random()*8999)+1000;
		return String.valueOf(d);
	}
}
