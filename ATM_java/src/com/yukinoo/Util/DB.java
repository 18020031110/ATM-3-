package com.yukinoo.Util;

import java.util.*;
import java.io.*;

public class DB {
	final static int MAXN = 1000;
	private String[] name = new String[MAXN];
	private String[] id = new String[MAXN];
	private String[] password = new String[MAXN];
	int[] money = new int[MAXN];
	int tot = 0;
	
	public int signIn(String id, String password) {
		for(int i = 0; i < tot; i++) {
			if(!id.equals(this.id[i])) continue;
			if(password.equals(this.password[i]))return 1;
			else return 0;
		}
		return -1;
	}
	
	public int register(String name, String id, String password) {
		for(int i = 0; i < tot; i++) {
			if(id.equals(this.id[i]))return -1;
		}
		if(tot >= MAXN)return 0;
		this.name[tot] = name;
		this.id[tot] = id;
		this.password[tot] = password;
		this.money[tot] = 0;
		tot++;
		return 1;
	}
	
	public int saving(String id, int money) {
		for(int i = 0; i < tot; i++) {
			if(id.equals(this.id[i])) {
				this.money[i] += money;
				return 1;
			}
		}
		return 0;
	}
	
	public int withdrawing(String id, int money) {
		for(int i = 0; i < tot; i++) {
			if(id.equals(this.id[i])) {
				if(this.money[i] >= money) {
					this.money[i] -= money;
					return 1;
				}
				else return 0;
			}
		}
		return 0;
	}
	
	public int quary(String id) {
		for(int i = 0; i < tot; i++) {
			if(id.equals(this.id[i]))return this.money[i];
		}
		return 0;
	}
}
