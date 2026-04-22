<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h1>로그인</h1>
	<form action="/Backend_local/member?action=login" method="post">
	<table border="1px; solid; #000">
		<tr><td>id</td><td><input type="text" name="id" /></td></tr>
		<tr><td>password</td><td><input type="text" name="password" /></td></tr>
		<tr><td colspan="2"><input type="submit" value="입력" /></td></tr>
	</table>
	</form>
	<%@ include file="/template/tail.jsp" %>
</div>
</body>
</html>