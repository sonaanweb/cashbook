<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
a {text-decoration: none;}
</style>
<title>cashbook.jsp</title>
</head>
<body>
<div class="container mt-3">
	<h1>cash book</h1>
		${loginMember.memberId}님 환영합니다<!-- loginMember에 담음 -->
	<div><a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne">회원정보</a>
	<a href="${pageContext.request.contextPath}/calendar">캘린더</a></div>
</div>
</body>
</html>