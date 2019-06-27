<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 읽기</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>
<body>
<div class="container">
<table class="table table-bordered">
	<tr>
		<td align="center">제목</td>
		<td>${content.title}</td>
		<td align="center">작성자</td>
		<td>${content.name}</td>
		<td align="center">작성날짜</td>
		<td><tf:formatDateTime pattern="yyyy-MM-dd HH:mm:ss" value="${content.regDate}" /></td>
	</tr>
	<tr>
		<td align="center">내용</td>
		<td colspan="5">${content.content}</td>
	</tr>
</table>
<br/> <br/>

<hr />
<!-- 댓글 읽기 기능 구현 -->
Comment <br/> <br/>
<c:if test="${!empty readReply}">
	<c:forEach var="readReply" items="${readReply}">
			<span class="glyphicon glyphicon-star"></span> ${readReply.name} (<tf:formatDateTime pattern="yyyy-MM-dd HH:mm:ss" value="${readReply.regDate}" />)
			<br/>
			${readReply.reply}
			<br/>
			<a class="btn btn-default btn-sm" href="<c:url value="/replyModify/${no}/${readReply.number}" />">수정</a><a class="btn btn-default btn-sm" href="<c:url value="/replyDelete/${no}/${readReply.number}" />">삭제</a>
			<br/>
			<br/>
	</c:forEach>
</c:if>

<hr/>

<br/><br/>
<form class="form-horizontal" action="/read/${no}" method="post">
	<input type="hidden" name="postNumber" value="${no}" />
	<input type="hidden" name="name" value="${member.name}" />
	<textarea class="form-control" name="reply"></textarea>
	<br/>
	<input class="btn btn-default" type="submit" value="댓글 달기" />
</form>
<br/>

<a class="btn btn-default btn-sm" href="<c:url value="/list" />">목록</a>
<a class="btn btn-default btn-sm" href="<c:url value="/modify/${no}" />">수정</a>
<a class="btn btn-default btn-sm" href="<c:url value="/delete/${no}" />">삭제</a>
</div>
</body>
</html>