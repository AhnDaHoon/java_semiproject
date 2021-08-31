package semiproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProblemNumberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@mydb.cwnyvgf2watg.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	String user = "scott";
	String password = "tigertiger1";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public ProblemNumberDAO() {
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
	
	
	public ProblemNumberVO problem (int rnd) {
		sb.setLength(0);
		ProblemNumberVO vo = null;
		sb.append("SELECT * ");
		sb.append("FROM problem_table_number ");
		sb.append("WHERE problem_no = ? "); 

		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, rnd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String numone = rs.getString("numone");
				String numtwo = rs.getString("numtwo");
				String numthree = rs.getString("numthree");
				String numfour = rs.getString("numfour");
				int answerNo = rs.getInt("answer_No");
				vo = new ProblemNumberVO(numone, numtwo, numthree, numfour, answerNo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		return vo;
	}
	
	
}
