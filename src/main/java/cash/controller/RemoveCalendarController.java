package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Member;
// cashbook 가계부 데이터 삭제는 폼X, 추후 자바스크립트를 통해 confirm - alert창 추가 + checkbox 선택 삭제 기능 구현해보기
// 삭제버튼을 눌렀을 때 체크박스 활성화할지 
@WebServlet("/removeCalendar")
public class RemoveCalendarController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
        int targetYear = Integer.parseInt(request.getParameter("targetYear"));
        int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
        int targetDate = Integer.parseInt(request.getParameter("targetDate"));
        
        // 삭제 DAO 호출
        CashbookDao cashbookDao = new CashbookDao();
        int row = cashbookDao.deleteCashbook(cashbookNo);
        
        if(row == 0) {
        	System.out.println("데이터 삭제 실패");
			request.getRequestDispatcher("/WEB-INF/view/calendarOne.jsp").forward(request, response);
        	return;
        }
			System.out.println("데이터 삭제 성공");
			response.sendRedirect(request.getContextPath()+"/calendarOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate);
		}
	}

