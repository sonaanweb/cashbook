<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- jsp컴파일시 자바코드로 변환되는 c:... (제어문코드) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
body {
   font-family: 'Pretendard-Regular';
   margin: 20px;
   justify-content: center;
   align-items: center;
 }

td {
   width: 120px;
   height: 120px;
   text-align: center;
 }

th {
   background-color: #f2f2f2;
 }
 
.day-link {
   display: block;
   padding: 5px;
   text-align: right;
 }

.day-link:hover {
   background-color: #FBEEAC;
 }

.income {
   color: #F4D160;
 }

.expense {
   color: #1D5D9B;
 }
.container {
    text-align: center;
  }

.hash-tags {
    display: inline-block;
  }
.date{
	font-size: 40px;
  }
.pre{
	color: #1D5D9B;
}
.next{
	color: #1D5D9B;
}
.mandy{
	color: #071952;
}
a{
	text-decoration: none;
}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<br>
	<!-- 메인 메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/inc/mainmenu.jsp"></jsp:include>
	</div>
	<!-- ----- 메인 메뉴 end -->
		<span>${loginMember.memberId}님 환영합니다!💰<!-- loginMember에 담음 --></span>
		<br>&#128171;오늘도 좋은 하루 보내세요&#128171;
<!-- 변수값 or 반환값 대신 EL 표현식(달러표시) 사용 -->
<!--
	속성값 대신 EL 사용 ex) request.getAttribute("targetYear")
	-- requsetScope.targetYear(requstScope는 생략 가능)
	형변환 연산이 필요 없다. EL이 자동으로 처리
-->
<!-- 자바코드(제어문) : JSTL로 사용 -->
<div class="mandy">
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}" class="pre">&lt;</a>
	<span class="date">${targetYear}년 ${targetMonth+1}월</span>
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}" class="next">&gt;</a>
</div>
<div class="hash-tags">
	<span>&#128464;이달의 해시태그</span>
	<div>
		<c:forEach var="m" items="${htList}">
			<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">
			${m.word}(${m.cnt})
			</a>
		</c:forEach>
	</div><br>
</div>
	<table class="table table-bordered">
	<!-- c:forEach : for/forEach문 둘 다 사용가능 -->
	<tr style="background-color: #F4D160;">
		<th>일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th>토</th>
	</tr>
		<tr>
			<c:forEach var="i" begin="0" end="${totalCell-1}" step="1">
				<c:set var="d" value="${(i-beginBlank+1)}"></c:set> <!-- value 값을 d라 부름 -->
			<!-- var(변수)=i, 0부터 totalCell-1 까지 반복, step = 1씩 증가-->
				<c:if test="${i != 0 && i%7 == 0}"> <!-- 조건 -->
					</tr><tr>
				</c:if>
				
				<c:if test="${d < 1 || d > lastDate}">
					<td></td>
				</c:if>
				
				<!-- else = > 반대 조건 -->
				<c:if test="${!(d < 1 || d > lastDate)}">
					<td>
                    	<div class="day-link">
                        <a href="${pageContext.request.contextPath}/calendarOne?targetYear=${targetYear}&targetMonth=${(targetMonth+1)}&targetDate=${d}">
                            ${d}
                        </a>
                    	</div>
						<c:forEach var="c" items="${list}">
						<!-- 같으면 그 날짜의 일정 출력-->
							<c:if test="${ d == fn:substring(c.cashbookDate,8,10)}">
								<div>
									<c:if test="${c.category == '수입' }">
										<span class="income">+${c.price}</span>
									</c:if>
								<c:if test="${c.category == '지출' }">
									<span class="expense">-${c.price}</span>
								</c:if>
								</div>
							</c:if>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<br><br>
</div>
</body>
</html>