package com.toiukha.groupactivity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.toiukha.groupactivity.entity.GroupActivityVO;
import util.JDBCUtil;


public class GroupActivityDAOimpl implements GroupActivityDAO{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	//資料庫指定操作
		private static final String INSERT_STMT = "INSERT INTO groupactivity (ACTNAME, ACTDESC, IMGPATH, ITNID, HOSTID, SIGNUPSTART, SIGNUPEND, MAXCAP, SIGNUPCNT, ACTSTART, ACTEND, ISPUBLIC, ALLOWCANCEL, RECRUITSTATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String UPDATE_STMT = "UPDATE groupactivity SET ACTNAME = ?, ACTDESC = ?, IMGPATH = ?, ITNID = ?, HOSTID = ?, SIGNUPSTART = ?, SIGNUPEND = ?, MAXCAP = ?, SIGNUPCNT = ?, ACTSTART = ?,	ACTEND = ?, ISPUBLIC = ?, ALLOWCANCEL = ?, RECRUITSTATUS = ?  WHERE ACTID = ?";
		private static final String DELET_STMT = "DELETE FROM groupactivity WHERE ACTID = ?";
		private static final String GET_BY_PK = "SELECT * FROM groupactivity WHERE ACTID = ?";
		private static final String GET_ALL = "SELECT * FROM groupactivity";
		
		static {
			try {
				Class.forName(JDBCUtil.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public int add(GroupActivityVO groupactVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
//				pstmt.setInt(1, groupactVO.getActId());//有設置自增主鍵
				pstmt.setString(1, groupactVO.getActName());
				pstmt.setString(2, groupactVO.getActDesc());
				pstmt.setString(3, groupactVO.getImgPath());
				pstmt.setInt(4, groupactVO.getItnId());
				pstmt.setInt(5, groupactVO.getHostId());
				pstmt.setTimestamp(6, groupactVO.getSignupStart());
				pstmt.setTimestamp(7, groupactVO.getSignupEnd());
				pstmt.setInt(8, groupactVO.getMaxCap());
				pstmt.setInt(9, groupactVO.getSignupCnt());
				pstmt.setTimestamp(10, groupactVO.getActStart());
				pstmt.setTimestamp(11, groupactVO.getActEnd());
				pstmt.setByte(12, groupactVO.getIsPublic());
				pstmt.setByte(13, groupactVO.getAllowCancel());
				pstmt.setByte(14, groupactVO.getRecruitStatus());
				
				return pstmt.executeUpdate();//印出更新筆數
				
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}finally {
				closeResources(con, pstmt, null);	
			}
			return -1;//如果有例外就印出-1
		}
			
			
		@Override
		public int update(GroupActivityVO groupactVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				pstmt.setString(1, groupactVO.getActName());
				pstmt.setString(2, groupactVO.getActDesc());
				pstmt.setString(3, groupactVO.getImgPath());
				pstmt.setInt(4, groupactVO.getItnId());
				pstmt.setInt(5, groupactVO.getHostId());
				pstmt.setTimestamp(6, groupactVO.getSignupStart());
				pstmt.setTimestamp(7, groupactVO.getSignupEnd());
				pstmt.setInt(8, groupactVO.getMaxCap());
				pstmt.setInt(9, groupactVO.getSignupCnt());
				pstmt.setTimestamp(10, groupactVO.getActStart());
				pstmt.setTimestamp(11, groupactVO.getActEnd());
				pstmt.setByte(12, groupactVO.getIsPublic());
				pstmt.setByte(13, groupactVO.getAllowCancel());
				pstmt.setByte(14, groupactVO.getRecruitStatus());
				
				pstmt.setInt(15, groupactVO.getActId());//查詢條件
				
				return pstmt.executeUpdate();//印出更新筆數
				
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}finally {
				closeResources(con, pstmt, null);	
			}
			return -1;//如果有例外就印出-1
		}
		
		@Override
		public int delete(Integer actId) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELET_STMT);
				
				pstmt.setInt(1, actId);
				
				return pstmt.executeUpdate();//印出更新筆數
				
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}finally {
				closeResources(con, pstmt, null);	
			}
			return -1;//如果有例外就印出-1
		}
		
		@Override
		public GroupActivityVO getByPK(Integer actId) {
			GroupActivityVO act = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_BY_PK);
				pstmt.setInt(1, actId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					act = new GroupActivityVO();
					act.setActId(rs.getInt("ACTID"));
					act.setActName(rs.getString("ACTNAME"));
					act.setActDesc(rs.getString("ACTDESC"));
					act.setImgPath(rs.getString("IMGPATH"));
					act.setItnId(rs.getInt("ITNID"));
					act.setHostId(rs.getInt("HOSTID"));
					act.setSignupStart(rs.getTimestamp("SIGNUPSTART"));
					act.setSignupEnd(rs.getTimestamp("SIGNUPEND"));
					act.setMaxCap(rs.getInt("MAXCAP"));
					act.setSignupCnt(rs.getInt("SIGNUPCNT"));
					act.setActStart(rs.getTimestamp("ACTSTART"));
					act.setActEnd(rs.getTimestamp("ACTEND"));
					act.setIsPublic(rs.getByte("ISPUBLIC"));
					act.setAllowCancel(rs.getByte("ALLOWCANCEL"));
					act.setRecruitStatus(rs.getByte("RECRUITSTATUS"));
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			}finally {
				closeResources(con, pstmt, rs);	
			}
			return act;
		}
		
		
		@Override
		public List<GroupActivityVO> getAll() {
			List<GroupActivityVO> actList = new ArrayList<>();
			GroupActivityVO act = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					act = new GroupActivityVO();
					act.setActId(rs.getInt("ACTID"));
					act.setActName(rs.getString("ACTNAME"));
					act.setActDesc(rs.getString("ACTDESC"));
					act.setImgPath(rs.getString("IMGPATH"));
					act.setItnId(rs.getInt("ITNID"));
					act.setHostId(rs.getInt("HOSTID"));
					act.setSignupStart(rs.getTimestamp("SIGNUPSTART"));
					act.setSignupEnd(rs.getTimestamp("SIGNUPEND"));
					act.setMaxCap(rs.getInt("MAXCAP"));
					act.setSignupCnt(rs.getInt("SIGNUPCNT"));
					act.setActStart(rs.getTimestamp("ACTSTART"));
					act.setActEnd(rs.getTimestamp("ACTEND"));
					act.setIsPublic(rs.getByte("ISPUBLIC"));
					act.setAllowCancel(rs.getByte("ALLOWCANCEL"));
					act.setRecruitStatus(rs.getByte("RECRUITSTATUS"));
					
					actList.add(act);
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			}finally {
				closeResources(con, pstmt, rs);	
			}
			return actList;
		}
		
		
		private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
				
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	
}
