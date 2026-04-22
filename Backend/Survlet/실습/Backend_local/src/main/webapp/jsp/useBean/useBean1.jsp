<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <% MemberDto m = new MemberDto(); %> --%>
	<jsp:useBean id="m" class="edu.ssafy.dto.MemberDto" scope="session"></jsp:useBean>
	<jsp:setProperty property="id" name="m" value="aa"/>
	<jsp:getProperty property="id" name="m"/>
</body>
</html>