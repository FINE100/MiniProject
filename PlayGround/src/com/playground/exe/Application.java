package com.playground.exe;

import java.util.Scanner;

import com.playground.admin.AdminService;
import com.playground.member.MemberService;

public class Application {
	Scanner scn = new Scanner(System.in);

	AdminService as = new AdminService();
	MemberService ms = new MemberService();

	int menuNo = 0;

	public Application() {

		run();
	}

	private void run() {
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("|           함께 가고 싶은 곳, 위드댕 입니다! U・ᴥ・U ♥(˘ε ˘)          |");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("|            [ 1. 로 그 인 ]            [ 2. 종 료  ]            |");
		System.out.println("───────────────────────────────────────────────────────────────");
		System.out.print("메뉴 선택 >>  ");
		menuNo = Integer.parseInt(scn.nextLine()); 
		System.out.println("───────────────────────────────────────────────────────────────");

		switch (menuNo) {
		case 1:

			as.doLogin();
			if (AdminService.memberInfo != null) {
				practice();
			}

			break;

		case 2:
			// 나중에
			break;

		}
	}

	// 1. 멤버십 등록 | 2. 멤버십 수정 | 3. 멤버십 조회 | 4. 멤버십 충전 | 4. 포인트 적립 | 5. 수영장 예약현황 | 6.
	// 멤버십 삭제 | 7. 일자별 통계

	private void practice() {

		// Admin 관리자 0

		int menu = 0;
		boolean flag = true;

		while (flag) {
		if (AdminService.memberInfo.getRole().equals("0")) {

			System.out.println("───────────────────────── with Dang ──────────────────────────");
			System.out.println("| 1. 멤버십 등록 | 2. 멤버십 수정    | 3. 멤버십 조회 | 4. 멤버십 충전  |");
			System.out.println("─────────────────────────────────────────────────────────────");
			System.out.println("| 5. 멤버십 삭제 | 6. 수영장 예약현황 | 7. 일자별 통계 | 8. 종료       |");
			System.out.println("──────────────────────────────────────────────────────────────");
			System.out.println("| 선택 >>>> ");
			
			menu = scn.nextInt();


				if (menu == 1) {
					// 멤버십 등록 (회원가입)						
					as.registMembership();
					
					// 멤버십 수정 (전화번호 수정)
				} else if (menu == 2) {
					as.updateTel();
					// 멤버 조회 
				} else if (menu == 3) {
					as.allSearchMember();

				} else if (menu == 4) {
					
				} else if (menu == 5) {					
					as.selectReservation();
				} else if (menu == 6) {

				} else if (menu == 7) {

				}

			}else if(AdminService.memberInfo.getRole().equals("1")) {
				System.out.println("─────────────────────────────────────────────────────────────");
				System.out.println("| 1. 멤버십 충전 |  2. 멤버십 사용  | 3. 멤버십 조회 |  4. 수영장 예약 |");
				System.out.println("─────────────────────────────────────────────────────────────");
				System.out.println("| 선택 >>>> ");
				menu = scn.nextInt();
				
				// 멤버십 충전
				if (menu == 1) {
				
				// 멤버십 사용
				} else if (menu == 2) {
					ms.useMembership();
					
				// 멤버십 조회	
				} else if (menu == 3) {
					ms.searchMember();

				// 수영장 예약 	
				} else if (menu == 4) {
					
					
				}

			}

		}
	}
}
