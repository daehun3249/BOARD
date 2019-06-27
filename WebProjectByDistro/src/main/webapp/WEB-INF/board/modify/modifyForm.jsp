<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<form action="<c:url value="/modify/${no}" />" method="post">
	<div class="form-group">
		<label for="title">제목</label>
			<input type="text" class="form-control" name="title" value="${content.title}" />
	</div>
	<div class="form-group">
		<label for="content">내용</label>
			<textarea name="content" class="form-control" rows="10">${content.content}</textarea>
	</div>
	<br/>
	<hr/>
	<br/>
		<input class="btn btn-default" type="submit" value="수정" />
</form>
</div>
</body>
</html>