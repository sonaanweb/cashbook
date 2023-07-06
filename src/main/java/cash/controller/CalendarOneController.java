package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.MemberDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/calendarOne")
public class CalendarOneController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(session.getAttribute("loginMember")==null){
			// 로그인이 되지 않은 상태면 login.jsp로 이동한다
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		String memberId = loginMember.getMemberId();
		System.out.println(memberId + "<---calendar One login member");			
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		List<Cashbook> list = new CashbookDao().selectCashbookListByDate(memberId, targetYear, targetMonth, targetDate);
		
		/*request.setAttribute("memberId", memberId);*/
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("list", list);
		System.out.println(list);
		
		// CalendarOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/calendarOne.jsp").forward(request, response);
	}

}
