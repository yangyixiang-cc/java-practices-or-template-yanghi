package com.nnrs.socket.client.ui;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.bethecoder.ascii_table.ASCIITable;
import com.nnrs.socket.entity.Administrator;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.util.ActionConst;
import com.nnrs.socket.util.DataPackage;
import com.nnrs.socket.util.DirConst;
import com.nnrs.socket.util.Md5Util;

public class AdministratorUI {
	 
	static Administrator admin = null;
	
	public DataPackage doAction(DataPackage dp)
	{
		Socket socket;
		try {
			socket = new Socket("localhost",6699);
			OutputStream os=socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(os);
			oos.writeObject(dp);
			socket.shutdownOutput();
			InputStream is=socket.getInputStream();
			ObjectInputStream ois=new ObjectInputStream(is);
			dp=(DataPackage)ois.readObject();
			ois.close();
			is.close();
			oos.close();
			os.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("—————————————————————————————————————————————————————————————————");
			System.out.println("| 警告，出现服务器连接错误或网络连接失败，请重新检查网络连接或关闭系统 |");
			System.out.println("—————————————————————————————————————————————————————————————————");
		}
		return dp;
	}

	public void login() {
		System.out.println("————————————————————————————————————————————");
		System.out.println("|                 管理员登录                |");
		System.out.println("————————————————————————————————————————————");
		Scanner input = new Scanner(System.in);
		boolean isSuccess = false;
		for (int i = 3; i >= 1; i--) {
			System.out.print("请输入管理员ID: ");
			String adminNumber = input.next();
			System.out.print("请输入管理员密码: ");
			String adminPwd = input.next();
			try {
				Administrator adminObject = new Administrator();
				adminObject.setAdminNumber(adminNumber);
				adminObject.setAdminPwd(Md5Util.encodeByMd5(adminPwd));
				DataPackage dp = new DataPackage();
				dp.setCode(DirConst.getCode());
				dp.setAction(ActionConst.ADMIN_LOGIN_ACTION);
				dp.setData(adminObject);	
				dp=doAction(dp);
				admin = (Administrator)dp.getRdata();
				if (admin != null) {
					isSuccess = true;
					break;
				} else {
					if (i == 1) {
						System.out.println("您已错误三次，系统自动退出");
						System.exit(0);
					} else {
						System.out.println("账号或密码错误，您还有 " + (i - 1) + "次机会");
					}
				}
			} catch (Exception e) {
				System.out.println("——————————————————————————————————");
				System.out.println("|   登录错误，程序出现异常         |");
				System.out.println("——————————————————————————————————");
			}
		}
		if (isSuccess) {
			System.out.println("——————————————————————————————————————————————");
			System.out.println("|登录成功，欢迎你"+admin.getAdminName()+"管理员|");
			System.out.println("——————————————————————————————————————————————");
			menu();
		}
	}
	
	public void menu()
	{
		System.out.println("————————————————————————————————————————————————————————");
		System.out.println("| 网络阅读小说系统["+admin.getAdminName()+"管理员]       |");
		System.out.println("————————————————————————————————————————————————————————");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.用户管理       2.书籍管理       3.查看日志         4.退出                              |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请选择：");
		String op = input.nextLine();
		switch (op) {
			case "1":
				userManagement();
				break;
			case "2":
				novelManagement();
				break;
			case "3":
				showLog();
				break;
			case "4":
				System.out.println("欢迎下次使用！");
				System.exit(0);
				break;
			default:
			System.out.println("———————————————————————————————");
			System.out.println("|     请输入正确的序号         |");
			System.out.println("———————————————————————————————");
		}
	}

	private void novelManagement() {
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.添加书籍  2.搜索书籍   3.修改书籍信息   4.删除书籍   5.在线书籍列表   6.书籍排行榜   7.返回|");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请选择：");
		String op = input.nextLine();
		switch (op) {
			case "1":
				addNovel();
				break;
			case "2":
				selectNovel();
				break;
			case "3":
				ModifyNovel();
				break;
			case "4":
				removeNovel();
				break;
			case "5":
				onlineNovelList();
				break;
			case "6":
				rankingList();
				break;
			case "7":
				menu();
				break;
			default:
				System.out.println("———————————————————————————————");
				System.out.println("|     请输入正确的序号         |");
				System.out.println("———————————————————————————————");
		}
		novelManagement();
	}
	private void userManagement() {
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.添加用户          2.注销用户              3.查看用户列表              4.返回            |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请选择：");
		String op = input.nextLine();
		switch (op) {
			case "1":
				addUser();
				break;
			case "2":
				offUser();
				break;
			case "3":
				showUserList();
				break;
			case "4":
				menu();
				break;
			default:
				System.out.println("———————————————————————————————");
				System.out.println("|     请输入正确的序号         |");
				System.out.println("———————————————————————————————");

		}
		userManagement();
	}
	private void showLog() {
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.SHOW_LOG_ACTION);
			dp=doAction(dp);
			List<String> list = (List<String>) dp.getRdata();
			if(list.isEmpty()) {
				System.out.println("———————————————————————————————");
				System.out.println("|     暂时还没有日志记录       |");
				System.out.println("———————————————————————————————");
			}else {
				System.out.println("———————————————————日志———————————————————");
				for(String s : list) {
					System.out.println("|"+s+"|");
				}
				System.out.println("——————————————————————————————————————————");
			}
		} catch (Exception e) {
			System.out.println("——————————————————————————————————————");
			System.out.println("|   日志调用失败，程序出现异常         |");
			System.out.println("——————————————————————————————————————");
		}
		menu();
	}
	private void rankingList() {
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.RANKING_LIST_ACTION);
			dp=doAction(dp);
			List<Novel> list = (List<Novel>) dp.getRdata();
			if(list.isEmpty()) {
				System.out.println("———————————————————————————————————— ");
				System.out.println("|   没有书籍，无法生成排行榜          |");
				System.out.println("—————————————————————————————————————");
			}else {
				String[] header = {"ID","名称","章节数","访问量"};
				String[][] data = new String[list.size()][4];
				int j = 0;
				for(Novel novel:list) {
					data[j][0] = novel.getNovelID();
					data[j][1] = novel.getNovelName();
					data[j][2] = String.valueOf(novel.getChapterNum());
					data[j][3] = String.valueOf(novel.getVisitNum());
					j++;
				}
				ASCIITable.getInstance().printTable(header, data);
			}
		} catch (Exception e) {
			System.out.println("——————————————————————————————————————");
			System.out.println("|   排行榜调用失败，程序出现异常        |");
			System.out.println("——————————————————————————————————————");
		}
	}

	private void onlineNovelList() {
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.ONLINE_NOVEL_LIST_ACTION);
			dp=doAction(dp);
			List<Novel> list = (List<Novel>) dp.getRdata();
			if(list.isEmpty()) {
				System.out.println("——————————————————————————");
				System.out.println("|      暂时没有书籍       |");
				System.out.println("——————————————————————————");
			}else {
				String[] header = {"ID","名称","章节数","访问量"};
				String[][] data = new String[list.size()][4];
				int j = 0;
				for(Novel novel:list) {
					data[j][0] = novel.getNovelID();
					data[j][1] = novel.getNovelName();
					data[j][2] = String.valueOf(novel.getChapterNum());
					data[j][3] = String.valueOf(novel.getVisitNum());
					j++;
				}
				ASCIITable.getInstance().printTable(header, data);
			}
			
		} catch (Exception e) {
			System.out.println("——————————————————————————————————————");
			System.out.println("|   书籍列表调用失败，程序出现异常      |");
			System.out.println("———————————————————————————————————————");
		}
	}

	private void removeNovel() {
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你要删除的书籍ID：");
		String novelID = input.next();
		try{
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.REMOVE_NOVEL_ACTION);
			dp.setData(novelID);
			dp=doAction(dp);
			if(dp.getState()==ActionConst.OK)
			{
				System.out.println("————————————————————————");
				System.out.println("|   书籍删除成功        |");
				System.out.println("————————————————————————");
			}else
			{
				System.out.println("————————————————————————");
				System.out.println("|   书籍删除失败        |");
				System.out.println("————————————————————————");
			}
		}catch (Exception e){
			System.out.println("——————————————————————————————————");
			System.out.println("|   书籍删除失败，程序出现异常     |");
			System.out.println("——————————————————————————————————");
		}
	}

	private void ModifyNovel() {
		Novel novel =new Novel();
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你要修改的书籍ID：");
		novel.setNovelID(input.next());
		System.out.print("请输入修改后的书籍名称：");
		novel.setNovelName(input.next());
		System.out.print("请输入修改后的书籍总章节数：");
		novel.setChapterNum(input.nextInt());
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.MODIFY_NOVEL_ACTION);
			dp.setData(novel);
			dp=doAction(dp);
			if(dp.getState()==ActionConst.OK)
			{
				System.out.println("————————————————————————");
				System.out.println("|   书籍修改成功        |");
				System.out.println("————————————————————————");
			}else
			{
				System.out.println("————————————————————————");
				System.out.println("|   书籍修改失败        |");
				System.out.println("————————————————————————");
			}

		} catch (Exception e) {
			System.out.println("——————————————————————————————————");
			System.out.println("|   书籍修改失败，程序出现异常     |");
			System.out.println("——————————————————————————————————");
		}
	}

	private void selectNovel() {
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你要搜索的书籍名称或ID：");
		String novelNI = scan.next();
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setData(novelNI);
			dp.setAction(ActionConst.SELECT_NOVEL_ACTION);
			dp=doAction(dp);
			List<Novel> list = (List<Novel>) dp.getRdata();
			if(list.isEmpty()) {
				System.out.println("——————————————————————————");
				System.out.println("|    未搜索到相关书籍      |");
				System.out.println("——————————————————————————");
			}else {
				String[] header = {"ID","名称","章节数","访问量"};
				String[][] data = new String[list.size()][4];
				int j = 0;
				for(Novel novel:list) {
					data[j][0] = novel.getNovelID();
					data[j][1] = novel.getNovelName();
					data[j][2] = String.valueOf(novel.getChapterNum());
					data[j][3] = String.valueOf(novel.getVisitNum());
					j++;
				}
				ASCIITable.getInstance().printTable(header, data);
			}
			
		} catch (Exception e) {
			System.out.println("——————————————————————————————————————");
			System.out.println("|   在线搜索调用失败，程序出现异常      |");
			System.out.println("———————————————————————————————————————");
		}

	}

	private void addNovel() {
		String[] data = new String[2];
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入书籍名称：");
		data[0] = input.next();
		System.out.print("请输入书籍总章节数：");
		data[1] = input.next();
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.ADD_NOVEL_ACTION);
			dp.setData(data);
			dp=doAction(dp);
			if(dp.getState()==ActionConst.OK)
			{
				System.out.println("————————————————————————");
				System.out.println("|   书籍添加成功        |");
				System.out.println("————————————————————————");
			}else
			{
				System.out.println("————————————————————————");
				System.out.println("|   书籍添加失败        |");
				System.out.println("————————————————————————");
			}

		} catch (Exception e) {
			System.out.println("—————————————————————————————————————");
			System.out.println("|   书籍添加失败，程序出现异常        |");
			System.out.println("—————————————————————————————————————");
		}
	}



	private void showUserList() {
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.SHOW_USERS_ACTION);
			dp=doAction(dp);
			List<User> list = (List<User>) dp.getRdata();
			if(list.isEmpty()) {
				System.out.println("————————————————————————");
				System.out.println("|   暂时还没有用户      |");
				System.out.println("————————————————————————");
			}else {
				String[] header = {"用户ID","名称","密码","创建时间","用户状态"};
				String[][] data = new String[list.size()][5];
				int j = 0;
				for(User user : list) {
					data[j][0] = user.getUserId();
					data[j][1] = user.getUserName();
					data[j][2] = user.getUserPassword();
					data[j][3] = String.valueOf(user.getDate());
					data[j][4] = user.isStatus()? "正常":"注销";
					j++;
				}
				ASCIITable.getInstance().printTable(header, data);

			}
			
		} catch (Exception e) {
			System.out.println("————————————————————————————————————————");
			System.out.println("|   用户列表调用失败，程序出现异常        |");
			System.out.println("————————————————————————————————————————");
		}
	}

	private void offUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入注销用户的ID：");
		String userID = input.next();
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.OFF_USER_ACTION);
			dp.setData(userID);
			dp=doAction(dp);
			if(dp.getState()==ActionConst.OK)
			{
				System.out.println("————————————————————————");
				System.out.println("|   用户注销成功        |");
				System.out.println("————————————————————————");
			}else
			{
				System.out.println("————————————————————————");
				System.out.println("|   用户注销失败        |");
				System.out.println("————————————————————————");
			}

		} catch (Exception e) {
			System.out.println("————————————————————————————————————");
			System.out.println("|   用户注销失败，程序出现异常       |");
			System.out.println("————————————————————————————————————");
		}

	}

	private void addUser() {
		User user = new User();
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入用户ID：");
		user.setUserId(scan.next());
		System.out.print("请输入用户名：");
		user.setUserName(scan.next());
		System.out.print("请输入密码：");
		try {
			user.setUserPassword(Md5Util.encodeByMd5(scan.next()));
			user.setDate(new java.sql.Date(new java.util.Date().getTime()));
			user.setStatus(true);
			DataPackage dp = new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.ADD_USER_ACTION);
			dp.setData(user);
			dp = doAction(dp);
			if (dp.getMessage().equals(ActionConst.OK_MESSAGE)) {
				System.out.println("————————————————————————");
				System.out.println("|   用户添加成功        |");
				System.out.println("————————————————————————");
			} else {
				System.out.println("————————————————————————");
				System.out.println("|   用户添加失败        |");
				System.out.println("————————————————————————");
			}
		} catch (Exception e) {
			System.out.println("————————————————————————————————————");
			System.out.println("|   用户添加失败，程序出现异常       |");
			System.out.println("————————————————————————————————————");
		}
	}
	

}


