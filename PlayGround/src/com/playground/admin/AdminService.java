package com.playground.admin;

import java.util.List;
import java.util.Scanner;

import com.playground.common.Join;
import com.playground.member.Member;
import com.playground.member.MemberDAO;
import com.playground.reservation.Reservation;

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

		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [   U・ᴥ・U 멤버십 회원 등록 탭입니다.  U・ᴥ・U   ]           ");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println();

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
		member.setCharging(1000);
		member.setPoint(100);

		int result = AdminDAO.getInstance().registMembership(member);

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

	// 회원 정보 조회
	public void allSearchMember() {
		List<Member> list = AdminDAO.getInstance().allSearchMember();
		for (Member member : list) {
			System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
			System.out.println("           [    U・ᴥ・U 회원 정보 조회 탭입니다.  U・ᴥ・U      ]         ");
			System.out.println("──────────────────────────────────────────────────────────────");
			System.out.println(" ● 회원 ID    : " + member.getMemberId());
			System.out.println(" ● 회원 이름   : " + member.getMemberName());
			System.out.println(" ● 회원 연락처  : " + member.getMemberTel());
			System.out.println(" ● 강아지 수    : " + member.getMemberPuppy());
			System.out.println("--------------------------------------------------------------");
			System.out.println(" ● 멤버십 금액  : " + member.getCharging());
			System.out.println(" ● 포인트 잔액  : " + member.getPoint());
			System.out.println("──────────────────────────────────────────────────────────────");

		}

	}

	public void searchMember() {
		System.out.println("조회할 ID를 입력해주세요 >> ");
		int id = Integer.parseInt(scn.nextLine());

		List<Member> list = MemberDAO.getInstance().searchMember(id);
		for (Member member : list) {
			System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
			System.out.println("           [    U・ᴥ・U 회원 정보 조회 탭입니다.  U・ᴥ・U      ]         ");
			System.out.println("──────────────────────────────────────────────────────────────");
			System.out.println(" ● 회원 ID   : " + member.getMemberId());
			System.out.println(" ● 회원 이름  : " + member.getMemberName());
			System.out.println(" ● 회원 연락처 : " + member.getMemberTel());
			System.out.println("--------------------------------------------------------------");
			System.out.println(" ● 멤버십 금액  : " + member.getCharging());
			System.out.println(" ● 포인트 잔액  : " + member.getPoint());
			System.out.println("──────────────────────────────────────────────────────────────");

		}

	}

	// 포인트 사용
	public void usePoint() {

		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [    U・ᴥ・U  포인트 사용 탭입니다.  U・ᴥ・U      ]          ");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("         포인트 5000점 이상부터 멤버십 충전으로 사용 가능합니다.          ");
		System.out.println("               포인트를 충전 금액으로 전환 하시겠습니까?                ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("                [  1. YES   ]       [ 2.  NO   ]              ");
		System.out.println("---------------------------------------------------------------");
		System.out.print(" 입력 >> ");
		int q = Integer.parseInt(scn.nextLine());
		System.out.println("---------------------------------------------------------------");

		int pointId = 0;
		if (q == 1) {
			System.out.print(" 회원 ID 입력 >>>  ");
			pointId = Integer.parseInt(scn.nextLine());
			System.out.println("──────────────────────────────────────────────────────────────");
			AdminDAO.getInstance().usePoint(pointId);
		} else {
			System.out.println();
			System.out.println("메뉴로 돌아가겠습니다~!");
			System.out.println();
		}
	}


		
		
	
	// 일자별 통계
	public void dailySales() {
		Member member = new Member();
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [   U・ᴥ・U  일자별 매출 확인 탭입니다.  U・ᴥ・U    ]         ");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("                매출 조회가 필요한 날짜를 입력 해주세요.               ");
		System.out.println("---------------------------------------------------------------");
		System.out.print(" 입력 (yyyy/mm/dd ) >> ");
		String startday = scn.nextLine();
		System.out.println("---------------------------------------------------------------");
		member = AdminDAO.getInstance().dailySales(startday);

		System.out.println("● 조회 날짜 : " + member.getStartDay());
		System.out.println("● 매출 합계 : " + member.getCharging() + "원");

	}

	// 일일 방분객
	public void dailyVisitor() {
		Reservation reserv = new Reservation();
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [   U・ᴥ・U  일일 방문객 확인 탭입니다.  U・ᴥ・U    ]        ");
		System.out.println("──────────────────────────────────────────────────────────────");

		reserv = AdminDAO.getInstance().dailyVisitor();

		System.out.println("● 방문한 고객 : " + reserv.getVisitor() + " 명");
		System.out.println("● 방문 강아지 : " + reserv.getReservationPuppy() + " 마리");

	}

	// 수영장 예약 현황

	// 날짜별 예약 현황
	public void selectReservation() {

		System.out.println("조회할 날짜를 입력해주세요.(yyyy/mm/dd) >>");
		String date = scn.nextLine();

		List<Join> list = AdminDAO.getInstance().selectReservation(date);
		for (Join join : list) {
			System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
			System.out.println("         [   U・ᴥ・U 날짜별 수영장 예약 조회 탭입니다.  U・ᴥ・U   ]       ");
			System.out.println("──────────────────────────────────────────────────────────────");
			System.out.println(" ● 예약 날짜  : " + join.getReservationDate());
			System.out.println(" ● 이용 시간  : " + join.getReservationTime() + "타임");
			System.out.println("--------------------------------------------------------------");
			System.out.println(" ● 회원 ID    : " + join.getMemberId());
			System.out.println(" ● 회원 이름   : " + join.getMemberName());
			System.out.println(" ● 강아지 수    : " + join.getMemberPuppy());
			System.out.println("──────────────────────────────────────────────────────────────");

		}

	}

	// 멤버십 삭제
	public void deleteMembershp() {
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("            [     U・ᴥ・U 회원 삭제 탭입니다.  U・ᴥ・U      ]          ");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println(" ● ID >> ");
		int memeberId = Integer.parseInt(scn.nextLine());
		int result = AdminDAO.getInstance().deleteMembershp(memeberId);
		System.out.println("--------------------------------------------------------------");

		if (result == 1) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

}
