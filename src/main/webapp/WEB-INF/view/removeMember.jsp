<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴 비밀번호</h1>
	<form method="post" action="${pageContext.request.contextPath}/removeMember">
	<table border="1">
		<tr>
			<td>memberPw</td>
			<td><input type="password" name="memberPw"></td>
		</tr>
		<tr>
			<td>memberPwCk</td>
			<td><input type="password" name="memberPwCk"></td>
		</tr>
	</table>
	<button type ="submit">회원탈퇴</button>
	</form>
</body>
</html>