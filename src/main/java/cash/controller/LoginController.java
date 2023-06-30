package cash.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.*;
import cash.vo.*;

@WebServlet("/login")//new-servlet 생성
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet : form태그에서 서버로 데이터를 전송할 때 get 방식으로 보내는 것(doGet 메서드가 호출)
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	//forward : 서버상에서 페이지가 이동되기때문에 넘어간 페이지의 url이 나타나지 않고 현재 페이지 url 그대로 유지
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doPost : form태그에서 서버로 데이터를 전송할 때 post 방식으로 보내는 것(doPost 메서드가 호출)
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member(memberId, memberPw, null, null);
		
		// dao 호출
		MemberDao memberDao = new MemberDao();
		Member loginMember = memberDao.selectMemberById(member);
			
		if(loginMember == null) { // null 로그인실패
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/login"); //jsp파일 리다이렉트 불가능(web-inf 폴더 내 위치하기 때문)
			return;
		}
		
		// 로그인 성공시 : session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", loginMember);
		// Attribute request 객체에 존재하는 저장공간
		// session에 담긴 정보를 보낸다 ("객체명","객체")
		// 객체명 = 어떤 이름을 붙여서 보내고 싶은지
		// 객체 = 정보를 담은 객체
		response.sendRedirect(request.getContextPath()+"/cashbook");
	}

}