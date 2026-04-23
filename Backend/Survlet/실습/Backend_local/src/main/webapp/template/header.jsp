<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="right">
		<c:if test="${!empty sessionScope.login }">
			로그인 정보 : ${sessionScope.login }
			<form action="/Backend_local/member?action=logout" method="post">
				<input type="submit" value="로그아웃">
			</form>
		</c:if>
		<c:if test="${empty sessionScope.login }">
			로그인 정보 : 로그인 되어있지 안습니다
		</c:if>
	</div>
</body>
</html>