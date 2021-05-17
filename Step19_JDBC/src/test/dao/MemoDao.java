package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemoDto;
import test.util.DBConnect;

/*
 *   DAO (Data Access Object)  의 약자
 *   
 *   - 만드는 방법
 *   
 *   1. 외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private 로 지정
 *   2. 자신의 참조값을 저장할수 있는 필드를 private static 로 선언
 *   3. 자신의 참조값을 오직 하나만 생성해서 리턴해주는 static 메소드 만들기 
 *   4. 나머지 기능(select,insert,update,delete)들은 non static 으로 만들기
 *   
 */
public class MemoDao {
	private static MemoDao dao;
	private MemoDao(){};
	public static MemoDao getInstance() {
		if (dao == null) {
			dao = new MemoDao();
		}
		return dao;
	}
	public MemoDto getData(int num) {
		MemoDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql 문
			String sql = "select content,regdate"
					+ " from memo"
					+ " where num = ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용 작성
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 추출한 db 작성
				dto=new MemoDto();
				dto.setNum(num);
				dto.setContent("content");
				dto.setRegdate("regdate");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}
	public List<MemoDto> getList() {
		List<MemoDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql 문
			String sql = "select num,content,regdate"
					+ " from memo"
					+ " order by num asc";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용 작성
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 추출한 db 작성
				int num = rs.getInt("num");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				MemoDto dto = new MemoDto();
				dto.setNum(num);
				dto.setContent(content);
				dto.setRegdate(regdate);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "delete from memo"
					+ " where num = ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1,num);
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
	public boolean update(MemoDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "update memo"
					+ " set content = ?"
					+ " where num = ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getNum());
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
	public boolean insert(MemoDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "insert into memo"
					+ " (num,content,regdate)"
					+ " values(memo_seq.nextval,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, dto.getContent());
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
	public boolean write(String memo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "insert into memo"
					+ " (num,content,regdate)"
					+ " values(memo_seq.nextval,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, memo);
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






