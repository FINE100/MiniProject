package com.playground.reservation;

import com.playground.member.Member;


public class Reservation extends Member {

	/*
	 * ID number primary key // 회원 ID DATE DATE // 이용날짜 AVAILABLE varchar2(20) //
	 * 이용가능인원 (default = 20) PUPPY number // 강아지 수 TIME varchar2(20) // 이용시간 (A,B,C)
	 */
	
	private int memberId; // 회원 ID
	private String reservationDate; // 이용날짜
	private String reservationAvailable; // 이용 가능 인원
	private int reservationPuppy; // 강아지 수
	private int totalPuppy; // 해당일에 이용하는 강아지 수
	private String reservationTime; // 이용시간 (A,B,C)

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
		return " 수영장 예약 현황 [ 예약 회원 ID : " + memberId + ", 회원 이름 : " + getMemberName() + ", 예약 날짜 : "
				+ reservationDate + ", 이용할 타임 : " + reservationTime + "]";
	}

}
