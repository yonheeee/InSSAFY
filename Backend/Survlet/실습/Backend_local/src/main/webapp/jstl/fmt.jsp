<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set value="123456789.789" />
	<jsp:useBean id="date" class="java.util.Date" />
	<h1><fmt:formatNumber type="number" value="${num } "/></h1>
	<h1><fmt:formatNumber type="currency" value="${num } "/></h1>
	date:<fmt:formatDate value="${date }" type="date" />
	time:<fmt:formatDate value="${date }" type="time" />
	both:<fmt:formatDate value="${date }" type="both" />

</body>
</html>