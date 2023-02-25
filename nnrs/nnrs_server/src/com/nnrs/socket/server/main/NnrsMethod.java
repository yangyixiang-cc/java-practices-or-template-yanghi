package com.nnrs.socket.server.main;

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
import com.nnrs.socket.util.Log;

public class NnrsMethod {
	AdministratorService adminService = new AdministratorServiceImpl();

	UserService userService = new UserServiceImpl();

	ShareService shareService = new ShareServiceImpl();
	static boolean flog = true;
	public Administrator adminLogin(DataPackage dp) throws Exception {
		Administrator admin = (Administrator) dp.getData();
		Administrator adminlogin = adminService.login(admin.getAdminNumber(), admin.getAdminPwd());
		try {
			Log.println("【系统日志】：" + adminlogin.getAdminName() + "管理员尝试登录");
			dp.setRdata(adminlogin);    
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】：" + adminlogin.getAdminName() + "管理员登录" + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】：" + adminlogin.getAdminName() + "管理员登录" + ActionConst.ERROR_MESSAGE);
			throw e;
		}
		return adminlogin;

	}

	public User  userLogin(DataPackage dp) throws Exception {
		User user = (User) dp.getData();
		User userLogin = userService.login(user.getUserId(), user.getUserPassword());
		try {
			Log.println("【系统日志】：" + userLogin.getUserName() + "用户尝试登录");
			dp.setRdata(userLogin);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】：" + userLogin.getUserName() + "用户登录" + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】：" + userLogin.getUserName() + "用户登录" + ActionConst.ERROR_MESSAGE);
			throw e;
		}
		return userLogin;

	}
	public void showCatalogue(DataPackage dp,User userLogin) throws Exception {
		String[] data = (String[]) dp.getData();
		try {
			List<String> list = userService.bookCatalogue(data[0]);
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求" + data[1] + "书籍目录" + ActionConst.OK_MESSAGE);

		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求" + data[1] + "书籍目录" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void novelChapterNum(DataPackage dp,User userLogin) throws Exception {
		String novelId = (String) dp.getData();
		try {
			int num = userService.novelChapterNum(novelId);
			dp.setRdata(num);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求书籍章节数" + ActionConst.OK_MESSAGE);

		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求书籍章节数" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void showLog(DataPackage dp,Administrator adminlogin) throws Exception {
		try {
			List<String> list = adminService.showLog();
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+adminlogin.getAdminName()+"管理员请求日志" + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+adminlogin.getAdminName()+"管理员请求日志" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void dowloadNovel(DataPackage dp,User userLogin) throws Exception {
		String[] data = (String[]) dp.getData();
		List[] lists = new ArrayList[2];
		try {
			lists[0] = userService.downloadNovel(data[0]);
			lists[1] = userService.bookCatalogue(data[0]);
			dp.setRdata(lists);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求下载书籍 " + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求下载书籍 " + ActionConst.ERROR_MESSAGE);
			throw e;
		}
	}

	public void readNovel(DataPackage dp,User userLogin) throws Exception {
		String[] data = (String[]) dp.getData();
		try {
			StringBuilder word = userService.readNovel(data[0], Integer.valueOf(data[1]));
			if(!userLogin.getUserId().equals("000000")) {
				int result = userService.autoHistory(data);
				if(result == DirConst.AUTO_SUCCESS) {
					Log.println("【系统日志】："+userLogin.getUserName()+"用户更新历史记录 " + ActionConst.OK_MESSAGE);
				}
			}
			dp.setRdata(word);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求在线阅读 " + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求在线阅读 " + ActionConst.ERROR_MESSAGE);
			throw e;
		}
	}

	public void showHistory(DataPackage dp,User userLogin) throws Exception {
		User user = (User) dp.getData();  
		try {
			List<History> list = userService.showHistory(user);
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求历史记录 " + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求历史记录 " + ActionConst.ERROR_MESSAGE);
			throw e;
		}
	}

	public void onlineNovelList(DataPackage dp,Administrator adminlogin) throws Exception {

		try {
			List<Novel> list = shareService.rankingList();
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+adminlogin.getAdminName()+"管理员请求在线书籍列表" + ActionConst.OK_MESSAGE);

		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+adminlogin.getAdminName()+"管理员请求在线书籍列表 " + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void rankingList(DataPackage dp,User userLogin) throws Exception {
		try {
			List<Novel> list = shareService.rankingList();
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求排行榜" + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户请求排行榜" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void selectNovel(DataPackage dp,User userLogin) throws Exception {
		String selectNovel = (String) dp.getData();
		try {
			List<Novel> list = shareService.selectNovel(selectNovel);
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户搜索书籍" + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+userLogin.getUserName()+"用户搜索书籍" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void modifyNovel(DataPackage dp,Administrator adminlogin) throws Exception {
		Novel novel = (Novel) dp.getData();
		try {
			int r = adminService.modifyNovel(novel.getNovelID(), novel.getNovelName(), novel.getChapterNum());
			if (r == DirConst.MODIFY_NOVEL_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员修改"+ novel.getNovelName() + "书籍信息" + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.ERROR);
				dp.setMessage(ActionConst.ERROR_MESSAGE);
				Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员修改"+ novel.getNovelName() + "书籍信息" + ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员修改"+ novel.getNovelName() + "书籍信息" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void removeNovel(DataPackage dp,Administrator adminlogin) throws Exception {
		String novelID = (String) dp.getData();
		try {
			int r = adminService.removeNovel(novelID);
			if (r == DirConst.DEL_NOVEL_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员删除ID号为"+ novelID +"书籍" + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.ERROR);
				dp.setMessage(ActionConst.ERROR_MESSAGE);
				Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员删除ID号为"+ novelID +"书籍" + ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员删除ID号为"+ novelID +"书籍" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void addNovel(DataPackage dp,Administrator adminlogin) throws Exception {
		String[] data = (String[]) dp.getData();
		try {
			int r = adminService.addNovel(data[0], Integer.valueOf(data[1]));
			if (r == DirConst.ADD_NOVEL_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员添加"+ data[0] + "书籍" + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员添加"+ data[0] + "书籍" + ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			Log.println("【系统日志】：" + adminlogin.getAdminName() +"管理员添加"+ data[0] + "书籍" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void modifyPwd(DataPackage dp,User userLogin) throws Exception {
		User user = (User) dp.getData();
		try {
			int r = userService.modifyPWD(user.getUserId(), user.getUserPassword());
			if (r == DirConst.MODIFY_PWD_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】：" + userLogin.getUserName() + "用户密码修改" + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.ERROR);
				dp.setMessage(ActionConst.ERROR_MESSAGE);
				Log.println("【系统日志】：" + userLogin.getUserName() + "用户密码修改" + ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】：" + userLogin.getUserName() + "用户密码修改" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void userRegister(DataPackage dp) throws Exception {
		User user = (User) dp.getData();
		try {
			int r = userService.userRegister(user);
			if (r == DirConst.REGISTER_USER_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】：" + user.getUserName() + "用户注册" + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.ERROR);
				dp.setMessage(ActionConst.ERROR_MESSAGE);
				Log.println("【系统日志】：" + user.getUserName() + "用户注册" + ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】：" + user.getUserName() + "用户注册" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void showUsers(DataPackage dp,Administrator adminlogin) throws Exception {
		try {
			List<User> list = adminService.showUsers();
			dp.setRdata(list);
			dp.setState(ActionConst.OK);
			dp.setMessage(ActionConst.OK_MESSAGE);
			System.out.println("【系统日志】："+ adminlogin.getAdminName() +"管理员显示用户列表" + ActionConst.OK_MESSAGE);
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			System.out.println("【系统日志】："+ adminlogin.getAdminName() +"管理员显示用户列表" + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}

	public void offUser(DataPackage dp,Administrator adminlogin) throws Exception {
		String userID = (String) dp.getData();
		try {
			int r = adminService.offUser(userID);
			if (r == DirConst.OFF_USER_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】："+ adminlogin.getAdminName() +"管理员注销用户" + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.ERROR);
				dp.setMessage(ActionConst.ERROR_MESSAGE);
				Log.println("【系统日志】："+ adminlogin.getAdminName() +"管理员注销用户 "+ ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+ adminlogin.getAdminName() +"管理员注销用户" + ActionConst.ERROR_MESSAGE);
			throw e;
		}
	}

	public void addUser(DataPackage dp,Administrator adminlogin) throws Exception {
		User user = (User) dp.getData();
		try {
			int r = adminService.insertuser(user);
			if (r == DirConst.ADD_USER_SUCCESS) {
				dp.setState(ActionConst.OK);
				dp.setMessage(ActionConst.OK_MESSAGE);
				Log.println("【系统日志】："+ adminlogin.getAdminName() +"管理员添加用户" + user.getUserName() + ActionConst.OK_MESSAGE);
			} else {
				dp.setState(ActionConst.ERROR);
				dp.setMessage(ActionConst.ERROR_MESSAGE);
				Log.println("【系统日志】："+ adminlogin.getAdminName() +"管理员添加用户" + user.getUserName() + ActionConst.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			dp.setState(ActionConst.ERROR);
			dp.setMessage(ActionConst.ERROR_MESSAGE);
			Log.println("【系统日志】："+ adminlogin.getAdminName() +"管理员添加用户" + user.getUserName() + ActionConst.ERROR_MESSAGE);
			throw e;
		}

	}
	public void auto() {  
		try {
			if(flog) {
				if(adminService.autoAddnovels() == DirConst.AUTO_SUCCESS) {
					System.out.println("数据库初始化成功");
					flog = false;
				}
			}
		} catch (Exception e) {
			System.out.println("数据库初始化失败");
		}
	}
}
