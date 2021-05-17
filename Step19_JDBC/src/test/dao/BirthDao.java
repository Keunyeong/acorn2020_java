package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import test.dto.BirthDto;
import test.util.DBConnect;

public class BirthDao {
	private static BirthDao dao;
	private BirthDao () {};
	public static BirthDao getInstance() {
		if(dao == null) {
			dao = new BirthDao();
		}
		return dao;
	}
	public boolean insert(BirthDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "insert into birth"
					+ " (num,name,birthday)"
					+ " values(birth_seq.nextval,?,?)   ";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirthday());
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "delete from   ";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.

			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}
}
