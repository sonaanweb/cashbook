package cash.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		/* session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 상태라면
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}*/

	// 로그인시 쿠키에 저장된 로그인 아이디 가져오기
	Cookie[] cookies = request.getCookies(); // 쿠키 배열 가져옴
	if(cookies != null) {
	for(Cookie c : cookies) {
		if(c.getName().equals("loginMemberId") == true) {
			request.setAttribute("loginMemberId", c.getValue());
			// request 객체의 속성에 memberId라는 이름으로 값을 저장
			// setAttribute()메서드를 사용하여 쿠키의 값을 request 객체에 저장한다
			// 이렇게 저장된 값은 다른 서블릿이나 jsp에서 활용할 수 있다 getAttribute()
			}
		}
	}
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		//forward : 서버상에서 페이지가 이동되기때문에 넘어간 페이지의 url이 나타나지 않고 현재 페이지 url 그대로 유지

}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doPost : form태그에서 서버로 데이터를 전송할 때 post 방식으로 보내는 것(doPost 메서드가 호출)
		//폼에서 받는 "name" 사용자로부터 입력받은 아이디와 비밀번호를 가져온다
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
		
		// 로그인 성공시 : session 사용 - 다른페이지에서 get으로 불러올 수 있음
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", loginMember);
		// Attribute request 객체에 존재하는 저장공간
		// session에 담긴 정보를 보낸다 ("객체명","객체")
		// 객체명 = 어떤 이름을 붙여서 보내고 싶은지
		// 객체 = 정보를 담은 객체
		
		// 아이디 저장 쿠키
		// 로그인 정보 세션 저장 + 로그인 id를 쿠키(만료기간설정)에 저장 -> loginMember라는 이름으로 저장
		// 로그인 페이지에 아이디저장이 체크되어있으면(test) idSave값이 넘어왔다면
		if(request.getParameter("idSave")!= null) { // idSave가 null이 아니라면
			Cookie loginIdcookie = new Cookie("loginMemberId",memberId); // 쿠키가 하나의 map이다 매개변수필요 string,string (키값) 저장
			//loginIdcookie.setMaxAge(0); // 기간 : 초단위로 계산 ex) 60*60*24 = 하루
			response.addCookie(loginIdcookie); // 응답. 쿠키정보를 사용자에게 전송
		}else {
			Cookie loginIdcookie = new Cookie("loginMemberId",null);
			loginIdcookie.setMaxAge(0); // 0 = 쿠키 삭제
			response.addCookie(loginIdcookie);
		}
		response.sendRedirect(request.getContextPath()+"/cashbook");
		// 로그인 성공페이지로 redirect -> /cashbook
	}

}