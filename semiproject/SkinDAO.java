package semiproject;

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

	public void insertOne(SkinVO VO) {
		sb.setLength(0);
		sb.append("INSERT INTO skin_table ");
		sb.append("VALUES( ?, ?) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, VO.getID());
			pstmt.setString(2, VO.getSkin());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String skinSelect(String id) { // String으로 아이디만 넣어도됨
		String OX = "";
		sb.setLength(0);
		sb.append("SELECT SKIN ");
		sb.append("FROM skin_table ");
		sb.append("WHERE ID = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			rs.next();
			OX = rs.getString("skin").trim();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OX;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
