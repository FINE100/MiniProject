package com.playground.common;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;

	public class DAO { // JDBC 연결 역할

		
		// java <-> DB 연결할 때 쓰는 객체
		protected Connection conn= null;
		
		// Select(조회) 결과 값 반환 받는 객체 
		protected ResultSet rs = null;    
		
		
		//Query문을 담고 Query문 실행하는 객체, DML (insert, update, delete) 사용시 편함
		protected PreparedStatement pstmt = null;
		
		
		//Query문을 담고 Query문 실행하는 객체, select 할 때 편함
		protected Statement stmt = null;
		
		
		
	    // 접속에 필요한 정보로 구성된 문자열, Connection String
	    
		String driver = "oracle.jdbc.driver.OracleDriver"; // 포트명 
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속주소, local host 대신 ip 주소가 들어갈수도 있음 (@127.0.0.1)
	    String id = "project"; //DB id
	    String pw = "1234"; //DB pw
	    
	    public void conn() {
	    	try {
	    		// 1. 드라이버 로딩
				Class.forName(driver);
				// 2. DB 연결
				conn = DriverManager.getConnection(url, id, pw);
			} catch (Exception e) { //Exception 넣으면 모든 예외 처리 다 가능함.
				e.printStackTrace();				
			}
	    }
	    		// 3. DB 닫기
	    public void disconnect() {
	    	try {
	    		if(rs != null) {
	    			rs.close();    			
	    		} 
	    		if(stmt != null) {
	    			stmt.close();   
	    		}
	    		if(pstmt!= null) {
	    			pstmt.close();   
	    		}
	    		if(conn!= null) {
	    			conn.close();   
	    		}
	    	}catch(Exception e) {	
	    		e.printStackTrace();
	    	}
	    }
		

	}





