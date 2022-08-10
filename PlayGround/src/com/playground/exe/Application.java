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
		System.out.println("───────────────────────────────────────────────────────────────");
		System.out.println("|           함께 가고 싶은 곳, 위드댕 입니다! U・ᴥ・U ♥(˘ε ˘)           |");
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
	
	// 1. 멤버십 등록 | 2. 멤버십 수정 | 3. 멤버십 조회 | 4. 멤버십 충전 | 4. 포인트 적립 | 5. 수영장 예약현황 | 6. 멤버십 삭제 | 7. 일자별 통계
	
	private void practice() {
		
		// Admin 관리자 0
		
		int menu = 0;
		
		if (AdminService.memberInfo.getRole().equals("0")) {
			
		
		System.out.println("─────────────────────────────────────────────────────────────");
		System.out.println("| 1. 멤버십 등록 | 2. 멤버십 수정 | 3. 멤버십 조회 | 4. 멤버십 충전     |");   
		System.out.println("─────────────────────────────────────────────────────────────");  
		System.out.println("| 4. 포인트 적립 | 5. 수영장 예약현황 | 6. 멤버십 삭제 | 7. 일자별 통계  |");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("| 선택 >>>> ");
		menu = scn.nextInt();
		
			if (menu == 1) {
				// 멤버십 등록 (회원가입)
				System.out.println("──────────────────────────────────────────────────────────────");
				System.out.println("           [   U・ᴥ・U 멤버십 회원 등록 탭입니다.  U・ᴥ・U   ]           ");
				System.out.println("──────────────────────────────────────────────────────────────");
				System.out.println();
				as.registMembership();
				// 멤버십 수정 (전화번호 수정)
			} else if (menu == 2) {
				as.updateTel();
				
			} else if (menu == 3) {
				as.s
			} else if (menu == 4) {
			
			} else if (menu == 5) {
			
			} else if (menu == 6) {
				
					}

			}

		}

	}

