package com.playground.member;

import java.util.List;
import java.util.Scanner;


import com.playground.admin.AdminDAO;
import com.playground.admin.AdminService;
import com.playground.common.Join;
import com.prlayground.pay.Pay;



public class MemberService {
	Scanner scn = new Scanner(System.in);
	
	
	// 멤버십 조회 
	public void searchMember() {
		
		 List<Member> list = MemberDAO.getInstance().searchMember(AdminService.memberInfo.getMemberId());
		for (Member member : list) {
			System.out.println("──────────────────────────────────────────────────────────────");
			System.out.println(" ● 회원 ID   : " + member.getMemberId());
			System.out.println(" ● 회원 이름  : " + member.getMemberName());
			System.out.println(" ● 회원 연락처 : " + member.getMemberTel());
			System.out.println("--------------------------------------------------------------");
			System.out.println(" ● 멤버십 금액  : " + member.getCharging());
			System.out.println(" ● 포인트 잔액  : " + member.getMemberTel());
			System.out.println("──────────────────────────────────────────────────────────────");

		}

	}
	
	//멤버십 사용 + 포인트 적립 
	public void useMembership() {
		Join join = new Join();
		
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [     U・ᴥ・U 멤버십 사용 탭입니다.  U・ᴥ・U     ]           ");
		System.out.println("──────────────────────────────────────────────────────────────");
		
		 System.out.println("멤버십 ID >>"    );
		 int memberId = Integer.parseInt(scn.nextLine());
		 System.out.println("1. 운동장 사용 | 2. 수영장 사용 >>>>> " );
		 int use = Integer.parseInt(scn.nextLine());
		 
		 join.setMemberId(memberId);
		 join.setUse(use);
		 

			int result = MemberDAO.getInstance().useMembership(join);
			if(result == 1) {
				System.out.println("상태 변경 완료");
			}else {
				System.out.println("상태 변경 실패");
			}
		 
	}
	
	
}
