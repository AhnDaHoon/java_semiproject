package semiproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.EmpVO;

public class ProblemDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@mydb.cwnyvgf2watg.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	String user = "scott";
	String password = "tigertiger1";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public ProblemDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("db 연결 실패");
			e.printStackTrace();
		}
		
		
	}
	
	
	public void problemInsert (ProblemVO vo ) {
		sb.append("INSERT INTO MEMBER ");
		sb.append("VALUES (PROBLEM_TABLE_SEQUENCE, ?, ?, ? ) ");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getProblem());
			pstmt.setString(2, vo.getAnswer());
			pstmt.setString(3, vo.getExplanations());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public ProblemVO problem (int rnd) {
		sb.setLength(0);
		ProblemVO vo = null;
		sb.append("SELECT * ");
		sb.append("FROM problem_table ");
		sb.append("WHERE problem_no = ? "); 

		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, rnd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String priblem = rs.getString("problem");
				String answer = rs.getString("answer");
				String explanations = rs.getString("explanations");
				vo = new ProblemVO(priblem, answer, explanations);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return vo;
	}
	
	
	
	
	
	
	
}
