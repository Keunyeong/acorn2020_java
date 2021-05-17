package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public BirthDto getData(int num) {
		//회원 한명의 정보를 담을 MemberDto 
		BirthDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//DBConnect 객체를 이용해서 Connection 객체의 참조값을 얻어온다.
			conn = new DBConnect().getConn();
			//실행할 sql 문
			String sql = "SELECT name,birthday" + " FROM birth" + " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			//query 문 수행하고 결과 얻어오기
			rs = pstmt.executeQuery();
			//반복문 돌면서 select 된 회원정보  읽어오기
			while (rs.next()) {
				//MemberDto 객체 생성해서 
				dto = new BirthDto();
				//회원 한명의 정보를 담는다. 
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getString("birthday"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//객체를 사용했던 순서 역순으로 닫아준다.
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
	public List<BirthDto> getList() {
		//회원 목록을 담을 객체 생성
		List<BirthDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//DBConnect 객체를 이용해서 Connection 객체의 참조값을 얻어온다.
			conn = new DBConnect().getConn();
			//실행할 sql 문
			String sql = "SELECT num,name,birthday" + " FROM birth" + " ORDER BY num ASC";
			pstmt = conn.prepareStatement(sql);
			//query 문 수행하고 결과 얻어오기
			rs = pstmt.executeQuery();
			//반복문 돌면서 select 된 회원정보  읽어오기
			while (rs.next()) {
				//회원정보를 list 에 담아 보세요.
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String birthday = rs.getString("birthday");
				//MemberDto 객체를 생성해서 회원 한명의 정보를 담는다.
				BirthDto dto = new BirthDto();
				dto.setNum(num);
				dto.setName(name);
				dto.setBirthday(birthday);
				//MemberDto 객체를 List 에 누적 시킨다.
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//객체를 사용했던 순서 역순으로 닫아준다.
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
	public boolean update(BirthDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "update memo"
					+ " set name = ?,birthday = ?"
					+ " where num = ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirthday());
			pstmt.setInt(3, dto.getNum());
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
			String sql = "delete from birth"
					+ " where num = ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);
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
