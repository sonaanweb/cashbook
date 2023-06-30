<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<title>회원정보</title>
</head>
<body>
<div class="container mt-3">
	<table class="table table-bordered">
		<tr>
			<td>아이디</td>
			<td>${member.memberId}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${member.memberPw}</td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath}/modifyMember" class="btn">회원정보수정</a>
	<a href="${pageContext.request.contextPath}/removeMember" class="btn">회원탈퇴</a>
</div>
</body>
</html>