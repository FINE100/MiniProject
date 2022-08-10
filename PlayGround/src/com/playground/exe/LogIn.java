package com.playground.exe;

import java.util.Scanner;

import com.playground.admin.AdminService;

public class LogIn {

	Scanner scn = new Scanner(System.in);
	AdminService as = new AdminService();
	int menuNo = 0;

	public LogIn() {
		run();
	}

	private void run() {
		System.out.println("1. 로그인 | 2. 종료");
		menuNo = Integer.parseInt(scn.nextLine());
		switch (menuNo) {
		case 1:

			as.doLogin();
			if (AdminService.memberInfo != null) {
				new AdminService();
			}

			break;

		case 2:
			// 나중에
			break;

		}
	}

}