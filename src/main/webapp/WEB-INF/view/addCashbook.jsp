<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 가계부 입력 폼 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 추가</title>
</head>
<body>
<h1>추가하기</h1>
<form method="post" action="${pageContext.request.contextPath}/addCashbook">
<input type="hidden" name="targetYear" value="${targetYear}">
<input type="hidden" name="targetMonth" value="${targetMonth}">
<input type="hidden" name="targetDate" value="${targetDate}">
<table class="table table-bordered">
	<tr>
		<td>날짜</td>
		<!-- 앞에서 값을 그대로 받아오기 때문에 month + 1 을 해주지 않아도 된다 (이미 되어있음)-->
		<td>${targetYear}년 ${targetMonth}월 ${targetDate}일</td>
	</tr>
	<tr>
		<td>수입/지출</td>
		<td>
			<select name="category">
			<option value="수입">수입</option>
			<option value="지출">지출</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>금액</td>
		<td><input type="number" name="price"></td>
	</tr>
	<tr>
		<td>메모</td>
		<td>
		<textarea rows="5" cols="80" placeholder="메모를 입력하세요" name="memo"></textarea>
		</td>
	</tr>
</table>
	<div>
		<button type="submit">저장</button>
	</div>
</form>
</body>
</html>