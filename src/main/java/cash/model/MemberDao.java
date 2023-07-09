package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cash.vo.Member;

public class MemberDao {
	// 회원 상세정보 (memberId 키를 받아 리턴)
	public Member selectMemberOne(String memberId) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_pw memberPw, createdate, updatedate FROM member WHERE member_id=?";
		try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         stmt = conn.prepareStatement(sql);
		     stmt.setString(1,memberId);
		     rs = stmt.executeQuery();
		     
		     Member loginmember = new Member(); // 객체 초기화
		     if (rs.next()) {
		    	 loginmember.setMemberId(rs.getString("memberId"));
		    	 loginmember.setMemberPw(rs.getString("memberPw"));
		    	 loginmember.setCreatedate(rs.getString("createdate"));
		    	 loginmember.setUpdatedate(rs.getString("updatedate"));
		     }
		     return loginmember;
		}
			catch(Exception e1) {
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
			return null;
		}

	
	//회원가입 메서드
	public int insertMember(Member member) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member VALUES(?, PASSWORD(?), NOW(), NOW())";
	     try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, member.getMemberId());
	         stmt.setString(2, member.getMemberPw());
	         row = stmt.executeUpdate();
	         
	      } catch (Exception e1) {
	         e1.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	            conn.close();
	         } catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return row;
	   }
	
	// 아이디 중복 체크
	public int checkId(String memberId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM member WHERE member_id = ?";
		try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, memberId);
	         rs = stmt.executeQuery();
	         if(rs.next()) {
	        	 cnt = rs.getInt(1);
	         }
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
	}

	/*하나의 메서드
	public int insertMember(Member member) {
	    int row = 0;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    String insertSql = "INSERT INTO member VALUES(?, PASSWORD(?), NOW(), NOW())";
	    String checkSql = "SELECT COUNT(*) FROM member WHERE member_id = ?";
	    try {
	        String driver = "org.mariadb.jdbc.Driver";
	        String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	        String dbUser = "root";
	        String dbPw = "java1234";
	        Class.forName(driver);
	        conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);

	        // 아이디 중복 체크
	        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	        checkStmt.setString(1, member.getMemberId());
	        ResultSet checkRs = checkStmt.executeQuery();
	        if (checkRs.next()) {
	            int cnt = checkRs.getInt(1);
	            if (cnt > 0) {
	                System.out.println("중복된 아이디입니다.");
	                return cnt;
	            }
	        }
	        checkRs.close();
	        checkStmt.close();

	        // 회원 가입
	        stmt = conn.prepareStatement(insertSql);
	        stmt.setString(1, member.getMemberId());
	        stmt.setString(2, member.getMemberPw());
	        row = stmt.executeUpdate();

	        if (row > 0) {
	            System.out.println("가입이 성공적으로 이루어졌습니다.");
	        } else {
	            System.out.println("가입에 실패하였습니다.");
	        }

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
	    return row;
	}*/
	
	// 로그인 메서드
	public Member selectMemberById(Member paramMember) { // 회원 조회
		Member returnMember = null; // 정보담을 변수 선언
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw = PASSWORD(?)";
		// try-catch-finally
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) { // 결과 확인 유효한 값이면 returnMember 객체 생성하고 memberId 설정
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
			}		
		} catch(Exception e1) { // 예외 발생시 처리하는 코드
			e1.printStackTrace(); // ㄴ 예외발생 당시의 호출스택에 있던 메서드의 정보와 메세지를 화면에 출력한다
		
		} finally { // 예외 발생 여부와 상관없이 항상 실행되는 변하지 않는 코드
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return returnMember; // 로그인 성공시 객체 반환
	}

	// 회원탈퇴 메서드
	public int removeMember(String memberId, String memberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE from member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		try {
			String driver = "org.mariadb.jdbc.Driver";
	        String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	        String dbUser = "root";
	        String dbPw = "java1234";
	        Class.forName(driver);
	        conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, memberId);
	        stmt.setString(2, memberPw);
	        int row = stmt.executeUpdate();
	        
	        return row; // 성공시 1
	        
		} catch(Exception e1) { // 예외 발생시 처리하는 코드
			e1.printStackTrace(); // ㄴ 예외발생 당시의 호출스택에 있던 메서드의 정보와 메세지를 화면에 출력한다
		
		} finally { // 예외 발생 여부와 상관없이 항상 실행되는 변하지 않는 코드
			try {
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}
	
	// 회원정보 수정 메서드
	public int modifyMember(String memberId,String memberPw,String modiPw) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE member set member_pw = password(?) where member_id = ? and member_pw = password(?)";
		
		try {
			String driver = "org.mariadb.jdbc.Driver";
	        String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	        String dbUser = "root";
	        String dbPw = "java1234";
	        Class.forName(driver);
	        conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, modiPw);
	        stmt.setString(2, memberId);
	        stmt.setString(3, memberPw);
	        int row = stmt.executeUpdate();
	        return row; // 성공시 1
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
		return 0;
	}
}


