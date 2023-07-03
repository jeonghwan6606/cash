package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cash.vo.Member;

public class MemberDao {
	public int insertMember(Member member) throws Exception {
			int addMemberRow= 0;
		
		
			Connection conn = null;
			PreparedStatement stmt = null;
			PreparedStatement addStmt = null;
			ResultSet rs = null;
			ResultSet addRs = null;
			//아이디 중복확인
			String sql ="Select member_id from member Where member_id=?";	
			
			String insertIdSql = "INSERT INTO member (member_id, member_pw, updatedate, createdate) VALUES (?, Password(?), NOW(), NOW())";
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, member.getMemberId());
				rs = stmt.executeQuery();
				if(rs.next()) {
					addMemberRow=0;
				}else {	
					addStmt = conn.prepareStatement(insertIdSql);
					addStmt.setString(1, member.getMemberId());
					addStmt.setString(2, member.getMemberPw());
					addMemberRow = addStmt.executeUpdate();
				}			
		
			} catch(Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			
			return addMemberRow;
		}
			
	
	public Member selectMemberById(Member paramMember) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw = PASSWORD(?)";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
			}		
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return returnMember;
	}
	
	//회원 상세정보
	public Member selectMemberOne(String memberId) {
		Member returnMember = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id, member_pw, createdate FROM member WHERE member_id=?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
				returnMember.setCreatedate(rs.getString("createdate"));
			}		
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return returnMember;
		
	}
	
	//회원탈퇴
	public int removeMember(Member member) throws Exception {
		int removeMemberRow= 0;
	
	
		Connection conn = null;
		PreparedStatement stmt = null;
		//아이디 중복확인
		String sql ="Delete from member where member_id=? and member_pw=PASSWORD(?)";	
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			removeMemberRow = stmt.executeUpdate();
				
	
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return removeMemberRow;
	}
	
	public int updateMember(String memberId, String newPassword, String memberPwCk) throws Exception {
	    int updateRow = 0;

	    Connection conn = null;
	    PreparedStatement stmt = null;

	    String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id=? and Where member_pw=PASSWORD(?)";

	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, newPassword);
	        stmt.setString(2, memberId);
	        stmt.setString(3, memberPwCk);
	        updateRow = stmt.executeUpdate();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    } finally {
	        try {
	            stmt.close();
	            conn.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }

	    return updateRow;
	}
}
