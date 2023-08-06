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

@WebServlet("/modifyMember")
public class ModifyMemberController extends HttpServlet {
	
	// 회원 정보 수정 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null){
			// 로그인이 되지 않은 상태면 login.jsp로 이동한다
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
	
		// 로그인 상태일시 modifyMember.jsp로 이동
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	}
	
	// 회원 정보 수정 action - post를 통해 값 가져옴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null){
			// 로그인이 되지 않은 상태면 login.jsp로 이동한다
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId(); // id는 고정값
		String memberPw = request.getParameter("memberPw"); // pw는 폼 입력값(확인)
		String modiPw = request.getParameter("modiPw"); // 수정할 비밀번호
		String remodiPw = request.getParameter("remodiPw"); // 수정 비밀번호 확인

		/*if (memberPw == null || memberPw.equals("")) {
		    System.out.println("현재 비밀번호를 입력하세요");    
		} else if (modiPw == null || modiPw.equals("")) {
		    System.out.println("변경할 비밀번호를 입력하세요");
		} else if(remodiPw == null || remodiPw.equals("")){
			 System.out.println("비밀번호 확인값을 입력하세요");
		}
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);*/
		
		String modifyPw ="";
		if(modiPw.equals(remodiPw)) { // 수정 비밀번호 - 재확인이 일치하면
			modifyPw = modiPw;
		}else {
			System.out.println("변경할 비밀번호 불일치");
			response.sendRedirect(request.getContextPath()+"/modifyMember");
			return;
		}
		
		// 모델값 호출
		MemberDao memberDao = new MemberDao();
		int row = memberDao.modifyMember(memberId,memberPw,modiPw);
		
		if(row == 1) {
			System.out.println("비밀번호 변경 성공");
			response.sendRedirect(request.getContextPath()+"/memberOne");
		}else if(row == 0){
			System.out.println("비밀번호 변경 실패");
			response.sendRedirect(request.getContextPath()+"/modifyMember");
		}else {
			System.out.println("modify member error!");
		}
	}

}
