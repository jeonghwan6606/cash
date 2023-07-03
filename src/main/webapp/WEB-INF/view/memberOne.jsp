<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="cash.vo.Member" %>
<%
	Member member = (Member) request.getAttribute("member");
%>
<html>
<head>
    <title>Member Details</title>
</head>
<body>
    <h1>회원정보 상세</h1>
    
    <table>
        <tr>
            <th>Member ID</th>
            <td><%= member.getMemberId() %></td>
        </tr>
        <tr>
            <th>Createdate</th>
            <td><%= member.getCreatedate()%></td>
        </tr>
        
    </table>
    
    <a href="${pageContext.request.contextPath}/modifyMember">회원정보수정</a>
    <a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
</body>
</html>