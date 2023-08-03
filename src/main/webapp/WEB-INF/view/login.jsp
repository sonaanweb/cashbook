<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- WEB-INF폴더에는 브라우저에서 직접 접근할 수 없다. 오직 서버내에서만 접근이 가능하다.(java파일에서 톰캣서버 연결)
만약 브라우저가 WEB-INF폴더에 있는 파일에 직접 접근하려고 하면 404에러를 발생시킨다.  -->
<!-- EL태그 = JSP, out.println과 같은 자바코드 간편출력 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
* { padding: 0; margin: 0; }

html, body {
  font-family: 'Pretendard-Regular';
  height: 100%;
  background: #ffffff;
}

#container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100%;
}

#loginBox {
  width: 300px;
  text-align: center;
  background-color: #ffffff;
}
.input-form-box {
  border: 0px solid #ff0000;
  display: flex;
  margin-bottom: 5px;
}
.input-form-box > span {
  display: block;
  text-align: left;
  padding-top: 5px;
  min-width: 65px;
}
.button-login-box {
  margin: 10px 0;
}
#loginBoxTitle {
  color:#000000;
  font-weight: bold;
  font-size: 32px;
  text-transform: uppercase;
  padding: 5px;
  margin-bottom: 20px;
  background: linear-gradient(to right, #f3969a, #FFEBB4, #FFACAC);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
#inputBox {
  margin: 10px;
}

#inputBox button, a.btn.btn-light{
  font-family: 'Pretendard-Regular';
  padding: 3px 5px;
}
a.btn.btn-light{
	background-color: #FFFFFF;
	border-color: #FFFFFF;
}
</style>
</head>
<body class="text-center">
<div id="container">
<!--  login 폼 영역 -->
<form method="post" action="${pageContext.request.contextPath}/login">
	<div id="loginBox">
		<div style="font-size: 16px; text-align: left;" >open!🔑</div>
		<div id="loginBoxTitle">Your CashBook</div>
        <!-- 아이디, 비밀번호, 아이디 저장 체크 -->
        	<div id="inputBox">
          		<div class="input-form-box">
          			<span>아이디</span>
          			<input type="text" name="memberId" class="form-control" value="${loginMember}">
          		</div>
       			<!-- EL은 null값 분기문 작성하지 않아도 된다. 자동null값 처리 -->
       			<!-- id end -->
	          	<div class="input-form-box">
	       			<span>비밀번호 </span>
	       			<input type="password" name="memberPw" class="form-control">
	          	</div>
          		<!-- pw end -->
        		<div>
				<input type="checkbox" name="idSave" value="y" ${loginMember != null ? 'checked' : ''}>ID저장
				<!-- 삼항 연산자 사용 checked 유지와 아이디 유지 -->
				</div>
				<!-- check end -->
          		<div class="button-login-box" >
            		<button type="submit" class="btn btn-secondary" style="width:100%">로그인</button>
            		<a href="${pageContext.request.contextPath}/addMember" class="btn btn-light" style="width:100%">회원가입</a>
          		</div>
        	</div>
		</div>
	</form>
</div>
</body>
</html>