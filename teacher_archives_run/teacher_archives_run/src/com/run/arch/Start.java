package com.run.arch;

import com.run.arch.ui.AdminUI;
import com.run.arch.ui.UserUI;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        AdminUI adminUI = new AdminUI();
        UserUI userUI = new UserUI();
        while (true){
            System.out.println("——————————————————————————————教师业务档案管理系统—————————————————————");
            System.out.println("———————————————————————————————————————————————————————————————————");
            System.out.println("——————————————————————————————1. 教师登录————————————————————————————");
            System.out.println("——————————————————————————————2. 管理员登录——————————————————————————");
            System.out.println("——————————————————————————————3. 教师注册————————————————————————————");
            System.out.println("——————————————————————————————4. 退出系统————————————————————————————");
            System.out.println("————————————————————————————————————————————————————————————————————");
            Scanner scan = new Scanner(System.in);
            System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
            System.out.print("请选择：");
            String choose = scan.next();
            switch (choose) {
                case "1":
                    userUI.login();
                    break;
                case "2":
                    adminUI.login();
                    break;
                case "3":
                    userUI.register();
                    break;
                case "4":
                    System.out.println("欢迎下次使用！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("操作失败");
            }
        }
    }
}
