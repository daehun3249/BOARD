<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인</title>

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
	<div class="outter">
		<div class="inner">
			<form action="login" method="post">
				<div class="form-group">
					<label for="inputId">ID</label>
					<input type="text" class="form-control" name="id" placeholder="ID를 입력하세요.">
				</div>
				<div class="form-group">
					<label for="inputPassword">암호</label>
					<input type="password" class="form-control" name="password" placeholder="암호">
				</div>
				<input type="submit" class="btn btn-default" value="로그인"/>
			</form>
		</div>
	</div>
</body>
</html>