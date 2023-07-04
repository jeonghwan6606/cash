<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Cashbook</title>
</head>
<body>
    <h1>Add Cashbook</h1>
    <form action="${pageContext.request.contextPath}/addCashbook" method="post">
        <label for="memberId">Member ID:</label>
        <input type="text" id="memberId" name="memberId"  value="${memberId}" readonly="readonly"><br>
        
        <label for="category">Category:</label>
        <select id="category" name="category" required>
            <option value="수입">수입</option>
            <option value="지출">지출</option>
        </select><br>
        
        <label for="cashbookDate">Date:</label>
       	 <input type="date" id="cashbookDate" name="cashbookDate" value="${targetYear}-${String.format("%02d", targetMonth+1)}-${String.format("%02d", targetDate)}" required><br>
        
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required><br>
        
        <label for="memo">Memo:</label>
        <textarea id="memo" name="memo" required></textarea><br>
        
        <input type="hidden" name="targetYear" value="${targetYear}">
        <input type="hidden" name="targetMonth" value="${targetMonth}">
        <input type="hidden" name="targetDate" value="${targetDate}">
        <input type="submit" value="Add Cashbook">
    </form>
</body>
</html>