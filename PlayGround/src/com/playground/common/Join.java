package com.playground.common;
import java.sql.Date;

public class Join {
	private int adminId;	  // 관리자id
	private String adminpPw; // 관리자pw
//	private String role;	 // 롤 = 관리자 default :0
	
	private int memberId; // 회원 ID
	private String memberPw; // 회원 pw = tel
	private String memberName; // 회원이름
	
	private String memberTel; // 전화번호
	private int memberPuppy; // 강아지 수

	private int memberCharging; // 충전액
	private int fare; // 사용금액 => 컬럼없음. 필요시 추가 요망.
	private int memberPoint; // 포인트
	private String role; // 롤 = 일반 회원 default : 1
	
	private int reservationId; // 회원 ID
	private String reservationDate; // 이용날짜
	private String reservationAvailable; // 이용 가능 인원
	private int reservationPuppy; // 강아지 수
	private int totalPuppy; // 해당일에 이용하는 강아지 수
	private String reservationTime; // 이용시간 (A,B,C)
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminpPw() {
		return adminpPw;
	}
	public void setAdminpPw(String adminpPw) {
		this.adminpPw = adminpPw;
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
	public int getMemberCharging() {
		return memberCharging;
	}
	public void setMemberCharging(int memberCharging) {
		this.memberCharging = memberCharging;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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



	
	
}
