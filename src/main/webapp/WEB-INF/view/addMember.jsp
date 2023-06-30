<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<title>addMember.jsp</title>
</head>
<body>
<div class="container mt-3">
	<form method="post" action="${pageContext.request.contextPath}/addMember"><!-- 컨트롤러 dopost -->
	<table class="table table-bordered">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="memberId"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="memberPw"></td>
		</tr>
	</table>
		<button type="submit" class="btn">회원가입</button>
	</form>
</div>
</body>
</html>