package com.playground.member;

import java.sql.SQLException;

import com.playground.common.DAO;
import com.playground.reservation.Reservation;
import com.prlayground.pay.Pay;

public class MemberDAO extends DAO {

	// | 1. 멤버십 사용 | 2. 포인트 조회 | 3. 수영장 예약 |

//	//1.멤버십 충전 (결제)
//	public int chargeMembership(Member member) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "  ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, member.getMemberCharging());
//			pstmt.setInt(2, member.getMemberId());
//
//			result = stmt.executeUpdate(sql);
//
//			if (result == 1) {
//				System.out.println("충전이 완료 되었습니다.");
//			} else {
//				System.out.println("충전 실패.");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			disconnect();
//		}
//
//		return result;
//	}
//}
	// 4. 멤버십 사용, 결제와 포인트 사용 부분 넣어야함

	public int useMembership(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "update pay "
					+ " set charging = charging - (select  (visitor * 6000) + (reservation_puppy * 6000)"
					+ " from reservation" + " where member_id =?)" + " where member_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberId());

			result = pstmt.executeUpdate(sql);

		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return result;

	}
	
	// 멤버십 사용  

	public int updateMoney(Pay pay, int use) {
		int result = 0;
		try {
			conn();
		
			String sql2 = "select charging from pay where member_id =?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, pay.getMemberId());

			rs = pstmt.executeQuery();

			int charging = 0;
			if (rs.next()) {
				charging = rs.getInt("charging");
			}
			// 예약 구분
			// 운동장 = 1, 운동장 + 수영장 = 2 구분
			String sql = "update pay "
					+ " set charging = charging - (select  (visitor * 6000) + (reservation_puppy * "+ (6000*use)
					+ ") from reservation" + " where member_id =?)" + " where member_id =?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pay.getMemberId());
				result = pstmt.executeUpdate();
				System.out.println("정상 차감 되었습니다.");
				if(result == 1) {
			} else { 
				System.out.println("차감 오류입니다. 다시 시도해주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 5. 수영장 예약 update
	public int useMemberPoint(Reservation reserv) {
		int result = 0;
		try {
			conn();
			String sql = "update reservation "
					+ " set reservation_date = ? , reservation_puppy = ? , reservation_time = ? , visitor = ?, use = ?"
					+ " where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserv.getReservationDate());
			pstmt.setInt(2, reserv.getReservationPuppy());
			pstmt.setString(3, reserv.getReservationTime());
			pstmt.setInt(4, reserv.getVisitor());
			pstmt.setInt(5, reserv.getUse());

						
			
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return result;

	}

}
