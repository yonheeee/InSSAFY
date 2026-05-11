<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.ssafy.dto.MemberDto"%>
<jsp:useBean id="mem" class="edu.ssafy.dto.MemberDto" scope="session" />
<jsp:setProperty name="mem" property="id" value="44"/>
<jsp:setProperty name="mem" property="name" value="마이콜"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>jsp</h3>
	<%MemberDto m =(MemberDto) request.getSession().getAttribute("mem"); %>
	<%=m.getId() %><br />
	
	<h3>tag</h3>
	<jsp:getProperty property="id" name="mem"/><br />
	<jsp:getProperty property="name" name="mem"/><br />
	
	<h3>el</h3>
	${mem.id }<br />
	${mem["id"] }
	${mem.name }
	${sessionScope["mem"]["id"]}
	
	<br>
	${param.id }
	${empty param.name }
	${empty paramValues.hobby }
</body>
</html>