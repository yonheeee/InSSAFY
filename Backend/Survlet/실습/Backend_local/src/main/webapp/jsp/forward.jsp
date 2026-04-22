<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%request.getRequestDispatcher("forward2.jsp").forward(request, response); %> --%>
	<h1>forward.jsp</h1>
	<jsp:forward page="forward2.jsp"></jsp:forward>
</body>
</html>