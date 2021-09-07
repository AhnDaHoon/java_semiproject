package semiproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;

public class RankDAO {
	// 1.변수 선언
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@mydb.cwnyvgf2watg.ap-northeast-2.rds.amazonaws.com:1521:ORCL";
	String user = "scott";
	String password = "tigertiger1";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public RankDAO() {
		// 2.JDBC 드라이버 로딩되어 있는지 여부 체크
		try {
			Class.forName(driver);
			// 3.연결
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn : " + conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<RankVO> selectAll() {

		sb.setLength(0);
		sb.append("SELECT * ");
		sb.append("FROM member_rank ");
		sb.append("ORDER BY score DESC ");
		RankVO vo = null;
		ArrayList<RankVO> list = new ArrayList<RankVO>();

		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			for (int i = 0; i < 5; i++) {
				rs.next();
				String nickname = rs.getString("nickname");
				int score = rs.getInt("score");
				vo = new RankVO(nickname, score);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public int selectScore(String nickname) {
		sb.setLength(0);

		sb.append("SELECT score ");
		sb.append("FROM member_rank ");
		sb.append("WHERE nickname = ? ");

		int score = 0;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, nickname);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				score = rs.getInt("score");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score;
	}

	public void updateScore(int score, String nickname) {
		sb.setLength(0);

		sb.append("UPDATE member_rank ");
		sb.append("SET ");
		sb.append("score = ? ");
		sb.append("WHERE nickname = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, score);
			pstmt.setString(2, nickname);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertOne(RankVO vo) {
		sb.setLength(0);

		sb.append("INSERT INTO member_rank ");
		sb.append("VALUES ( ? , ?) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getNickname());
			pstmt.setInt(2, vo.getScore());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
