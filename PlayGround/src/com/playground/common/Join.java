package com.playground.common;
import java.sql.Date;

public class Join {

	private int memberId; // 회원 ID
	private String memberPw; // 회원 pw = tel
	private String memberName; // 회원이름
	
	private String memberTel; // 전화번호
	private int memberPuppy; // 강아지 수

	private int charging; // 충전액	
	private int point; // 포인트
	private String role; // 롤 = 일반 회원 default : 1
	
	private String reservationDate; // 이용날짜
	private String reservationAvailable; // 이용 가능 인원
	private int reservationPuppy; // 강아지 수
	private int totalPuppy; // 해당일에 이용하는 강아지 수
	private String reservationTime; // 이용시간 (A,B,C)
	
	private int use;
	
	
	
	public int getUse() {
		return use;
	}
	public void setUse(int use) {
		this.use = use;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public int getMemberPuppy() {
		return memberPuppy;
	}
	public void setMemberPuppy(int memberPuppy) {
		this.memberPuppy = memberPuppy;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationAvailable() {
		return reservationAvailable;
	}
	public void setReservationAvailable(String reservationAvailable) {
		this.reservationAvailable = reservationAvailable;
	}
	public int getReservationPuppy() {
		return reservationPuppy;
	}
	public void setReservationPuppy(int reservationPuppy) {
		this.reservationPuppy = reservationPuppy;
	}
	public int getTotalPuppy() {
		return totalPuppy;
	}
	public void setTotalPuppy(int totalPuppy) {
		this.totalPuppy = totalPuppy;
	}
	public String getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	@Override
	public String toString() {
		return "★★ 수영장 예약 회원 조회 [ ID : " + memberId +" | "+ " 이름 : " + memberName +" | "+ " 강아지 수 : " + memberPuppy
				+" | "+ " 예약 날짜 : "+  reservationDate +" | "+ " 예약 타임 : " + reservationTime + " ]";
	}
	public int getCharging() {
		return charging;
	}
	public void setCharging(int charging) {
		this.charging = charging;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}



	
	
}
