<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<title>회원정보</title>
<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
html, body{
   font-family: 'Pretendard-Regular';
   justify-content: center;
   align-items: center;
}
h2{
   font-family: 'Pretendard-Regular';
   justify-content: center;
   align-items: center;
}
.left-section {
  width: 25%;
  float: left;
  padding-right: 20px;
}

.right-section {
border-left: 0.5px solid rgb(169, 169, 169);
padding-left: 20px;
  width: 75%;
  float: right;
}

.pw {
    border: none;
  }
</style>
</head>
<body>
<div class="container mt-3">
	<!-- 메인 메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/inc/mainmenu.jsp"></jsp:include>
	</div>
	<!-- ----- 메인 메뉴 end -->
 	<div class="flex">
		<div class="left-section" style="position: relative;">
			<div style="text-align: right">
    			<a href="${pageContext.request.contextPath}/modifyMember">회원정보수정</a>
    		</div>
    		<div style="text-align: right">
				<a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
			</div>
         </div>
         <div class="right-section">
          	<div>
				<h2 style="color: black;">${loginMember.memberId}님, 안녕하세요!</h2>
			</div>
			<div>
				<img src="./img/smile.png">
			</div>
			<div class="row">
				<table>
					<tbody class="left">
						<tr>
							<th>아이디</th>
							<td>${member.memberId}</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td>${member.createdate}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>