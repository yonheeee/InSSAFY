<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
		<a href="/Backend_local/generic">GenericServlet</a>
		<a href="/Backend_local/myjsp.jsp">myjsp.jsp</a>
		<a href="/Backend_local/myservlet?name=둘리&age=9">myservlet</a>
		<form action="/Backend_local/myservlet" method="post">
			<input type="text" name="name"><br>
			<input type="text" name="age"><br>
			<label><input type="checkbox" name="과일" value="사과">사과</label>
			<label><input type="checkbox" name="과일" value="바나나">바나나</label>
			<label><input type="checkbox" name="과일" value="배">배</label>
			<input type="submit" value="눌러">
		</form>
		
		<a href="/Backend_local/member?action=insertform">회원입력</a>
		<a href="/Backend_local/member?action=select">회원조회</a>
	</div>
</body>
</html>