<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
>>>>>>> 7b17fbba4bd746c51ad3e90a5036e13d140e51be
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<title>Insert title here</title>
</head>
<body>

=======
<title>자동차 등록 결과</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
}

th:nth-child(1) {
	width: 130px;
}
</style>
</head>
<body>
	<h1>자동차 등록 결과</h1>
	<h2>지금까지 등록한 자동차 수 : <%=request.getAttribute("carCount")%></h2>
	<h2>등록된 자동차 정보</h2>
	<%-- 테이블 내에서 Car의 내용을 출력하기 위해 expression tag를 사용한다. --%>
	<table>
		<thead>
			<tr>
				<th>항목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>자동차등록번호</td>
				<td><%=request.getAttribute("VIN")%></td>
			</tr>
			<tr>
				<td>모델 명</td>
				<td><%=request.getAttribute("modelName")%></td>
			</tr>
			<tr>
				<td>색상</td>
				<td><%=request.getAttribute("color")%></td>
			</tr>
			<tr>
				<td>주행 거리</td>
				<td><%=request.getAttribute("mileage")%></td>
			</tr> 
		</tbody>
	</table>
	<!-- 다시 자동차를 등록할 수 있는 링크를 제공한다. -->
	<a href="regist.jsp">추가등록</a>
>>>>>>> 7b17fbba4bd746c51ad3e90a5036e13d140e51be
</body>
</html>