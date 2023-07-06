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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
a {text-decoration: none;}
</style>
</head>
<body>
<div class="container mt-3">
	<h1>로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		 <table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="memberId" value="${loginMemberId}"></td>
        		 <!-- EL은 null값 분기문 작성하지 않아도 된다. 자동null값 처리 -->
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<div>
			<input type="checkbox" name="idSave" value="y" ${loginMemberId != null ? 'checked' : ''}>ID저장
			<!-- 삼항 연산자 사용 checked 유지와 아이디 유지 -->
		</div>
		<button type="submit" class="btn">로그인</button>
		<a href="${pageContext.request.contextPath}/addMember" class="btn">회원가입</a>
	</form>
</div>
</body>
</html>