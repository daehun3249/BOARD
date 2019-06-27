<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>환영합니다.</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<style>
	html, body
		{
		    height: 100%;
		}
		
		body
		{
		    display: table;
		    margin: 0 auto;
		}
		
		.outter
		{
		    height: 100%;
		    display: table-cell;
		    vertical-align: middle;
		}
		
		.inner
		{
		    height: 200px;
		    width: 200px;      
		}
</style>

</head>

		
<body>
	<c:if test="${empty member}">
		<div class="outter">
			<div class="inner">
				<table class="table">
					<tr>
						<td align="center">로그인 하십시오.</td>
					</tr>
					<tr>
						<td align="center">
							<a class="btn btn-primary btn-sm" href="<c:url value="/login" />">로그인</a>
							<a class="btn btn-primary btn-sm" href="<c:url value="/join" />">회원가입</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty member}">
		<div class="outter">
			<div class="inner">
				<table class="table">
					<tr>
						<td align="center" width="300">환영합니다. ${member.name}님!</td>
					</tr>
					<tr>
						<td align="center" width="300">
							<a class="btn btn-primary btn-sm" href="<c:url value="/change" />">비밀번호 변경</a>
							<a class="btn btn-primary btn-sm" href="<c:url value="/logout" />">로그아웃</a>
						</td>
					</tr>
					<tr>
						<td align="center"><a class="btn btn-primary btn-sm" href="<c:url value="/list" />">게시판으로 이동</a></td>
					</tr>
				</table>
			</div>
		</div>
	</c:if>
</body>
</html>