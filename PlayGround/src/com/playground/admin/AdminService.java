package com.playground.admin;

import java.util.Scanner;

import com.playground.member.Member;


public class AdminService {

	Scanner scn = new Scanner(System.in);


	
	// 멤버십 등록
	public void insertStudent(Member member) {
		int result = AdminDAO.getInstance().registMembership(member);
		// rsult == 1의 의미 : 1행 삽입 성공 
		  if(result == 1) {
			System.out.println("멤버십 등록 성공");
		} else {
			System.out.println("멤버십 등록 실패");
		}
	}
	
	
	}
	
	


