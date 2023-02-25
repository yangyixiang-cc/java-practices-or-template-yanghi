package com.nnrs.socket.client.ui;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bethecoder.ascii_table.ASCIITable;
import com.nnrs.socker.client.impl.LocalUserImpl;
import com.nnrs.socket.entity.History;
import com.nnrs.socket.entity.Novel;
import com.nnrs.socket.entity.User;
import com.nnrs.socket.util.ActionConst;
import com.nnrs.socket.util.DataPackage;
import com.nnrs.socket.util.DirConst;
import com.nnrs.socket.util.FileUtil;
import com.nnrs.socket.util.Md5Util;

public class UserUI {
	static User user = null;
	String flog = "1";
	LocalUserImpl localServer = new LocalUserImpl();
	LocalUserUI localUI = new LocalUserUI();
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
	//注册用户
	public void userRegister() {
		User user = new User();
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入用户ID：");
		user.setUserId(scan.next());
		System.out.print("请输入用户名：");
		user.setUserName(scan.next());
		System.out.print("请输入密码：");
		String password = scan.next();
		try {
			user.setUserPassword(Md5Util.encodeByMd5(password));
		    user.setDate(new java.sql.Date(new java.util.Date().getTime()));
		    user.setStatus(true);
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.USER_REGISTER_ACTION);
			dp.setData(user);
			dp=doAction(dp);
			if(dp.getMessage().equals(ActionConst.OK_MESSAGE))
			{
				System.out.println("————————————————————————");
				System.out.println("|   用户注册成功        |");
				System.out.println("————————————————————————");
			}else
			{
				System.out.println("————————————————————————");
				System.out.println("|   用户注册失败        |");
				System.out.println("————————————————————————");
			}

		} catch (Exception e) {
			System.out.println("——————————————————————————————————");
			System.out.println("|   用户注册失败，程序出现异常     |");
			System.out.println("——————————————————————————————————");
		}
	}

	public void login() {
		System.out.println("————————————————————————————————————————————");
		System.out.println("|                 用户登录                 |");
		System.out.println("————————————————————————————————————————————");
		System.out.print("是否登录客户端：(1/0)：");
		Scanner input = new Scanner(System.in);
		flog = input.next();
		if(("1").equals(flog)) {
			boolean isSuccess = false;
			for (int i = 3; i >= 1; i--) {
				System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
				System.out.print("请输入用户ID: ");
				String userId = input.next();
				System.out.print("请输入用户密码: ");
				String password = input.next();
				try {
					User userObject = new User();
					userObject.setUserId(userId);
					userObject.setUserPassword(Md5Util.encodeByMd5(password));			
					DataPackage dp=new DataPackage();
					dp.setCode(DirConst.getCode());
					dp.setAction(ActionConst.USER_LOGIN_ACTION);								
					dp.setData(userObject);
					dp = doAction(dp);		
					user = (User)dp.getRdata();
					if (user != null && user.isStatus()) {
						isSuccess = true;
						break;
					} else {
						if(!user.isStatus()) {
							System.out.println("用户已注销，无法登录，请联系管理员,已返回");
							break;
						}else {
							if (i == 1) {
								System.out.println("您已错误三次，系统自动退出");
								System.exit(0);
							} else {
								System.out.println("用户名或密码错误，您还有 " + (i - 1) + "次机会");
							}
						}
						
					}
				} catch (Exception e) {
					System.out.println("登录错误 ");
				}
			}
			if (isSuccess) {
				System.out.println("————————————————————————————————————————————");
				System.out.println("|登录成功，欢迎你"+user.getUserName()+"用户  |");
				System.out.println("————————————————————————————————————————————");
				onlineMenu();
			}
		}else if (("0").equals(flog)) {
			System.out.println("————————————————————————————————————————————————");
			System.out.println("|你未登录，无法查看历史记录，无法从上次的阅读开始  |");
			System.out.println("————————————————————————————————————————————————");
			if(user == null) {
				user = new User();
				user.setUserId("000000");
				user.setUserName("游客");
			}
			onlineMenu();
		}else {
			flog = "0";
			System.out.println("输入错误，请重新输入！");
		}
	}

	public void onlineMenu() {
		String u = "0".equals(flog) ? "游客":user.getUserName()+"用户";
		System.out.println("————————————————————————————————");
		System.out.println("| 网络小说阅读系统["+u+"]    |");
		System.out.println("————————————————————————————————");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("1.搜索书籍      2.热门排行榜      3.在线阅读      4.下载书籍      5.查看历史记录      6.退出");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请选择：");
		String op = input.next();
		switch (op) {
		case "1":
			selectNovel();
			break;
		case "2":
			rankingList();
			break;
		case "3":
			onlineReadNovel();
			break;
		case "4":
			downloadNovel();
			break;
		case "5":
			if("1".equals(flog)) {
				showOnHistory();
			}else {
				System.out.println("——————————————————————————————————————");
				System.out.println("| 你未登录，无法查看历史记录，已自动返回 |");
				System.out.println("——————————————————————————————————————");
				onlineMenu();
			}
			break;
		case "6":
			System.out.println("欢迎下次使用！");
			System.exit(0);
			break;	
		default:System.out.println("操作失败");
		}
		onlineMenu();
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
	private int novelChapterNum(String novelId) {
		int num = 0;
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.NOVEL_CHAPTERS_NUM);
			dp.setData(novelId);
			dp=doAction(dp);
			num = (int) dp.getRdata();
		} catch (Exception e) {
			System.out.println("———————————————————————————————");
			System.out.println("|    获取书籍章节数异常        |");
			System.out.println("———————————————————————————————");
		}
		return num;
	}
	private void onlineReadNovel() {
		String[] data = new String[3];
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.println("请输入你要阅读的书籍ID：");
		String novelId = scan.next();
		System.out.println("请输入你要阅读的书籍名称：");
		String fileName = scan.next();
		int chapters = novelChapterNum(novelId);
		int chapter = 1;
		StringBuilder s = null;
		data[0] = novelId;
		if(chapters == 0) {
			System.out.println("———————————————————————————");
			System.out.println("|    抱歉没有该书籍，已返回 |");
			System.out.println("———————————————————————————");
			onlineMenu();
		}else {
			while(true) {
				
				onlineReadOne(s,fileName,chapter,chapters,data);
		}
		}
		
		
	}


	
	private void onlineReadOne(StringBuilder s, String fileName, int chapter,int chapters,String[] data) {
		Scanner scan = new Scanner(System.in);
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.目录      2.从头阅读       3.跳转阅读       4.返回                                     |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你的操作：");
		String choose = scan.next();
		switch(choose) {
		case "1":
			data[1] = fileName;
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.SHOW_CATALOGUE_ACTION);
			dp.setData(data);
			dp=doAction(dp);
			List<String> list = (List<String>) dp.getRdata();
			if(list.isEmpty()) {
				System.out.println("———————————————————————————");
				System.out.println("|    目录为空              |");
				System.out.println("———————————————————————————");
			}else {
				System.out.println("————————————————————————————"+fileName+"————————————————————————————");
				for(String k: list) {
					System.out.println("|\t\t"+k.substring(0,k.indexOf(".")));
				}
				System.out.println("————————————————————————————————————————————————————————————————————");
			}
			break;
		case "2":
			try {
				data[1] = String.valueOf(chapter);
				data[2] = user.getUserId();
				DataPackage dp1 =new DataPackage();
				dp1.setCode(DirConst.getCode());
				dp1.setAction(ActionConst.READ_NOVEL_ACTION);
				dp1.setData(data);
				dp1=doAction(dp1);
				s = (StringBuilder) dp1.getRdata();
			} catch (Exception e) {
				System.out.println("———————————————————————————————");
				System.out.println("|    阅读书籍异常，已返回       |");
				System.out.println("———————————————————————————————");
				onlineReadOne(s, fileName, chapter, chapters,data);
			} 
			if( s != null) {
				System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
				System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
				System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
				System.out.println(s);
				System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
			}
			onlineReadTwo(s, fileName, chapter, chapters,data);
			break;
		case "3":
			System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
			System.out.print("请输入你要跳转的章节：");
			int num = scan.nextInt();
			try {
				if(num < 1 || num > chapters) {
					System.out.println("———————————————————————————————");
					System.out.println("|    无法跳转，不在章节范围内   |");
					System.out.println("———————————————————————————————");
				}else {
					data[1] = String.valueOf(num);
					data[2] = user.getUserId();
					DataPackage dp1=new DataPackage();
					dp1.setCode(DirConst.getCode());
					dp1.setAction(ActionConst.READ_NOVEL_ACTION);
					dp1.setData(data);
					dp1=doAction(dp1);
					s = (StringBuilder) dp1.getRdata(); 
					if( s != null) {
						System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
						System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
						System.out.println(s);
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
					}
				}
			} catch (Exception e) {
				System.out.println("———————————————————————————————");
				System.out.println("|    跳转章节异常，已返回       |");
				System.out.println("———————————————————————————————");
				onlineReadOne(s, fileName, chapter, chapters,data);
			}
			onlineReadTwo(s, fileName, chapter, chapters,data);
			break;
		case "4":
			onlineMenu();
			break;
		default:
			System.out.println("———————————————————————————————");
			System.out.println("|     请输入正确的序号         |");
			System.out.println("———————————————————————————————");
		
		}
	}

	private void onlineReadTwo(StringBuilder s, String fileName, int chapter, int chapters, String[] data) {
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.上一章          2.下一章               3.返回                                         |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你的操作：");
		String choose = scan.next();
		switch (choose) {
		case "1":
			if (chapter == 1) {
				System.out.println("———————————————————————————————");
				System.out.println("|    无法切换，已是第一章了     |");
				System.out.println("———————————————————————————————");
			} else {
				try {
					chapter--;
					data[1] = String.valueOf(chapter);
					data[2] = user.getUserId();
					DataPackage dp = new DataPackage();
					dp.setCode(DirConst.getCode());
					dp.setAction(ActionConst.READ_NOVEL_ACTION);
					dp.setData(data);
					dp = doAction(dp);
					s = (StringBuilder) dp.getRdata();
				} catch (Exception e) {
					System.out.println("———————————————————————————————");
					System.out.println("|    切换章节异常，已返回       |");
					System.out.println("———————————————————————————————");
					onlineReadOne(s, fileName, chapter, chapters, data);
				}
				if (s != null) {
					System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
					System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
					System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
					System.out.println(s);
					System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
				}
			}
			onlineReadTwo(s, fileName, chapter, chapters, data);
			break;
		case "2":
			try {
				if (chapter == localServer.novelChapterNum(fileName)) {
					System.out.println("———————————————————————————————");
					System.out.println("|    无法切换，已是最后一章了   |");
					System.out.println("———————————————————————————————");
					
				} else {
					chapter++;
					data[1] = String.valueOf(chapter);
					data[2] = user.getUserId();
					DataPackage dp = new DataPackage();
					dp.setCode(DirConst.getCode());
					dp.setAction(ActionConst.READ_NOVEL_ACTION);
					dp.setData(data);
					dp = doAction(dp);
					s = (StringBuilder) dp.getRdata();
					if (s != null) {
						System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
						System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
						System.out.println(s);
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
					}
					
				}
			} catch (Exception e) {
				System.out.println("———————————————————————————————");
				System.out.println("|    切换章节异常，已返回       |");
				System.out.println("———————————————————————————————");
				onlineReadOne(s, fileName, chapter, chapters, data);
			}
			onlineReadTwo(s, fileName, chapter, chapters, data);
			break;
		case "3":
			break;
		default:
			System.out.println("———————————————————————————————");
			System.out.println("|     请输入正确的序号         |");
			System.out.println("———————————————————————————————");

		}
	}
	private void downloadNovel() {
		String[] data = new String[2];
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你要下载的书籍ID：");
		String novelID = input.next();
		System.out.print("请输入你要下载的书籍名称：");
		String novelName = input.next();
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.DOWLOAD_NOVEL_ACTION);
			data[0] = novelID;
			data[1] = novelName;
			dp.setData(data);
			dp=doAction(dp);
			if(dp.getState() == ActionConst.ERROR) {
				System.out.println("————————————————————————————————————");
				System.out.println("|   没有该书籍或输入错误，请检查     |");
				System.out.println("————————————————————————————————————");
			}else {
				List[] lists = (ArrayList[]) dp.getRdata();
				System.out.println(FileUtil.novelPlace(lists, novelName));
			}
		} catch (Exception e) {
			System.out.println("————————————————————————————————————");
			System.out.println("|   下载调用失败，程序出现异常       |");
			System.out.println("————————————————————————————————————");
		}
		
	}

	private void showOnHistory() {
		try {
			DataPackage dp=new DataPackage();
			dp.setCode(DirConst.getCode());
			dp.setAction(ActionConst.SHOW_HISTORY_ACTION);
			dp.setData(user);
			dp=doAction(dp);
			if(dp.getState() == ActionConst.OK) {
				List<History> list = (List<History>) dp.getRdata();
				if(list.isEmpty()) {
					System.out.println("—————————————————————————————");
					System.out.println("|   暂时没有历史记录         |");
					System.out.println("—————————————————————————————");
				}else {
					String[] header = {"书籍编号","书籍名称","阅读到第几章节"};
					String[][] data = new String[list.size()][3];
					int j = 0;
					for(History h : list ) {
						data[j][0] = h.getNovelId();
						data[j][1] = h.getNovelName();
						data[j][2] = String.valueOf(h.getReadChapter());
						j++;
					}
					ASCIITable.getInstance().printTable(header, data);
				}
			}else {
				System.out.println("————————————————————————————————————");
				System.out.println("|   历史记录调用失败，程序出现异常    |");
				System.out.println("————————————————————————————————————");
			}
			
		} catch (Exception e) {
			System.out.println("————————————————————————————————————");
			System.out.println("|   历史记录调用失败，程序出现异常    |");
			System.out.println("————————————————————————————————————");
		}
		
	}
}
