<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부 상세 내역</title>
</head>
<body>
<h1>${targetYear}년 ${targetMonth}월 ${targetDate}일</h1>
<a href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">추가</a>
<!--  <button onclick="deleteSelected()">삭제</button> -->
<table>
	<tr>
		<!-- <th>선택</th>-->
	    <th>수입/지출</th>
	    <th>금액</th>
	    <th>날짜</th>
	    <th>메모</th>
	    <th>삭제</th>
	</tr>
	<c:forEach items="${list}" var="cashbook">
    <tr>
     	<!-- <td><input type="checkbox" name="selectedItem" value="${cashbook.cashbookNo}"></td> -->
        <td>${cashbook.category}</td>
        <td>${cashbook.price}</td>
        <td>${cashbook.cashbookDate}</td>
        <td>${cashbook.memo}</td>
        <td><a href="${pageContext.request.contextPath}/removeCalendar?cashbookNo=${cashbook.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">삭제</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>