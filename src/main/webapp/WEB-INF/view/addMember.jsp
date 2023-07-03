<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원가입</h1>
	<form method="post" action="${pageContext.request.contextPath}/addMember">
	<table>
		<tr>
			<td>id</td>
			<td><input type="text" name="memberId"></td>
		</tr>
		<tr>
			<td>pw</td>
			<td><input type="text" name="memberPw"></td>
		</tr>
	</table>
	<button type="submit">회원가입</button>
	</form>
</body>
</html>