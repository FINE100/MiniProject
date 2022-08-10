package com.playground.admin;

import java.util.List;
import java.util.Scanner;

import com.playground.member.Member;
import com.yedam.student.StudentDTO;
import com.yedam.student.StudentManage;

public class AdminService {

	Scanner scn = new Scanner(System.in);

	// 로그인
	public static Member memberInfo = null;

	// 로그인
	public void doLogin() {
		Member member = new Member();

		System.out.println("ID >");
		int id = Integer.parseInt(scn.nextLine());

		System.out.println("PW >");
		String pw = scn.nextLine();

		// 1 row...
		// id /pw..

		member = AdminDAO.getInstance().loginInfo(id);

		// DB 조회한 정보와 내가 입력한 PW 비교
		//
		if (member.getMemberPw().equals(pw)) {
			memberInfo = member;
		} else {
			System.out.println("로그인 실패");

		}

	}

	// 로그아웃
	public void logout() {
		if (memberInfo != null)
			memberInfo = null;
	}

	// 멤버십 등록
	public void registMembership() {
		Member member = new Member();

		System.out.println("ID > ");
		int id = Integer.parseInt(scn.nextLine());

		System.out.println("PW > ");
		String pw = scn.nextLine();

		System.out.println("이름 > ");
		String name = scn.nextLine();

		System.out.println("전화번호 > ");
		String tel = scn.nextLine();

		System.out.println("강아지 수 > ");
		int puppyNum = Integer.parseInt(scn.nextLine());

		member.setMemberId(id);
		member.setMemberPw(pw);
		member.setMemberName(name);
		member.setMemberTel(tel);
		member.setMemberPuppy(puppyNum);

		// role == 0, 일반 사용자

		member.setRole("1");
		member.setUse(1);

		int result = AdminDAO.getInstance().registMembership();

		if (result == 1) {
			System.out.println("고객 정보 등록 완료");
		} else {
			System.out.println("고객 정보 등록 실패");
		}

	}

	// 2. 멤버십 수정, 전화번호 수정

	public int updateTel() {
		Member member = new Member();
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("회원 ID 입력 > ");
		int id = Integer.parseInt(scn.nextLine());
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("변경할 전화번호 입력 > ");
		String tel = scn.nextLine();
		System.out.println("──────────────────────────────────────────────────────────────");

		member.setMemberId(id);
		member.setMemberTel(tel);

		int result = AdminDAO.getInstance().updateTel(member);

		if (result == 1) {
			System.out.println("정보 변경 완료");
		} else {
			System.out.println("정보 변경 실패");
		}

		return result;
	}

public void MemberInfo() {
	List<Member> list = AdminDAO.getInstance().MemberInfo();
	for(Member member : list) {
		System.out.println("*******************************************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("*******************************************************");
		
	}
	
}
}