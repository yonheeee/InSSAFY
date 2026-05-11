<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 조회 페이지</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<%@include file="/header.jsp"%>
	<h1>목록 조회 페이지</h1>

	<a href="${root }/restaurant?action=index">메인 화면으로</a>
	<a href="${root }/restaurant?action=registForm">등록하기</a>
	<table>
		<thead>
			<tr>
				<th>맛집 코드</th>
				<th>맛집 이름</th>
				<th>별점</th>
			</tr>
		</thead>
		<tbody>
         <!-- <tr>
            <td><a href="">더미코드</a></td>
            <td>더미 맛집 이름</td>
            <td>더미 별점</td>
         </tr> -->
         
         <c:forEach var="r" items="${restaurants }">
         	<tr>
         		<td><a href="${root }/restaurant?action=detail&code=${r.code}">${r.code }</td>
         		<td>${r.name }</td>
         		<td>${r.rating }</td>
         	</tr>
         </c:forEach>
		</tbody>
	</table>
</body>
</html>