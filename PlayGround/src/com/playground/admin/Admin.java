package com.playground.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
	
/*ID 		number			primary key 
  PW     varchar2(20)
  ROLE
 */
	
	private int adminId;	  // 관리자id
	private String adminpPw; // 관리자pw
	private String role;	 // 롤 = 관리자 default :0 

}
