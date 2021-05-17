package test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import test.dto.EplDto;
import test.util.DBConnect;

public class EplDao {
	private static EplDao dao;
	private EplDao() {};
	public static EplDao getInstance() {
		if (dao == null) {
			dao = new EplDao();
		}
		return dao;
	}
	public EplDto getData(int rank) {
		EplDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql 문
			String sql = "SELECT team,point" 
					+ " FROM epl" 
					+ " WHERE rank=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rank);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new EplDto();
				dto.setRank(rank);
				dto.setTeam(rs.getString("team"));
				dto.setPoint(rs.getInt("point"));
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
	public List<EplDto> getList() {
		List<EplDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DBConnect().getConn();
			String sql = "SELECT rank,team,point" + " FROM epl" + " ORDER BY rank ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rank = rs.getInt("rank");
				String team = rs.getString("team");
				int point = rs.getInt("point");
				EplDto dto = new EplDto();
				dto.setRank(rank);
				dto.setTeam(team);
				dto.setPoint(point);
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
	public boolean insert(EplDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "insert into epl" + " (rank,team,point)" + " values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, dto.getRank());
			pstmt.setString(2, dto.getTeam());
			pstmt.setInt(3, dto.getPoint());
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
	public boolean update(EplDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "update epl" + " set team=?,point=?" + " where rank = ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, dto.getTeam());
			pstmt.setInt(2, dto.getPoint());
			pstmt.setInt(3, dto.getRank());
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
	public boolean delete(int rank) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			//실행할 sql(Insert or Update or Delete) 문 작성
			String sql = "delete from epl"
					+ " where rank = ? ";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, rank);
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
