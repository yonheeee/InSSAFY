<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,edu.ssafy.dto.MemberDto"%>
<%-- <%
	List<MemberDto> list = (List<MemberDto>)request.getAttribute("res");
%> --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div align="center">
		<%@ include file="/template/header.jsp" %>
			<h1>회원조회</h1>
			<table border="1px;solid;#000">
				<tr><td>id</td><td>pw</td><td>name</td><td>age</td><td>hobby</td><td>삭제</td></tr>
				<%-- <% for(MemberDto mem: list){ %> --%>
				<form action=/Backend_local/member?action=delsid" method="post"></form>
					<c:forEach items="${res }" var="mem">
					<tr>
						<td><a href="/Backend_local/member?action=detail&id=${mem.id}">${mem.id }</a></td>
						<td>${mem.password }</td>
						<td>${mem.name }</td>
						<td>${mem.age }</td>
						<td>${mem.hobby[0] }</td>
						<td><input type="checkbox" name="delsid" value="${mem.id }"></td>
					</tr>
					</c:forEach>
				<input type="submit" value="선택삭제" />
				</form>
				<%-- <%} %> --%>
			</table>
			<%@ include file="/template/tail.jsp" %>
		</div>
</body>
</html>
