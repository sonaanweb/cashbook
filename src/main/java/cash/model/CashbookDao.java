package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cash.vo.Cashbook;

public class CashbookDao {
	
	// 수입 지출 입력 메서드 (반환값:cashbook PK값 = cashbook_no)
	public int insertCashbook(Cashbook cashbook) {
        int cashbookNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력 후 생성된 키값을 반환하기 위해 필요함
		try {
			 String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         String sql="INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate)" 
	        		 	+ " VALUES(?,?,?,?,?,NOW(),NOW())";
	         stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	         stmt.setString(1, cashbook.getMemberId());
	         stmt.setString(2, cashbook.getCategory());
	         stmt.setString(3, cashbook.getCashbookDate());
	         stmt.setInt(4, cashbook.getPrice());
	         stmt.setString(5, cashbook.getMemo());
	         stmt.setString(6, cashbook.getUpdatedate());
	         stmt.setString(7, cashbook.getCreatedate());
	         stmt.executeUpdate();
	         rs = stmt.getGeneratedKeys();
	         if(rs.next()) {
	        	 cashbookNo = rs.getInt(1);
	         }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
		return cashbookNo;
	}
	
	
	// 해시태그 리스트 + 페이징 메서드
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage){
		List<Cashbook> list = new ArrayList<>();
		// cash table 요소 + hashtag
		String sql="SELECT c.* FROM cashbook c INNER JOIN hashtag h"
					+" ON c.cashbook_no = h.cashbook_no"
					+" WHERE c.member_id = ? AND h.word = ?"
					+" ORDER BY c.cashbook_date DESC"
					+" LIMIT ?,?";
		
		return list;
	}
	
	
	// 달력 출력 메서드
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth){
		
		List<Cashbook> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="SELECT cashbook_no cashbookNo, member_id memberId, category, price, cashbook_date cashbookDate"
					+ " FROM cashbook"
					+ " WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ?"
					+ " ORDER BY cashbook_date ASC";
		try {
			 String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1,memberId);
	         stmt.setInt(2,targetYear);
	         stmt.setInt(3,targetMonth);
	         System.out.println(stmt);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	        	 Cashbook c = new Cashbook();
	        	 c.setCashbookNo(rs.getInt("cashbookNo"));
	        	 c.setCategory(rs.getString("category"));
	        	 c.setPrice(rs.getInt("price"));
	        	 c.setCashbookDate(rs.getString("cashbookDate"));
	        	 list.add(c);
	         }
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	// 해당 월일 데이터 출력 메서드
	public List<Cashbook> selectCashbookListByDate (String memberId, int targetYear, int targetMonth, int targetDate) {
		List<Cashbook> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="SELECT cashbook_no cashbookNo, member_id memberId, category, price, cashbook_date cashbookDate, memo, createdate, updatedate"
					+" FROM cashbook"
					+" WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?"
					+" ORDER BY cashbook_date ASC";
			try {
				 String driver = "org.mariadb.jdbc.Driver";
		         String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
		         String dbUser = "root";
		         String dbPw = "java1234";
		         Class.forName(driver);
		         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1,memberId);
		         stmt.setInt(2,targetYear);
		         stmt.setInt(3,targetMonth);
		         stmt.setInt(4,targetDate);
		         System.out.println(stmt);
		         rs = stmt.executeQuery();
		         while(rs.next()) {
		        	 Cashbook c = new Cashbook();
		        	 c.setCashbookNo(rs.getInt("cashbookNo"));
		        	 c.setMemberId(rs.getString("memberId"));
		        	 c.setCategory(rs.getString("category"));
		        	 c.setPrice(rs.getInt("price"));
		        	 c.setMemo(rs.getString("memo"));
		        	 c.setCashbookDate(rs.getString("cashbookDate"));
		        	 c.setCreatedate(rs.getString("createdate"));
		        	 c.setUpdatedate(rs.getString("updatedate"));
		        	 list.add(c);
		         }
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			return list;			
	}
	
	// 데이터 삭제 메서드
	public int deleteCashbook(int cashbookNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql="DELETE from cashbook WHERE cashbook_no = ?";
		try {
			String driver = "org.mariadb.jdbc.Driver";
	        String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
	        String dbUser = "root";
	        String dbPw = "java1234";
	        Class.forName(driver);
	        conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, cashbookNo);
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
}
