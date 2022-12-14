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

	AdminDAO() {

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
	public int registMembership(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "insert into member (member_id, member_pw,"
					+ "member_name,member_tel, member_puppy, role, charging, point,start_day) values (?,?,?,?,?,?,?,?, to_char(sysdate, 'yyyy/mm/dd'))";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());

			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberTel());

			pstmt.setInt(5, member.getMemberPuppy());
			pstmt.setString(6, member.getRole());

			pstmt.setInt(7, member.getCharging());
			pstmt.setInt(8, member.getPoint());

			result = pstmt.executeUpdate();

//			if (result == 1) {
//				System.out.println("정상 등록 되었습니다.");
//			} else {
//				System.out.println("등록이 실패하였습니다.");
//			}

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
//
//			if (result == 1) {
//				System.out.println("전화번호 변경 완료.");
//			} else {
//				System.out.println("전화번호 변경 실패.");
//			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}

		return result;
	}

	// 멤버십 조회 select

	public List<Member> searchMember(int memberId) {
		List<Member> list = new ArrayList<>();
		Member member = null;

		try {
			conn();
			String sql = "select member_id, member_name, member_puppy, member_tel, charging, point, start_day"
					+ " from member" + " where member_id= ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				member.setMemberPuppy(rs.getInt("member_puppy"));
				member.setCharging(rs.getInt("charging"));
				member.setPoint(rs.getInt("point"));
				member.setStartDay(rs.getString("start_day"));
				list.add(member);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return list;
	}

// 3-2) 전체 회원 조회 : 회원ID, 이름, 연락처, 강아지 수, 멤버십 금액, 포인트

	public List<Member> allSearchMember() {
		List<Member> list = new ArrayList<>();
		Member member = null;

		try {
			conn();
			String sql = "select member_id, member_name, member_tel, member_puppy, charging, point" + " from member"
					+ " where charging is not null";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				member.setMemberPuppy(rs.getInt("member_puppy"));
				member.setCharging(rs.getInt("charging"));
				member.setPoint(rs.getInt("point"));

				list.add(member);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

// 6. 수영장 예약현황 select
//    전체 예약 현황 조회 : 회원ID, 이름, 강아지 수, 예약날짜, 이용 시간(타임)

	public List<Join> selectReservation(String date) {
		List<Join> list = new ArrayList<>(); // list
		Join join = null;

		try {
			conn();
			String sql = "select m.member_id member_id, m.member_name member_name, m.member_puppy member_puppy,"
					+ " to_char(r. reservation_date, 'yyyy/mm/dd') reservation_date, r.reservation_time reservation_time "
					+ " from member m, reservation r" + " where m.member_id = r.member_id"
					+ " and r.reservation_date = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);

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

	// 포인트 사용
	public int usePoint(int memberId) {
		int result = 0;

		try {
			conn();

			// 금액 사용
			String sql = "update member " + " set charging = point + charging," + " point = point - point"
					+ " where member_id = ?" + " and point>5000";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);

			result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println();
				System.out.println("정상 반영 되었습니다. 감사합니다!");
				System.out.println();
			} else {
				System.out.println();
				System.out.println("아직 5000점이 되지 않았습니다. 다음에 이용해주세요~");
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}

		return result;

	}

//6. 수영장 예약현황 select
//  날짜별 현황 조회 : A타임 | B 타임 | C 타임 => 예약 날짜, id, 이름, 강아지 수, 예약 타임 
	public List<Join> TimeReservation(String date) {
		List<Join> list = new ArrayList<>(); // list
		Join join = null;

		try {
			conn();
			String sql = " select to_char(r. reservation_date, 'yyyy/mm/dd') reservation_date, m.member_id member_id,"
					+ " m.member_name member_name, r.reservation_puppy reservation_puppy, r.reservation_time reservation_time"
					+ " from member m, reservation r" + " where  m.member_id = r.member_id AND r.reservation_date = ?";

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

		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return result;

	}

// 8. 일자별 매출 통계

// 일일 매출

	public Member dailySales(String startDay) {
		Member mem = new Member();

		try {
			conn();
			String sql = "SELECT to_char(start_day, 'yyyy/mm/dd') as daily, sum(charging) as pay" + " from member"
					+ " where start_day is not null" + "	and start_day = ?"
					+ " group by to_char(start_day, 'yyyy/mm/dd')";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDay);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mem = new Member();
				mem.setStartDay(rs.getString("daily"));
				mem.setCharging(rs.getInt("pay"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return mem;
	}

// 일일 방문객 수 
	public Reservation dailyVisitor() {

		Reservation reserv = new Reservation();

		try {
			conn();
			String sql = "select sum(visitor) visitor, sum(reservation_puppy) puppy" + " from reservation"
					+ " where reservation_date = to_char(SYSDATE, 'yyyy/mm/dd')";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				reserv = new Reservation();
				reserv.setVisitor(rs.getInt("visitor"));
				reserv.setReservationPuppy(rs.getInt("puppy"));
			}
		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return reserv;

	}
}

//String sql2 = "select point from pay where member_id =?";
//pstmt = conn.prepareStatement(sql2);
//pstmt.setInt(1, join.getMemberId());
//
//rs = pstmt.executeQuery();
//
//int point = 0;
//if (rs.next()) {			
//	point = rs.getInt("point");
//}
//
//
//	
//	String sql = "update member"
//			+ " set charging = charging - (select (visitor * 6000) + (reservation_puppy * (6000* ?))"
//			+ " from reservation where member_id =?)," 
//			+ " point = point + (select ((visitor * 6000)*0.05) + ((reservation_puppy * (6000* ?)*0.05))"
//			+ " from reservation where member_id =?)"
//			+ " where member_id =?";
//
//	
//}
//public int chargeMembership(Member member) {
//	int result = 0;
//	try {
//		conn();
//		String sql = "update member set charging = ? where member_id =?";
//		pstmt = conn.prepareStatement(sql);
//		pstmt.setInt(1, member.getCharging());
//		pstmt.setInt(2, member.getMemberId());
//
//		result = stmt.executeUpdate(sql);
//
//		if (result == 1) {
//			System.out.println("충전이 완료 되었습니다.");
//		} else {
//			System.out.println("충전 실패.");
//		}
//
//	} catch (Exception e) {
//		e.printStackTrace();
//
//	} finally {
//		disconnect();
//	}
//
//	return result;
//
//}
