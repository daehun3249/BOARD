<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<script type="text/javascript">
var message = '${message}';
var returnUrl = '${returnUrl}';
if(!confirm(message)) {
	history.go(-1);
} else {
	document.location.href = returnUrl;
}
</script>
</body>
</html>