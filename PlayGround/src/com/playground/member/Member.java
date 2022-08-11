package com.playground.member;

public class Member {
	/*
	 * ID number primary key // 회원 ID PW varchar2(15) // 회원 비밀번호 (= tel) NAME
	 * varchar2(20) // 회원 이름 TEL varchar2(15) // 전화번호 PUPPY number // 강아지 수 CHARGING
	 * number // 충전액 POINT number // 포인트
	 */

	private int memberId; // 회원 ID
	private String memberPw; // 회원 pw = tel
	private String memberName; // 회원이름
	
	private String memberTel; // 전화번호
	private int memberPuppy; // 강아지 수
	private String role; // 롤 = 일반 회원 default : 1
	private int use; // 1 운동장 2 운동장 + 수영장
	

	private int charging; // 충전액
	private int point; // 포인트
	private String startDay; // 충전일

	
	
	

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public void setMemberPuppy(int memberPuppy) {
		this.memberPuppy = memberPuppy;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public int getMemberId() {
		return memberId;
	}

	
	
	public String getMemberPw() {
		return memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public int getMemberPuppy() {
		return memberPuppy;
	}
	
	public String getRole() {
		return role;
	}

	public int getCharging() {
		return charging;
	}

	public int getPoint() {
		return point;
	}
	public void setCharging(int charging) {
		this.charging = charging;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	

	@Override
	public String toString() {
		return " 회원 정보 [ 회원 ID : " + memberId + ", 이름 : " + memberName + ", 연락처 : " + memberTel + ", 강아지 수 : "
				+ memberPuppy + "마리" +  "]";
	}

	public int getUse() {
		return use;
	}

	public void setUse(int use) {
		this.use = use;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}


}
