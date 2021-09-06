package semiproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberVO;

public class SkinDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@mydb.cwnyvgf2watg.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	String user = "scott";
	String password = "tigertiger1";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	
	SkinDAO() {
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
	
	
	public void updateOne(SkinVO VO) {
		sb.setLength(0);
		sb.append("UPDATE skin_table ");
		sb.append("SET skin = ? ");
		sb.append("WHERE ID = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, VO.getSkin());
			pstmt.setString(2, VO.getID());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public void skinSelect(SkinVO VO) { // String으로 아이디만 넣어도됨
		sb.setLength(0);
		sb.append("SELECT skin_table ");
		sb.append("FROM ID = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, VO.getID());
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
