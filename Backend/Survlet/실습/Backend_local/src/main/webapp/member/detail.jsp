<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.ssafy.dto.MemberDto, java.util.Arrays"%>
    
  <%
  MemberDto mem = (MemberDto)request.getAttribute("mem");
  %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function deletemember(){
	id = document.getElementById("id").value;
	document.getElementById("rform").action="/Backend_local/member?action=delete&id="+id;
	document.getElementById("rform").submit();
}

</script>
<title>Insert title here</title>
</head>
<body>
<div align="center">
<%@ include file="/template/header.jsp" %>
	<h1>회원상세</h1>
	<form id="rform" action="/Backend_local/member?action=update" method="post">
	
	<table border="1px; solid; #000">
		<tr><td>id</td><td><input type="text" id="id" readonly="readonly "name="id" value="<%=mem.getId() %>" /></td></tr>
<%-- 		<tr><td>password</td><td><input type="text" name="password" value="<%=mem.getPassword() %>" /></td></tr> --%>
		<tr><td>name</td><td><input type="text" name="name" value="<%=mem.getName() %>"/></td></tr>
		<tr><td>age</td><td><input type="text" name="age" value="<%=mem.getAge() %>"/></td></tr>
		<tr><td>hobby</td><td><input type="text" name="hobby" value="<%=Arrays.toString(mem.getHobby()) %>"/></td></tr>
		<tr><td colspan="2"><input type="submit" value="수정" /><input type="button" onclick="deletemember()" value="삭제" /></td></tr>
	</table>
	
	</form>
	<%@ include file="/template/tail.jsp" %>
</div>
</body>
</html>