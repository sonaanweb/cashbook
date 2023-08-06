<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
span{
vertical-align : middle;
}
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
#cashTitle { <!-- cashbook title -->
  color:#000000;
  font-weight: bold;
  font-size: 32px;
  text-transform: uppercase;
  background: linear-gradient(to right, #1D5D9B, #F4D160, #FBEEAC);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
html, body{
  font-family: 'Pretendard-Regular';
  height: 100%;
  background: #ffffff;
}
li{
  font-family: 'Pretendard-Regular';
  height: 100%;
}
a{
	color: black;
	text-decoration: none;
}
</style>
</head>
<body>
<br>
<div class="container mt-3">
<nav class="navbar navbar-expand-lg" data-bs-theme="light" style="background-color: white;">
	<%
		if(session.getAttribute("loginMember") != null) { // 로그인 전 세션값 확인
	%>	
    	<span><a href="${pageContext.request.contextPath}/calendar" class="navbar-brand" id="cashTitle">Cashbook</a></span>
    <%
		}else{
    %>
    	<span><a href="${pageContext.request.contextPath}/login" class="navbar-brand" id="cashTitle">Cashbook</a></span>
    <%
		}
    %>
		<div class="collapse navbar-collapse" id="navbarColor03">
			<ul class="navbar-nav me-auto">
			<%
				if(session.getAttribute("loginMember") != null) { // 로그인 전 세션값 확인
			%>	
				<li class="nav-item">
					<a class="nav-link active" href="${pageContext.request.contextPath}/calendar">캘린더</a>
				</li>
        		<li class="nav-item">
          			<a class="nav-link active" href="${pageContext.request.contextPath}/memberOne">회원정보</a>
       			</li>
        		<li class="nav-item">
          			<a class="nav-link active" href="${pageContext.request.contextPath}/logout">로그아웃</a>
        		</li>
        	<%
        		}else{
        	%>
        		<li>환영합니다. 회원가입을 진행해주세요</li>
        	<%
        		}
        	%>
       		</ul>
      	</div>
	</nav>
<hr>
</div>
</body>
</html>