package com.nnrs.socket.server.main;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.nnrs.socket.entity.Administrator;
import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.server.service.AdministratorService;
import com.nnrs.socket.server.service.ShareService;
import com.nnrs.socket.server.service.UserService;
import com.nnrs.socket.server.service.impl.AdministratorServiceImpl;
import com.nnrs.socket.server.service.impl.ShareServiceImpl;
import com.nnrs.socket.server.service.impl.UserServiceImpl;
import com.nnrs.socket.util.ActionConst;
import com.nnrs.socket.util.DataPackage;
import com.nnrs.socket.util.DirConst;
import com.nnrs.socket.util.FileUtil;
import com.nnrs.socket.util.Log;


public class NnrsThread extends Thread {
	Socket socket;
	static User userLogin = null;
	static Administrator adminLogin = null;
	NnrsMethod Nm = new NnrsMethod();
	public NnrsThread(Socket socket) {
		this.socket = socket;
	}
	@Override 
	public void run() {
		if(userLogin == null) {
			userLogin = new User();
			userLogin.setUserId("000000");
			userLogin.setUserName("游客");
		}
		DataPackage dp = null;
		try {
			Nm.auto();
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			dp = (DataPackage) ois.readObject();
			InetAddress addr = socket.getInetAddress();
			Log.println("【系统日志】：正在鉴定来自"+addr.getHostName()+":"+addr.getHostAddress()+"客户端的操作...");
			switch (dp.getAction()) {
			case ActionConst.ADMIN_LOGIN_ACTION:
				// 管理员登录
				adminLogin = Nm.adminLogin(dp);
				break;
			case ActionConst.ADD_USER_ACTION:
				// 添加顾客
				Nm.addUser(dp,adminLogin);
				break;
			case ActionConst.OFF_USER_ACTION:
				// 注销用户
				Nm.offUser(dp,adminLogin);
				break;
			case ActionConst.SHOW_USERS_ACTION:
				// 显示所有用户
				Nm.showUsers(dp,adminLogin);
				break;
			case ActionConst.ONLINE_NOVEL_LIST_ACTION:
				// 在线书籍列表
				Nm.onlineNovelList(dp,adminLogin);
				break;
			case ActionConst.ADD_NOVEL_ACTION:
				// 增加书籍
				Nm.addNovel(dp,adminLogin);
				break;
			case ActionConst.REMOVE_NOVEL_ACTION:
				// 删除书籍
				Nm.removeNovel(dp,adminLogin);
				break;
			case ActionConst.MODIFY_NOVEL_ACTION:
				// 修改书籍信息
				Nm.modifyNovel(dp,adminLogin);
				break;
			case ActionConst.USER_REGISTER_ACTION:
				// 用户注册
				Nm.userRegister(dp);
				break;
			case ActionConst.USER_LOGIN_ACTION:
				// 用户登录
				userLogin = Nm.userLogin(dp);
				break;
			case ActionConst.MODIFY_PWD_ACTION:
				// 修改用户密码
				Nm.modifyPwd(dp,userLogin);
				break;	
			case ActionConst.SELECT_NOVEL_ACTION:
				// 搜索书籍
				Nm.selectNovel(dp,userLogin);
				break;
			case ActionConst.RANKING_LIST_ACTION:
				// 书籍排行榜 排行榜的前十名
				Nm.rankingList(dp,userLogin);
				break;
			case ActionConst.SHOW_HISTORY_ACTION:
				// 显示用户阅读历史
				Nm.showHistory(dp,userLogin);
				break;
			case ActionConst.READ_NOVEL_ACTION:
				// 在线阅读书籍
				Nm.readNovel(dp,userLogin);
				break;
			case ActionConst.SHOW_LOG_ACTION:
				// 显示日志
				Nm.showLog(dp,adminLogin);
				break;
			case ActionConst.SHOW_CATALOGUE_ACTION:
				//显示目录
				Nm.showCatalogue(dp,userLogin);
				break;
			case ActionConst.DOWLOAD_NOVEL_ACTION:
				// 下载书籍
				Nm.dowloadNovel(dp,userLogin);
				break;
			case ActionConst.NOVEL_CHAPTERS_NUM:
				//显示章节数
				Nm.novelChapterNum(dp,userLogin);
				break;
			default:
				// 无效动作
				break;
			}
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(dp);
			Log.println("【系统日志】：已向"+addr.getHostName()+":"+addr.getHostAddress()+"客户端返回数据...");
			oos.close();
			os.close();
			ois.close();
			is.close();
			socket.close();
			Log.println("【系统日志】：对"+addr.getHostName()+":"+addr.getHostAddress()+"客户端所有资源已关闭...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	

	
}
