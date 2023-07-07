package cash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {
	
	// 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null){
			// 로그인이 되지 않은 상태면 login.jsp로 이동한다
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}	
		
		// request 매개값 분석
		int targetYear = Integer.parseInt(request.getParameter("targetYear")); // 폼에서 바로 넘어옴
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		System.out.println(targetYear+"<--addcashbook targetYear");
		System.out.println(targetMonth+"<--addcashbook targetMonth");
		System.out.println(targetDate+"<--addcashbook targetDate");

	    request.setAttribute("targetYear", targetYear);
	    request.setAttribute("targetMonth", targetMonth);
	    request.setAttribute("targetDate", targetDate);
		
		// jsp페이지로 포워드
		// 나머지 데이터는 입력폼에서 사용자에게 받도록 함
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
	}
	
	// 입력액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("utf-8"); //요청에서 전달되는 데이터 문자 인코딩 - 설정 안 했을 때 오류 -> 필터적용
		
		// session 유효성 검사
		HttpSession session = request.getSession();
	    Member loginMember = (Member)session.getAttribute("loginMember"); // Member 클래스 객체 loginMember 불러오기
		if(session.getAttribute("loginMember") == null){
			// 로그인이 되지 않은 상태면 login.jsp로 이동한다
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String memberId = loginMember.getMemberId(); // loginMember 객체에서 아이디 반환 memberId에 저장(로그인 사용자)
		System.out.println(memberId+"<-- add cashbook memberId");
		
		// request 매개값으로 채워줄 것
		// reuqest.getParameter()
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		int price = Integer.parseInt(request.getParameter("price"));
		String category = request.getParameter("category");
		String memo = request.getParameter("memo");
		String cashbookDate = targetYear + "-" + (targetMonth) + "-" + targetDate;
		
		System.out.println(targetYear);
		System.out.println(targetMonth);
		System.out.println(targetDate);
		System.out.println(memo);
		System.out.println(cashbookDate);
		System.out.println(category);
		System.out.println(price);
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setCategory(category);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		// 입력 DAO 호출
		CashbookDao cashbookDao = new CashbookDao();
		int cashbookNo = cashbookDao.insertCashbook(cashbook); // 키값 반환 int
		if(cashbookNo == 0) { // 0일시 입력 실패
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/addCashbook");
			return; // 끝냄
		} 
		
			// 입력 성공시 해시태그가 있다면 해시태그를 추출하고, 추출 후 해시태그를 입력 (반복문) -- insertHashtag
			// 해시태그 추출 알고리즘
			// #구디 #자바
			HashtagDao hashtagDao = new HashtagDao();
			String memo2 = memo.replace("#", " #"); // "#구디#아카데미" -> "#구디 #아카데미" -- 공백추가해서 바꿔주겠다
			Set<String> set = new HashSet<>(); // 중복된 해시태그방지를 위해 set자료구조를 사용
			for(String ht : memo2.split(" ")){ // memo2의 공백
				if (ht.startsWith("#")) {
				String ht2 = ht.replace("#", "");
				if(ht2.length() > 0) {
					set.add(ht2);
				}
			}
		}
		      for(String s : set) {
	        		Hashtag hashtag = new Hashtag();
					hashtag.setCashbookNo(cashbookNo);
					hashtag.setWord(s);
					hashtagDao.insertHashtag(hashtag);
	        }			
				// 입력성공 redirect -> CalendarOneController -> forward -> CalendarOne.jsp 문자형으로 넘겨줘야한다
				// 받아오기만 하는 값들은 api month+1을 해주지 않아도 되므로 그대로 둔다
			response.sendRedirect(request.getContextPath() + "/calendarOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate);
		}
}
