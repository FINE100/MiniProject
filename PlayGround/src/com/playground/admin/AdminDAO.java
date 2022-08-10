package com.playground.admin;

import com.playground.member.Member;
import java.sql.Date;
import com.playground.reservation.Reservation;
import com.prlayground.pay.Pay;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.playground.common.DAO;
import com.playground.common.Join;

public class AdminDAO extends DAO {

	// 싱글톤

	private static AdminDAO ad = new AdminDAO();

	private AdminDAO() {

	}

	public static AdminDAO getInstance() {
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

	// 1. 멤버십 등록 | 2. 멤버십 수정 | 3. 멤버십 조회 | 4. 멤버십 충전 | 4. 포인트 적립 | 5. 수영장 예약현황 | 6.
	// 멤버십 삭제 | 7. 일자별 통계

	// 1. 멤버십 등록 insert
	public int registMembership() {
		Member member = new Member();
		int result = 0;
		try {
			conn();
			String sql = "insert into member (member_id, member_pw,"
					+ "member_name,member_tel, member_puppy, role,use) values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberTel());
			pstmt.setInt(5, member.getMemberPuppy());
			pstmt.setString(6, member.getRole());
			pstmt.setInt(7, member.getUse());

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
	public int chargeMembership(Pay pay) {
		int result = 0;
		try {
			conn();
			String sql = "update pay set charging = ? where member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pay.getCharging());
			pstmt.setInt(2, pay.getMemberId());

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
	public int updateTel(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "update member set member_tel = ? where member_id =?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberTel());
			pstmt.setInt(2, member.getMemberId());

			result = pstmt.executeUpdate();

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
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

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

// 3-2) 전체 회원 조회 : 회원ID, 이름, 연락처, 강아지 수, 멤버십 금액, 포인트

	public List<Member> allMemberInfo() {
		List<Member> list = new ArrayList<>();
		Member member = null;

		try {
			conn();
			String sql = "select m.member_id member_id, m.member_name member_name, m.member_puppy member_puppy, "
					+ "p.charging, p.point" + " from member m, pay" + " where m.member_id = p.member_id";
			pstmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				member.setMemberPuppy(rs.getInt("member_puppy"));
				member.setCharging(rs.getInt("charing"));
				member.setPoint(rs.getInt("point"));

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

// 5. 포인트 적립 update 
	public int useMemberPoint(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "update member set point = ? where member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getPoint());
			pstmt.setInt(2, member.getMemberId());

			result = stmt.executeUpdate(sql);

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
		return result;

	}

// 6. 수영장 예약현황 select
//    전체 예약 현황 조회 : 회원ID, 이름, 강아지 수, 예약날짜, 이용 시간(타임)

	public List<Join> selectReservation() {
		List<Join> list = new ArrayList<>(); // list
		Join join = null;

		try {
			conn();
			String sql = "select m.member_id member_id, m.member_name member_name, m.member_puppy member_puppy,"
					+ " to_char(r. reservation_date, 'yyyy/mm/dd') reservation_date, r.reservation_time reservation_time "
					+ " from member m, reservation r" + " where m.member_id = r.member_id";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				join = new Join();
				join.setMemberId(rs.getInt("member_id"));
				join.setMemberName(rs.getString("member_name"));
				join.setMemberPuppy(rs.getInt("member_puppy"));

				join.setReservationDate(rs.getString("reservation_date"));
				join.setReservationTime(rs.getString("reservation_time"));

				list.add(join);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//6. 수영장 예약현황 select
// (당일) 타임별 예약 현황 조회 : A타임 | B 타임 | C 타임 => 예약 날짜, id, 이름, 강아지 수, 예약 타임 
	public List<Join> TimeReservation(String date) {
		List<Join> list = new ArrayList<>(); // list
		Join join = null;

		try {
			conn();
			String sql = " select to_char(r. reservation_date, 'yyyy/mm/dd') reservation_date, m.member_id member_id,"
					+ " m.member_name member_name, r.reservation_puppy reservation_puppy, r.reservation_time reservation_time"
					+ " from member m, reservation r" + " where  m.member_id = r.member_id AND reservation_date = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				join = new Join();
				join.setReservationDate(rs.getString("reservation_date"));
				join.setMemberId(rs.getInt("member_id"));
				join.setMemberName(rs.getString("member_name"));
				join.setMemberPuppy(rs.getInt("member_puppy"));
				join.setReservationTime(rs.getString("reservation_time"));

				list.add(join);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

// 7. 멤버십 삭제 delete

	public int deleteMembershp(int memberId) {
		int result = 0;
		try {
			conn();
			String sql = "delete from member where member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);

			result = pstmt.executeUpdate();
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
		return result;

	}

// 8. 일자별 매출 통계

// 일일 매출

// 일일 방문객 수 
	public Reservation dailySales(String date) {

		Reservation reserv = null;

		try {
			conn();
			String sql = "select sum(visitor) visitor, sum(reservation_puppy) puppy" + " from reservation"
					+ " where reservation_date = to_char(SYSDATE, 'yyyy/mm/dd')";
			pstmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				reserv = new Reservation();
				reserv.setReservationDate(date);
			}
		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return reserv;

	}

}
