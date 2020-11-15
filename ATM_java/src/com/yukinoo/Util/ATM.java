package com.yukinoo.Util;

import java.util.Scanner;

public class ATM {
	private static DB db = new DB();
	
	public void welcome() {
		System.out.println("===================");
		System.out.println("   欢迎使用ATM机   ");
		System.out.println("===================");
		
		while(true) {
			System.out.println("1.登陆");
			System.out.println("2.注册");
			System.out.print("请选择您想要办理的业务：");
			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			
			if(x==1) {
				int result = signIn();
			}
			else if(x==2) {
				int result = register();
			}
		}
	}
	
	public int signIn() {
		int result = 0;
		
		String name,id,password;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入账号：");
		id = sc.nextLine();
		System.out.println("请输入密码：");
		password = sc.nextLine();
		
		result = db.signIn(id, password);
		
		if(result == 0) {
			System.out.println("密码错误");
		}
		else if(result == -1) {
			System.out.println("该账号不存在");
		}
		
		if(result == 1) {
			while(true) {
				System.out.println("登陆成功，请选择你的操作");
				System.out.println("1.存钱");
				System.out.println("2.取钱");
				System.out.println("3.查询余额");
				System.out.println("4.登出");
				
				int x = sc.nextInt();
				
				int money = 0;
				if(x == 1) {
					System.out.println("请输入要存入的金额");
					money = sc.nextInt();
					int now_money;
					if(db.saving(id, money) == 1){
						now_money = db.quary(id);
						System.out.println("存入成功，您当前的余额为" + now_money);
					}
					else {
						System.out.println("存入失败");
					}
				}
				else if(x == 2) {
					System.out.println("请输入要取出的金额");
					money = sc.nextInt();
					int now_money = db.quary(id);
					if(db.withdrawing(id, money)==1) {
						now_money = db.quary(id);
						System.out.println("取出成功，您当前的余额为" + now_money);
					}
					else {
						System.out.println("您的余额不足，您当前的余额为" + now_money);
					}
				}
				else if(x == 3) {
					int now_money = db.quary(id);
					System.out.println("您的余额为" + now_money);
				}
				else if(x == 4) {
					break;
				}
			}
		}
		
		
		return result;
	}
	
	public int register() {
		int result = 0;
		
		String name,id,password;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入姓名：");
		name = sc.nextLine();
		System.out.println("请输入账号：");
		id = sc.nextLine();
		System.out.println("请输入密码：");
		password = sc.nextLine();
		
		result = db.register(name, id, password);
		
		
		if(result == 0) {
			System.out.println("数据库已满");
		}
		else if(result == -1) {
			System.out.println("该账号已存在");
		}
		return result;
	}
}
