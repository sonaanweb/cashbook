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

@WebServlet("/removeMember")
public class RemoveMemberController extends HttpServlet {
	
	// 비밀번호 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 탈퇴하려는 회원을 구분할 수 있는 정보 추출
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember != null) {
			String memberId = loginMember.getMemberId();
			request.setAttribute("memberId", memberId);
			request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response); // post 방식으로 넘어옴
		}else {
			response.sendRedirect(request.getContextPath()+ "/login");
		}
		
	}
	
	// 탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember != null) {
			String memberId = loginMember.getMemberId(); // 고정 되어있는 값
			String memberPw = request.getParameter("memberPw"); // pw 받아와야 함 (비밀번호 재입력)
			
			// 회원탈퇴 메서드 호출
			MemberDao memberDao = new MemberDao();
			int row = memberDao.removeMember(memberId, memberPw);
			
			if(row == 1) { // row == 1
			// 비밀번호 검사
			System.out.println("탈퇴 성공");
			session.invalidate(); // 비밀번호가 맞으면 탈퇴 성공, 성공시 login.jsp로 이동
			response.sendRedirect(request.getContextPath()+"/login");
		} else {
			// 비밀번호 불일치
			System.out.println("비밀번호 불일치");
			request.setAttribute("memberId", memberId); // 폼에서 아이디 유지
			request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
			return;
		}
	}

}
}