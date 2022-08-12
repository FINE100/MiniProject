package com.playground.member;

import java.util.List;
import java.util.Scanner;

import com.playground.admin.AdminDAO;
import com.playground.admin.AdminService;
import com.playground.common.Join;
import com.playground.reservation.Reservation;
import com.prlayground.pay.Pay;

public class MemberService {
	Scanner scn = new Scanner(System.in);
	Member member = new Member();

	// 멤버십 충전
	public void charging() {
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [    U・ᴥ・U  멤버십 충전 탭입니다.  U・ᴥ・U      ]          ");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.print(" 충전할 ID를 입력해주세요 >>> ");
		int id = Integer.parseInt(scn.nextLine());
		System.out.println("---------------------------------------------------------------");
		System.out.print(" 충전할 금액을 입력해주세요 >>> ");
		int money = Integer.parseInt(scn.nextLine());
		System.out.println("---------------------------------------------------------------");

		member.setMemberId(id);
		member.setCharging(money);
		member.getStartDay();

		int result = MemberDAO.getInstance().charging(member);

		if (result == 1) {
			System.out.println();
			System.out.print("" + money + "원 충전 완료!");
			System.out.println();
		} else {
			System.out.println("충전이 실패했습니다. 다시 확인해주세요.");
			System.out.println();
		}

	}

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
			System.out.println(" ● 포인트 잔액  : " + member.getPoint());
			System.out.println("──────────────────────────────────────────────────────────────");

		}

	}

	// 멤버십 사용 + 포인트 적립
	public void useMembership() {
		Join join = new Join();

		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [     U・ᴥ・U 멤버십 사용 탭입니다.  U・ᴥ・U     ]           ");
		System.out.println("──────────────────────────────────────────────────────────────");

		System.out.print(" 멤버십 ID >>");
		int memberId = Integer.parseInt(scn.nextLine());

		System.out.print(" 이용 날짜 >>");
		String date = scn.nextLine();

		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("|            [ 1. 운 동 장 ]          [ 2. 수 영 장  ]           |");
		System.out.println("──────────────────────────────────────────────────────────────");

		System.out.println(" 선택 >>");
		int use = Integer.parseInt(scn.nextLine());

		join.setMemberId(memberId);
		join.setReservationDate(date);

		int result = MemberDAO.getInstance().useMembership(join, use);
		if (result == 1) {
			System.out.println("♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥");
		} else {
			System.out.println(" T_T ");
		}

	}

	// 수영장 예약
	public void reserveSwim() {
		Reservation reserv = new Reservation();
		System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
		System.out.println("           [     U・ᴥ・U 수영장 예약 탭입니다.  U・ᴥ・U     ]          ");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.print("● 예약 ID >> ");
		int memberId = Integer.parseInt(scn.nextLine());
		System.out.print("● 예약 날짜 >> ");
		String date = scn.nextLine();
		System.out.print("● 예약 타임 ( A / B / C ) >> ");
		String time = scn.nextLine();
		System.out.print("● 방문자수 >> ");
		int visitor = Integer.parseInt(scn.nextLine());
		System.out.print("● 강아지 수 >> ");
		int puppy = Integer.parseInt(scn.nextLine());

		reserv.setMemberId(memberId);
		reserv.setReservationDate(date);
		reserv.setReservationTime(time);
		reserv.setVisitor(visitor);
		reserv.setReservationPuppy(puppy);

		MemberDAO.getInstance().reserveSwim(reserv);
	}

	// 수영장 예약조회

	public void Reservation() {
		System.out.println("조회할 ID >>");
		int id = Integer.parseInt(scn.nextLine());

		List<Join> list = MemberDAO.getInstance().TimeReservation(id);
		for (Join join : list) {
			System.out.println("───────────♥º♥──────────── with Dang ─────────────♥º♥─────────");
			System.out.println("         [   U・ᴥ・U 회원별 수영장 예약 조회 탭입니다.  U・ᴥ・U   ]       ");
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

}
