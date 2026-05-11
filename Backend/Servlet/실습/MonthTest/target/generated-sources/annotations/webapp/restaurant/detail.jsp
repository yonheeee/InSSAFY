<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보 페이지</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
     <%@include file="/header.jsp"%>
	<h1>상세 정보 페이지</h1>

	<a
		href="${root }/restaurant?action=list">목록으로</a>
	
	<table>
		<tr>
			<th>맛집 코드</th>
			<td>${restaurant.code}</td>
		</tr>
		<tr>
			<th>맛집 이름</th>
			<td>${restaurant.name}</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>${restaurant.category}</td>
		</tr>
		<tr>
			<th>별점</th>
			<td>${restaurant.rating}점</td>
		</tr>
		<tr>
			<th>대표 리뷰</th>
			<td>${restaurant.review}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${restaurant.regDate }</td>
		</tr>
	</table>
	<a
		href="#">수정</a>
	<a
		href="${root }/restaurant?action=delete&code=${restaurant.code}">삭제</a>
</body>
</html>