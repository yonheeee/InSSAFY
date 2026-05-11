<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,edu.ssafy.dto.MemberDto"%>
<%
    List<MemberDto> list = (List<MemberDto>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원조회</title>
</head>
<body>
    <div align="center">
        <h1>회원조회</h1>
        <table border="1">
            <tr>
                <td>id</td>
                <td>pw</td>
                <td>name</td>
                <td>age</td>
                <td>hobby</td>
            </tr>

            <% for(MemberDto mem : list){ %>
            <tr>
               <a href="/Backend_Local/member?action=detail&id=<%= mem.getId() %>"><td></td>
                <td><%= mem.getPassword() %></td>
                <td><%= mem.getName() %></td>
                <td><%= mem.getAge() %></td>
                <td><%= java.util.Arrays.toString(mem.getHobby()) %></td>
            </tr>
            <% } %>
        </table>
        <%@ include file="/template/tail.jsp" %>
    </div>
</body>
</html>