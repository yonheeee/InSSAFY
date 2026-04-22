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
<c:if test="${param.user == 'guest' }">
<h1>Guest</h1>
</c:if>
<c:if test="${param.user == 'member' }">
<h1>Member</h1>
</c:if>
<c:if test="${param.user != 'member' and param.user != 'guest' }">
<h1>애들은 가라~</h1>
</c:if>
</body>
</html>