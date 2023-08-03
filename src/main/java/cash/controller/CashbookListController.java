package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.vo.Cashbook;

/**
 * Servlet implementation class CashbookListController
 */
@WebServlet("/cashbookListbyTag")
public class CashbookListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session 구현 test 부분 id / 페이징 알고리즘 재정리
		String memberId = "user";
		String word = request.getParameter("word"); // word에서 넘어온다
		int currentPage = 1;
		int rowPerPage = 10;
		int beginRow = 0;
		
		// DAO 호출
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook>list = cashbookDao.selectCashbookListByTag(memberId, word, beginRow, rowPerPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}

}
