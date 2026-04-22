<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${param.user == 'guest' }">
		<h1>Guest</h1>
		</c:when>
		<c:when test="${param.user == 'member' }">
		<h1>Member</h1>
		</c:when>
		<c:otherwise>
			<h1>애들은 가라~~~</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>