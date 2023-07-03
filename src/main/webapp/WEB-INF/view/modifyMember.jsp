<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="cash.vo.Member" %>
<%
	Member member = (Member) request.getAttribute("member");
%>
<html>
<head>
    <title>Modify Member</title>
</head>
<body>
   <h1>회원정보 수정</h1>
	<form method="post" action="${pageContext.request.contextPath}/modifyMember">
    
	    <table>
	        <tr>
	            <th>Member ID</th>
	            <td><%= member.getMemberId() %></td>
	        </tr>
	        <tr>
	            <th>New Password</th>
	            <td><input type="text" name="newPassword"></td>
	        </tr>
	         <tr>
	            <th>New PasswordCk</th>
	            <td><input type="text" name="newPasswordCk"></td>
	        </tr>
	        <tr>
	            <th>Current PasswordCk</th>
	            <td><input type="text" name="memberPwCk"></td>
	        </tr>
	    </table>
	    
	    <button type="submit">회원정보수정</button>
    </form>
</body>
</html>