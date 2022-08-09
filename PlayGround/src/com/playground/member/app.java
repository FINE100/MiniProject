package com.playground.member;

import com.playground.admin.AdminDAO;
import com.playground.admin.AdminService;

public class app {
	public static void main(String[] args) {

		AdminService as = new AdminService();

		as.selectReservation();

		//AdminDAO.getInstance().loginInfo(0);

			}
}
