<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>삭제하시겠습니까?</title>
</head>
<body>
삭제하시겠습니까?
<a href="<c:url value="/deleteSuccess/${no}" />">[예]</a>
<a href="<c:url value="/read/${no}" />">[아니오]</a>
</body>
</html>