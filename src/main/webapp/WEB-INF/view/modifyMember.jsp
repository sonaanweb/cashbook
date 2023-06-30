<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/modifyMember"><!--  @WebServlet("/ModifyMember") -->
	<table class="table">
		<tr>
			<td>현재 비밀번호</td>
			<td><input type="password" name="memberPw"></td>
		</tr>
		<tr>
			<td>비밀번호 변경</td>
			<td><input type="password" name="modiPw"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="remodiPw"></td>
		</tr>
	</table>
	<button type="submit">수정</button>
</form>
</body>
</html>