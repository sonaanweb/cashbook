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
<h1>${targetYear}년 ${targetMonth+1}월</h1>
<table>
	<tr>
	    <th>수입/지출</th>
	    <th>금액</th>
	    <th>날짜</th>
	    <th>메모</th>
	</tr>
	<c:forEach items="${list}" var="cashbook">
    <tr>
        <td>${cashbook.category}</td>
        <td>${cashbook.price}</td>
        <td>${cashbook.cashbookDate}</td>
        <td>${cashbook.memo}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>