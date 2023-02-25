package com.nnrs.socket.util;

public interface ActionConst {

	int USER_LOGIN_ACTION = 21;
	int ADMIN_LOGIN_ACTION = 22;
	int ADD_USER_ACTION = 23;
	int OFF_USER_ACTION = 24;
	int SHOW_USERS_ACTION = 25;
	int USER_REGISTER_ACTION = 26;
	int MODIFY_PWD_ACTION = 27;
	int ADD_NOVEL_ACTION = 28;
	int REMOVE_NOVEL_ACTION = 29;
	int MODIFY_NOVEL_ACTION = 30;
	int SELECT_NOVEL_ACTION = 31;
	int RANKING_LIST_ACTION = 32;
	int ONLINE_NOVEL_LIST_ACTION = 33;
	int SHOW_HISTORY_ACTION = 34;
	int READ_NOVEL_ACTION = 35;
	int SHOW_LOCALNOVELIST_ACTION = 36;
	int DOWLOAD_NOVEL_ACTION = 37;
	int SHOW_LOG_ACTION = 38;
	int NOVEL_CHAPTERS_NUM = 39;
	int SHOW_CATALOGUE_ACTION = 40;
	/**
	 * 返回状态(成功)
	 */
	int OK=200;
	/**
	 * 返回状态(失败)
	 */
	int ERROR=500;	
	
	/**
	 * 返回消息(成功)
	 */
	String OK_MESSAGE="操作成功";
	
	/**
	 * 返回消息(失败)
	 */
	String ERROR_MESSAGE="操作失败";
}
