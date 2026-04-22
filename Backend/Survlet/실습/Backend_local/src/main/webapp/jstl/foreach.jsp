<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set var="price" value="1000,2000,3000,4000,5000"></c:set>

<body>
	<c:forEach var="p" items="${price }" varStatus="cnt" >
		${cnt.count}번 price : ${p } <br>
	</c:forEach>
	<hr>
	<c:forEach var="p" items="${price }" varStatus="cnt" begin="2" end="3" >
		${cnt.count}번 price : ${p } <br>
	</c:forEach>
	<hr>
	<c:forEach var="p" items="${price }" varStatus="cnt" step="2">
		${cnt.count}번 price : ${p } <br>
	</c:forEach>
</body>
</html>