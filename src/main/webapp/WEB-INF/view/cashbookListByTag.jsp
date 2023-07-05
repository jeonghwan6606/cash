<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>Cashbook List</title>
</head>
<body>
   <h1>Cashbook List</h1>
   <table>
      <thead>
         <tr>
            <th>Cashbook No</th>
            <th>Member ID</th>
            <th>Category</th>
            <th>Cashbook Date</th>
            <th>Price</th>
            <th>Memo</th>
            <th>Created Date</th>
            <th>Updated Date</th>
         </tr>
      </thead>
      <tbody>
         <c:forEach var="cashbook" items="${list}">
            <tr>
               <td>${cashbook.cashbookNo}</td>
               <td>${cashbook.memberId}</td>
               <td>${cashbook.category}</td>
               <td>${cashbook.cashbookDate}</td>
               <td>${cashbook.price}</td>
               <td>${cashbook.memo}</td>
               <td>${cashbook.createdate}</td>
               <td>${cashbook.updatedate}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   
   <div>
      <c:choose>
         <c:when test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/cashbookListByTag?page=${currentPage - 1}&word=${param.word}">Previous</a>
         </c:when>
         <c:otherwise>
            <span>Previous</span>
         </c:otherwise>
      </c:choose>
      
      <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
         <c:choose>
            <c:when test="${pageNumber == currentPage}">
               <strong>${pageNumber}</strong>
            </c:when>
            <c:otherwise>
               <a href="${pageContext.request.contextPath}/cashbookListByTag?page=${pageNumber}&word=${param.word}">${pageNumber}</a>
            </c:otherwise>
         </c:choose>
      </c:forEach>
      
      <c:choose>
         <c:when test="${currentPage < totalPageCount}">
            <a href="${pageContext.request.contextPath}/cashbookListByTag?page=${currentPage + 1}&word=${param.word}">Next</a>
         </c:when>
         <c:otherwise>
            <span>Next</span>
         </c:otherwise>
      </c:choose>
   </div>
</body>
</html>