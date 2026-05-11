<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath }" />
<style>
a {
text-decoration: none;

}
</style>
<div>
	<span> <a href="${root }/restaurant?action=index">홈으로</a></span> |
	<span> <a href="${root }/restaurant?action=list">목록 조회</a></span> | 

	<c:choose>
		<c:when test="${empty sessionScope.loginUser}">
			<span><a href="${root}/restaurant?action=loginForm">로그인</a></span>
		</c:when>

		<c:otherwise>
			<span>안녕하세요. ${sessionScope.loginUser.name} 님.</span> |
			<span><a href="${root}/restaurant?action=logout">로그아웃</a></span>
		</c:otherwise>
	</c:choose>
</div>
<hr />
<script type="text/javascript">
	const alertMsg = `${param.alertMsg}` || `${alertMsg}`; // 메시지 자체에 ""가 포함 되기도 함
	if (alertMsg) {
		alert(alertMsg);
	}
</script>
