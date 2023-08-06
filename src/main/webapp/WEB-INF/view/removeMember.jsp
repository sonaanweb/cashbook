<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
.removemember{
 font-family: 'Pretendard-Regular';
	display: flex;
	justify-content: center;
	align-items: center;
	height: 70vh;
	}

</style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/removeMember"><!-- post컨트롤러 -->
<div class="container mt-3">
	<!-- 메인 메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/inc/mainmenu.jsp"></jsp:include>
	</div>
	<div class="removemember">
	<!-- ----- 메인 메뉴 end -->
		<table class="form-group">
			<tr>
  			<td colspan="2" style="text-align: center; color: red;">
  			탈퇴시 계정 복구는 불가능합니다.<br>탈퇴를 원하시면 비밀번호를 입력해주세요.</td>
			</tr>
			<tr style="height: 20px;"><!-- 이전 tr과의 간격을 위한 추가 -->
			</tr>
			<tr>
				<td>아이디</td>
				<td>${memberId}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="memberPw" class="form-control"></td>
			</tr>
			<tr>
				<td><button type="submit" class="btn btn-dark" style="margin-top: 10px;">탈퇴하기</button></td>
			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>