package com.playground.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.playground.admin.AdminDAO;
import com.playground.admin.AdminService;
import com.playground.common.DAO;
import com.playground.common.Join;
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
	
	// 싱글톤

		private static MemberDAO md = new MemberDAO();

		MemberDAO() {

		}

		public static MemberDAO getInstance() {
			return md;
		}

		// 3-1) 단건 회원 조회 

		public List<Member> searchMember(int memberId) {
			 List<Member>list = new ArrayList<>();
			Member member = null;

			try {
				conn();
				String sql = "select m.member_id id, m.member_name name, m.member_puppy puppy," 
						+"m.member_tel tel, p.charging charging, p.point point"
						+" from member m, pay p"
						+" where m.member_id = p.member_id" 
						+" And m.member_id= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memberId);
				
				rs = pstmt.executeQuery();

				while(rs.next()) {
					member = new Member();
					member.setMemberId(rs.getInt("id"));
					member.setMemberName(rs.getString("name"));
					member.setMemberTel(rs.getString("tel"));
					member.setMemberPuppy(rs.getInt("puppy"));
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
	
	
	
	
//	
//	// 4. 멤버십 사용(사용과 동시에 포인트 자동 적립)
//
//	public int useMembership(Member member) {
//		int result = 0;
//		try {
//			conn();
//			
//			String sql = "update pay"
//					+ " set charging = charging - (select (visitor * 6000) + (reservation_puppy * 6000)"
//                    + " from reservation where member_id =0)," 
//                    + " point = point + (select ((visitor * 6000)*0.05) + ((reservation_puppy * 6000)*0.05)"
//                    + " from reservation where member_id =0)"
//                    + " where member_id =?";
//					
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, member.getMemberId());
//			
//
//			result = pstmt.executeUpdate(sql);
//			
//
//		} catch (Exception e) {
//
//		} finally {
//			disconnect();
//		}
//		return result;
//
//	}
	
	// 멤버십 금액 사용  

	public int useMembership(Join join) {
		
		int result = 0;
		try {
			conn();
		
			String sql2 = "select point from pay where member_id =?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, join.getMemberId());

			rs = pstmt.executeQuery();

			int point = 0;
			if (rs.next()) {			
				point = rs.getInt("point");
			}
			
			if(point > 5000) {
				
				String sql = "update member"
						+ " set charging = charging - (select (visitor * 6000) + (reservation_puppy * (6000* ?))"
						+ " from reservation where member_id =?)," 
						+ " point = point + (select ((visitor * 6000)*0.05) + ((reservation_puppy * (6000* ?)*0.05))"
						+ " from reservation where member_id =?)"
						+ " where member_id =?";
			
				
			}
			
			 
			
			// 금액 사용
			String sql = "update member"
						+ " set charging = charging - (select (visitor * 6000) + (reservation_puppy * (6000* ?))"
						+ " from reservation where member_id =?)," 
						+ " point = point + (select ((visitor * 6000)*0.05) + ((reservation_puppy * (6000* ?)*0.05))"
						+ " from reservation where member_id =?)"
						+ " where member_id =?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0);				
				pstmt.setInt(2, join.getMemberId());
				pstmt.setInt(3, 0);
				pstmt.setInt(4, join.getMemberId());
				pstmt.setInt(5, join.getMemberId());
				
				result = pstmt.executeUpdate();
				
				System.out.println("정상 처리 되었습니다.");
				if(result == 1) {
			} else { 
				System.out.println("처리 오류입니다. 다시 시도해주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 5. 수영장 예약 
	public int reserveSwim(Reservation reserv) {
		int result = 0;
		try {
			conn();
			String sql = "insert into reservation "
					+ " values (?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reserv.getMemberId());
			pstmt.setString(2, reserv.getReservationDate());
			pstmt.setInt(3, reserv.getReservationPuppy());
			pstmt.setString(4, reserv.getReservationTime());
			pstmt.setInt(5, reserv.getVisitor());
			pstmt.setInt(5, reserv.getUse());

						
			
			result = pstmt.executeUpdate();
			
		

		} catch (Exception e) {

		} finally {
			disconnect();
		}
		return result;

	}

	

}
