<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 목록</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성날짜</td>
				<td>조회수</td>
			</tr>
			<c:if test="${!empty boardList}">
			<c:forEach var="boardList" items="${boardList}">
			<tr>
				<td>${boardList.postNumber}</td>
				<td><a href="<c:url value="/read/${boardList.postNumber}" />">${boardList.title}</a></td>
				<td>${boardList.name}</td>
				<td><tf:formatDateTime pattern="yyyy-MM-dd HH:mm:ss" value="${boardList.regDate}" /></td>
				<td>${boardList.hits}</td>
			</tr>
			</c:forEach>
			</c:if>
		</table>
		<a class="btn btn-default" href="<c:url value="/write" />">글쓰기</a>
		<a class="btn btn-default" href="<c:url value="/" />">메인으로</a>
	</div>
</body>
</html>