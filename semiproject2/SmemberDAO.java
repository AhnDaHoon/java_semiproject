package semiproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semiproject2.SmemberVO;

public class SmemberDAO {
	// 1.변수 선언
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@mydb.cwnyvgf2watg.ap-northeast-2.rds.amazonaws.com:1521:ORCL";
	String user = "scott";
	String password = "tigertiger1";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public SmemberDAO() {
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

	public SmemberVO selectOne(String no) {

		sb.setLength(0);
		sb.append("SELECT id , pw , name , email , no , nickname ");
		sb.append("FROM smember ");
		sb.append("WHERE no = ? ");
		SmemberVO vo = null;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String nO = rs.getString("no");
				String nickname = rs.getString("nickname");
				vo = new SmemberVO(id, pw, name, email, no, nickname);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vo;
	}

	public void insertOne(SmemberVO vo) {
		sb.setLength(0);

		sb.append("INSERT INTO SMEMBER ");
		sb.append("VALUES ( ? , ? , ? , ? , ? , ? ) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getNo());
			pstmt.setString(6, vo.getNickname());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 중복 확인 id
	public boolean isExists(String id) {
		sb.setLength(0);
		sb.append("SELECT * ");
		sb.append("FROM SMEMBER ");
		sb.append(" WHERE ID = ? ");
		boolean flag = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// 중복 확인 id
	public boolean isExists(String id, String pw) {
		sb.setLength(0);
		sb.append("SELECT * ");
		sb.append("FROM SMEMBER ");
		sb.append(" WHERE ID = ? AND PW = ? ");
		boolean flag = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// 이메일 중복확인
	public boolean isExists2(String email) {
		sb.setLength(0);
		sb.append("SELECT * ");
		sb.append("FROM SMEMBER ");
		sb.append(" WHERE EMAIL = ? ");
		boolean flag2 = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			flag2 = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag2;
	}

	// 닉네임 중복확인
	public boolean isExists3(String nickname) {
		sb.setLength(0);
		sb.append("SELECT * ");
		sb.append("FROM SMEMBER ");
		sb.append(" WHERE NICKNAME = ? ");
		boolean flag3 = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, nickname);

			rs = pstmt.executeQuery();
			flag3 = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag3;
	}

	// 보안코드 확인
	public boolean isExists4(String no) {
		sb.setLength(0);
		sb.append("SELECT * ");
		sb.append("FROM SMEMBER ");
		sb.append(" WHERE no = ? ");
		boolean flag3 = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();
			flag3 = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag3;
	}

	// 닉네임 가져오기
	public String getNickname(String id) {
		sb.setLength(0);
		sb.append("SELECT NICKNAME ");
		sb.append("FROM SMEMBER ");
		sb.append("WHERE id = ? ");
		String nickname = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if(rs.next()){
                nickname = rs.getString(1);
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nickname;
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
