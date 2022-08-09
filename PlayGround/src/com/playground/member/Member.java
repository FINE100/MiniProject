package com.playground.member;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class Member {
/*
 *  ID    		number 		primary key		// 회원 ID 
	PW	  	 varchar2(15)			   		// 회원 비밀번호 (= tel)  
	NAME 	 varchar2(20)					// 회원 이름
	TEL		 varchar2(15)					// 전화번호
	PUPPY 		number 						// 강아지 수
	CHARGING 	number						// 충전액 
	POINT 		number						// 포인트
 */
	

	private int memberId;		// 회원 ID
	private String memberPw;	// 회원 pw = tel
	private String memberName;	// 회원이름
	private String memberTel;	// 전화번호
	private int memberPuppy;	// 강아지 수
	
	
	private int memberCharging; // 충전액 
	private int fare;			// 사용금액   => 컬럼없음. 필요시 추가 요망.
	private int memberPoint; 	// 포인트
	private String role;		// 롤 = 일반 회원 default : 1
	
	
	@Override
	public String toString() {
		return " 회원 정보 [ 회원 ID : " + memberId + ", 이름 : " + memberName + ", 연락처 : " + memberTel
				+ ", 강아지 수 : " + memberPuppy +"마리" + ", 멤버십 금액 현황 : " + (memberCharging - fare)+ "/" + memberCharging 
				+ ", 포인트 적립금액 : " + memberPoint	+ "]";
	}


	
	}



	
	
	
	

