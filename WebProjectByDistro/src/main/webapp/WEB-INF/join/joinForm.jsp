<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>

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
		    height: 426px;
		    width: 200px;      
		}
</style>

</head>
<body>
	<div class="outter">
		<div class="inner">
			<form action="join" method="post">
				<p class="text-center">회원가입</p>
				<div class="form-group">
					<label for="InputId">ID</label>
					<input class="form-control" type="text" name="id" placeholder="ID를 입력하세요." />
				</div>
				<div class="form-group">
					<label for="InputPassword">암호</label>
					<input class="form-control" type="password" name="password" placeholder="암호" />
				</div>
				<div class="form-group">
					<label for="InputConfirmPassword">암호 확인</label>
					<input class="form-control" type="password" name="confirmPassword" placeholder="암호 확인" />
				</div>
				<div class="form-group">
					<label for="InputEmail">이메일</label>
					<input class="form-control" type="text" name="email" placeholder="이메일" />
				</div>
				<div class="form-group">
					<label for="InputName">이름</label>
					<input class="form-control" type="text" name="name" placeholder="이름" />
				</div>
				<input type="submit" class="btn btn-default" value="가입" />
			</form>
		</div>
	</div>
</body>
</html>