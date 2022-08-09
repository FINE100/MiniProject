package com.playground.admin;

import com.playground.common.Join;
import com.playground.member.Member;

public class AdminService {

	
	public void selectReservation() {
		Join join =AdminDAO.getInstance().selectReservation();
		System.out.println(join.getMemberId());
	}
	
	
}

