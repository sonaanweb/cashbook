<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/removeMember"><!-- post컨트롤러 -->
<div class="container mt-3">
	<table class="table">
		<tr>
			<td>아이디</td>
			<td>${memberId}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="memberPw"></td>
		</tr>
	</table>
	<button type="submit" class="btn">탈퇴하기</button>
</div>
</form>
</body>
</html>