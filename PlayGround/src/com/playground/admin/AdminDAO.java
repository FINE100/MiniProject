package com.playground.admin;

import com.playground.member.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.playground.common.DAO;

public class AdminDAO extends DAO {

	// 싱글톤

	private static AdminDAO ad = new AdminDAO();

	private AdminDAO() {

	}

	private static AdminDAO getInstance() {
		return ad;
	}

	// 로그인
	public Member loginInfo(int id) {
		Member member = null;
		try {
			conn();
			String sql = "select * from member where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberName(rs.getString("member_name"));
				member.setRole(rs.getString("role"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}

	// 1. 멤버십 등록 | 2. 멤버십 수정(충전) | 3. 멤버십 조회 | 4. 멤버십 사용 | 5. 포인트 적립 | 6. 수영장 예약현황 |
	// 7. 멤버십삭제

	// 1. 멤버십 등록 insert
	public int registMembership(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "insert into member (member_id, member_pw,"
					+ "member_name,member_tel, member_puppy, member_charging, member_point, role) values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberTel());
			pstmt.setInt(5, member.getMemberPuppy());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setString(8, member.getRole());

			result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("정상 등록 되었습니다.");
			} else {
				System.out.println("등록이 실패하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 2. 멤버십 수정 update
	// 2-1. 멤버십 충전
	public int chargeMembership(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "update member set member_charging = ? where member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberCharging());
			pstmt.setInt(2, member.getMemberId());

			result = stmt.executeUpdate(sql);

			if (result == 1) {
				System.out.println("충전이 완료 되었습니다.");
			} else {
				System.out.println("충전 실패.");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}

		return result;
	}

	// 2. 멤버십 수정 update
	// 2-2. 멤버십 정보 수정
	public int updateMembership(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "update member set member_tel = ? where member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberTel());
			pstmt.setInt(2, member.getMemberId());

			result = stmt.executeUpdate(sql);

			if (result == 1) {
				System.out.println("전화번호 변경 완료.");
			} else {
				System.out.println("전화번호 변경 실패.");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}

		return result;
	}

	// 멤버십 조회 select
	// 3-1) 단건 회원 조회 : 회원ID, 이름, 연락처

	public List<Member> MemberInfo() {
		List<Member> list = new ArrayList<>();
		Member member = null;

		try {
			conn();
			String sql = "SELECT member_id, member_name, member_tel FROM member WHERE member_id = ?";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));

				list.add(member);

			}
		} catch (SQLException e) {
			System.out.println("※※※Error 에러 코드표 확인하세요.※※※");
			System.out.println("해당 Error 코드 : " + e.getErrorCode());
			System.out.println("해당원인" + e.getMessage());

			// ora_00001 : 어떤 이유로 오류가 났습니다. >> 표시할 수 있도록 함.
			e.getMessage(); // 오류 메세지 보이게 하는 방법
			e.getErrorCode(); // 오류 메세지 보이게 하는 방법

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return list;
	}

// 3-2) 전체 회원 조회 : 회원ID, 이름, 연락처, 강아지 수, 멤버십 금액, 잔여 금액, 포인트

	public List<Member> allMemberInfo() {
		List<Member> list = new ArrayList<>();
		Member member = null;

		try {
			conn();
			String sql = "select * from member";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				member.setMemberPuppy(rs.getInt("member_puppy"));
				member.setMemberCharging(rs.getInt("member_charging"));
				member.setMemberPoint(rs.getInt("member_point"));
				member.setRole(rs.getString("role"));

				list.add(member);

			}
		} catch (SQLException e) {
			System.out.println("※※※Error 에러 코드표 확인하세요.※※※");
			System.out.println("해당 Error 코드 : " + e.getErrorCode());
			System.out.println("해당원인" + e.getMessage());

			// ora_00001 : 어떤 이유로 오류가 났습니다. >> 표시할 수 있도록 함.
			e.getMessage(); // 오류 메세지 보이게 하는 방법
			e.getErrorCode(); // 오류 메세지 보이게 하는 방법

		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return list;
	}



// 4. 멤버십 사용

public int useMembership(Member member) {
	int result = 0;
	try {
		conn();
		String sql = "update member set member_charging = member_charging -? where member_id =?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, member.getMemberCharging());
		pstmt.setInt(2, member.getMemberId());

		result = stmt.executeUpdate(sql);

	
	}catch (SQLException e) {
		System.out.println("※※※Error 에러 코드표 확인하세요.※※※");
		System.out.println("해당 Error 코드 : " + e.getErrorCode());
		System.out.println("해당원인" + e.getMessage());

		// ora_00001 : 어떤 이유로 오류가 났습니다. >> 표시할 수 있도록 함.
		e.getMessage(); // 오류 메세지 보이게 하는 방법
		e.getErrorCode(); // 오류 메세지 보이게 하는 방법

	} catch (Exception e) {

	} finally {
		disconnect();
	} return result;

}



// 포인트 적립 update 
public int useMemberPoint(Member member) {
	int result = 0;
	try {
		conn();
		String sql = "update member set member_point = ? where member_id =?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, member.getMemberPoint());
		pstmt.setInt(2, member.getMemberId());

		result = stmt.executeUpdate(sql);

	
	}catch (SQLException e) {
		System.out.println("※※※Error 에러 코드표 확인하세요.※※※");
		System.out.println("해당 Error 코드 : " + e.getErrorCode());
		System.out.println("해당원인" + e.getMessage());

		// ora_00001 : 어떤 이유로 오류가 났습니다. >> 표시할 수 있도록 함.
		e.getMessage(); // 오류 메세지 보이게 하는 방법
		e.getErrorCode(); // 오류 메세지 보이게 하는 방법

	} catch (Exception e) {

	} finally {
		disconnect();
	} return result;

}



// 수영장 예약현황 select
//4-1) 회원별 예약 현황 조회 : 회원ID, 이름, 강아지 수, 예약날짜, 이용 시간(타임)

public Member selectReservation() {
	Member member = new Member();
	try {
		conn();
		
	
}
	
	retrun member;
}
//4-2) 타임별 예약 현황 조회 : A타임 | B 타임 | C 타임 





// 멤버십 삭제 delete

public int deleteMembershp(int memberId) {
	int result = 0;
	try {
		conn();
		String sql = "delet from member where member_id =?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memberId);
		
		result = pstmt.executeUpdate();
	}catch (SQLException e) {
		System.out.println("※※※Error 에러 코드표 확인하세요.※※※");
		System.out.println("해당 Error 코드 : " + e.getErrorCode());
		System.out.println("해당원인" + e.getMessage());

		// ora_00001 : 어떤 이유로 오류가 났습니다. >> 표시할 수 있도록 함.
		e.getMessage(); // 오류 메세지 보이게 하는 방법
		e.getErrorCode(); // 오류 메세지 보이게 하는 방법

	} catch (Exception e) {

	} finally {
		disconnect();
	} return result;

}
}





