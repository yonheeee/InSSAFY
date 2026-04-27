<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 등록 페이지</title>
</head>
<body>
	
	<nav>
	<%@ include file="/header.jsp"%>
		<h1>등록 페이지</h1>

	</nav>
	<form action="${pageContext.servletContext.contextPath }/restaurant" method="POST">
	<input type="hidden" name="action" value="regist">
		<fieldset>
			<legend>맛집 리뷰 등록</legend>

			<p>
				<label> 맛집 코드 <input type="text" name="code" placeholder="예: R001" required maxlength="15">
				</label>
			</p>

			<p>
				<label> 맛집 이름 <input type="text" name="name"
					placeholder="식당명을 입력하세요" required>
				</label>
			</p>

			<p>
				<label> 카테고리 <select name="category">
						<option value="한식">한식</option>
						<option value="중식">중식</option>
						<option value="일식">일식</option>
						<option value="양식">양식</option>
						<option value="기타">기타</option>
				</select>
				</label>
			</p>

			<p>
				<label> 별점 <input type="number" name="rating" min="1" max="5" value="5"> (1~5점)
				</label>
			</p>

			<p>
				<label> 대표 리뷰 <br> <textarea name="review" rows="4"
						cols="30" placeholder="리뷰 내용을 작성해주세요"></textarea>
				</label>
			</p>

			<p>
				<label> 등록일 <input type="date" name="regDate" required>
				</label>
			</p>

			<input type="submit" value="맛집 등록"> <br>
			<br> <a href="${pageContext.servletContext.contextPath }/restaurant?action=list">목록으로
				돌아가기</a>
		</fieldset>
	</form>
</body>
</html>