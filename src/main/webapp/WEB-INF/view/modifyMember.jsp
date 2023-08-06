<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<title>회원정보 수정</title>
<style>
.infomodify{
	display: flex;
	justify-content: center;
	align-items: center;
	height: 70vh;
	}
</style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/modifyMember"><!--  @WebServlet("/ModifyMember") -->
<div class="container mt-3">
	<!-- 메인 메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/inc/mainmenu.jsp"></jsp:include>
	</div>
	<!-- ----- 메인 메뉴 end -->
	<div class="infomodify">
	<table class="form-group">
		<tr>
			<td style="text-align: center;"><img src="./img/smile.png"></td>
		</tr>
		<tr>
			<td class="form-label mt-4">현재 비밀번호</td>
		</tr>
		<tr>
			<td><input type="password" name="memberPw" class="form-control"></td>
		</tr>
		<tr>
			<td class="form-label mt-4">비밀번호 변경</td>
		</tr>
		<tr>
			<td><input type="password" name="modiPw" class="form-control"></td>
		</tr>
		<tr>
			<td class="form-label mt-4">비밀번호 확인</td>
		</tr>
		<tr>
			<td><input type="password" name="remodiPw" class="form-control"></td>
		</tr>
		<tr>
			<td><button type="submit" class="btn btn-dark" style="margin-top: 10px;">수정</button></td>
		</tr>
	</table>
	</div>
</div>
</form>
</body>
</html>