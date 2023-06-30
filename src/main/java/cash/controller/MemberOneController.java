package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/memberOne")
public class MemberOneController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼에서 받아오는 게 없음 doPost 삭제
		// session 유효성 검사
		HttpSession session = request.getSession();
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		// 모델 값 구하기 - 모델 호출 (DAO 메서드 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		// member 출력하는 (포워딩 대상) memberOne.jsp에도 공유되어야 한다
		// request가 공유되니까 request 안에 넣어 같이 공유하기
		request.setAttribute("member", member); // member라는 이름으로 들어간다
		
		// memberOne.jsp 포워딩 (보여준다)
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

}
