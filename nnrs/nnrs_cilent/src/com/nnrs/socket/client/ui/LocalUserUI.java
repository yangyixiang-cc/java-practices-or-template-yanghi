package com.nnrs.socket.client.ui;
import java.util.List;
import java.util.Scanner;
import com.nnrs.socker.client.impl.LocalUserImpl;
import com.nnrs.socket.util.FileUtil;
import com.nnrs.socket.util.Log;

public class LocalUserUI {
	/**
	 * 顾客界面主菜单 《未登录》
	 */
	LocalUserImpl localServer = new LocalUserImpl();
	public void localMenu() {
		System.out.println("\t\t\t网络小说阅读系统[本地用户]");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.阅读书籍      2.查看本地书籍列表     3.历史记录     4.退出                              |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner input = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请选择：");
		String op = input.next();
		switch (op) {
		case "1":
			readLocalNovel();
			break;
		case "2":
			showNovelList();
			break;
		case "3":
			showHistory();
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
		localMenu();
	}

	private void readLocalNovel() {
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.println("请输入你要阅读的书籍名称：");
		String fileName = scan.next();
		int chapter = 1;
		StringBuilder s = null;
		if(FileUtil.isFileExist(fileName)) {
			while(true) {
				readOne(s,fileName,chapter);
			}
		}else {
			System.out.println("———————————————————————————————");
			System.out.println("|     本地没有改书籍，已返回    |");
			System.out.println("———————————————————————————————");
			localMenu();
		}
		
	}

	public void readOne(StringBuilder s,String fileName,int chapter){
		Scanner scan = new Scanner(System.in);
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.目录      2.从头阅读       3.跳转阅读       4.返回                                     |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你的操作：");
		
		String choose = scan.next();
		switch(choose) {
		case "1":
			try {
				Log.println("显示目录"+fileName);
				List<String> list = localServer.showCatalogue(fileName);
				System.out.println("————————————————————————————————————————————"+fileName+"———————————————————————————————————————");
				System.out.println("———————————————————————————————————————————目录———————————————————————————————————————————");
				for(String k : list) {
					System.out.println("|"+"\t\t"+k);
				}
				System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
			} catch (Exception e) {
				System.out.println("———————————————————————————————");
				System.out.println("|     目录调用异常，已返回      |");
				System.out.println("———————————————————————————————");
				readOne(s,fileName,chapter);
			} 
			break;
		case "2":
			try {
				s = localServer.readNovel(fileName, chapter);
			} catch (Exception e) {
				System.out.println("———————————————————————————————");
				System.out.println("|     阅读调用异常，已返回      |");
				System.out.println("———————————————————————————————");
				readOne(s,fileName,chapter);
			} 
			if( s != null) {
				Log.println("开始阅读"+fileName);
				System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
				System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
				System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
				System.out.println(s);
				System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
			}
			readTwo(s,fileName,chapter);
			break;
		case "3":
			System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
			System.out.print("请输入你要跳转的章节：");
			int num = scan.nextInt();
			try {
				if(num < 1 || num > localServer.novelChapterNum(fileName)) {
					System.out.println("无法跳转，不在范围内");
				}else {
						s = localServer.readNovel(fileName, num);
					if( s != null) {
						Log.println("跳转阅读"+fileName+"第"+chapter+"章");
						System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
						System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
						System.out.println(s);
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
					}
				}
			} catch (Exception e) {
				System.out.println("———————————————————————————————");
				System.out.println("|     跳转章节异常，已返回      |");
				System.out.println("———————————————————————————————");
				readOne(s,fileName,chapter);
			}
			readTwo(s,fileName,chapter);
			break;
		case "4":
			localMenu();
			break;
		default:
			System.out.println("———————————————————————————————");
			System.out.println("|     请输入正确的序号         |");
			System.out.println("———————————————————————————————");
		}
		
	}
	private void readTwo(StringBuilder s,String fileName,int chapter) {
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("|1.上一章          2.下一章               3.返回                                          |");
		System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
		Scanner scan = new Scanner(System.in);
		System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
		System.out.print("请输入你的操作：");
		String choose = scan.next();
		switch(choose) {
		case "1":
			if(chapter == 1) {
				System.out.println("———————————————————————————————");
				System.out.println("|    无法切换，已是第一章了     |");
				System.out.println("———————————————————————————————");
			}else {
				try {
					chapter--;
					s = localServer.readNovel(fileName, chapter);
				} catch (Exception e) {
					System.out.println("———————————————————————————————");
					System.out.println("|    切换章节异常，已返回       |");
					System.out.println("———————————————————————————————");
					readOne(s,fileName,chapter);
				} 
				if( s != null) {	
					Log.println("阅读"+fileName+"第"+chapter+"章");
					System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
					System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
					System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
					System.out.println(s);
					System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
				}
			}
			readTwo(s,fileName,chapter);
			break;
		case "2":
			try {
				if(chapter == localServer.novelChapterNum(fileName)) {
					System.out.println("———————————————————————————————");
					System.out.println("|    无法切换，已是最后一章了   |");
					System.out.println("———————————————————————————————");
				}else {
					chapter++;
					s = localServer.readNovel(fileName, chapter); 
					if( s != null) {
						Log.println("阅读"+fileName+"第"+chapter+"章");
						System.out.println("———————————————————————————————————————"+fileName+"——————————————————————————————————————————————————");
						System.out.println("———————————————————————————————————————第"+chapter+"章———————————————————————————————————————");
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
						System.out.println(s);
						System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
					}
				}
			} catch (Exception e1) {
				System.out.println("———————————————————————————————");
				System.out.println("|    切换章节异常，已返回       |");
				System.out.println("———————————————————————————————");
				readOne(s,fileName,chapter);
			}
			readTwo(s,fileName,chapter);
			break;
		case "3":
			break;
		default:
			System.out.println("———————————————————————————————");
			System.out.println("|     请输入正确的序号         |");
			System.out.println("———————————————————————————————");
		}
	}
	private void showNovelList() {
		try {
			List<String> list = localServer.showNovelList();
			if(list.isEmpty()) {
				System.out.println("———————————————————————————————");
				System.out.println("| 暂时没有书籍，无法生成列表    |");
				System.out.println("———————————————————————————————");
				System.out.println("");
			}else {
				System.out.println("—————————————————————书籍列表—————————————————————");
				for(String s : list) {
					System.out.println("|\t\t"+s);
				}
				System.out.println("—————————————————————————————————————————————————");
			}
		} catch (Exception e) {
			System.out.println("———————————————————————————————");
			System.out.println("|    显示书籍列表异常，         |");
			System.out.println("———————————————————————————————");
		}
	}

	private void showHistory() {
		try {
			List<String> list = localServer.showHistory();
			if(list.isEmpty()) {
				System.out.println("———————————————————————————————————");
				System.out.println("|暂时没有阅读历史，请立即开始阅读吧！|");
				System.out.println("———————————————————————————————————");
			}else {
				System.out.println("—————————————————————阅读历史—————————————————————");
				for(String s : list) {
					System.out.println("|\t"+s);
				}
				System.out.println("——————————————————————————————————————————————————");
			}	
		} catch (Exception e) {
			System.out.println("———————————————————————————————");
			System.out.println("|    显示阅读历史异常，         |");
			System.out.println("———————————————————————————————");
		}
	}

}
