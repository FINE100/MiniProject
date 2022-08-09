package com.playground.admin;


public class Admin {
	
/*ID 		number			primary key 
  PW     varchar2(20)
  ROLE
 */
	
	private int adminId;	  // 관리자id
	private String adminpPw; // 관리자pw
	private String role;	 // 롤 = 관리자 default :0
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
}
