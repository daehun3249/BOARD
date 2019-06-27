<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>암호 변경</title>

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
			<form action="change" method="post">
				<div class="form-group">
					<label for="inputOldPassword">이전암호</label>
					<input type="password" class="form-control" name="oldPassword" placeholder="이전암호">
				</div>
				<div class="form-group">
					<label for="inputNewPassword">새암호</label>
					<input type="password" class="form-control" name="newPassword" placeholder="새암호">
				</div>
				<input type="submit" class="btn btn-default" value="암호변경"/>
			</form>
		</div>
	</div>
</body>
</html>