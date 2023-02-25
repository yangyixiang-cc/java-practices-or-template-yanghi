package com.nnrs.socket.client.main;

import java.util.Scanner;

import com.nnrs.socket.client.ui.AdministratorUI;
import com.nnrs.socket.client.ui.LocalUserUI;
import com.nnrs.socket.client.ui.UserUI;

public class App {
	
	public static void main(String[] args) {
		AdministratorUI adminUI = new AdministratorUI();
		UserUI userUI = new UserUI();
		LocalUserUI localUI = new LocalUserUI();
		while (true) {
			System.out.println("——————————————————————————————网络小说阅读系统———————————————————————————-");
			System.out.println("————————————————————————————————————————————————————————————————————");
			System.out.println("——————————————————————————————1. 本地阅读——————————————————————————————");
			System.out.println("——————————————————————————————2. 在线阅读——————————————————————————————");
			System.out.println("——————————————————————————————3. 读者注册——————————————————————————————");
			System.out.println("——————————————————————————————4. 后台管理——————————————————————————————");
			System.out.println("——————————————————————————————5. 退出系统——————————————————————————————");
			System.out.println("————————————————————————————————————————————————————————————————————");
			Scanner scan = new Scanner(System.in);
			System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
			System.out.print("请选择：");
			String choose = scan.next();
			switch (choose) {
			case "1":
				localUI.localMenu();
				break;
			case "2":
				userUI.login();
				break;
			case "3":
				userUI.userRegister();
				break;
			case "4":
				adminUI.login();
				break;
			case "5":
				System.out.println("欢迎下次使用！");
				System.exit(0);
				break;
			default:
				System.out.println("操作失败");
			}
		}

	}

}
