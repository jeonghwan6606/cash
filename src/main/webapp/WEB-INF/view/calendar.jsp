<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar</title>
	<style>
		table, th, td { border: 1px solid gray; }
		table { border-collapse: collapse; width: 90%; }
	</style>
</head>
<body>
	<!-- 변수값 or 반환값 : EL 사용 중  -->
	<!-- 
		속성값 대신 EL 사용
		ex)
		request.getAttribute("targetYear") -- ${targetYear}
		(requestScope는 생략 가능)
		형변환 연산이 필요 없음 (EL이 자동으로 처리)
	 -->
    <h1>Calendar</h1>
    
 	<!-- 자바코드(제어문) : JSTL 사용 -->
    <h2>
        ${targetYear}년 ${targetMonth+1}월
    </h2>
    <table class="table">
        <tr>
            <th>일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
        </tr>
        <!--  
        beginBlank: 시작 공백 개수, lastDate: 출력되는 월의 마지막 날짜, totalCell: 전체 출력 셀 개수
        endBlank: 마지막 공백 개수, targetMonth: 출력되는 월 
        -->
        <c:forEach var="row" begin="0" end="${totalCell/7 - 1}">
            <tr>
                <c:forEach var="col" begin="0" end="6">
                    <td>
                        <c:choose>
                            <c:when test="${(row == 0 && col < beginBlank) || day > lastDate}">
                                &nbsp;
                            </c:when>
                            <c:otherwise>
                                ${day}
                                <c:set var="day" value="${day+1}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
        <div>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=$(targetYear)&targetMonth=$(targetMonth)">이전</a>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=$(targetYear)&targetMonth=$(targetMonth)>">다음</a>
	</div>	
</body>
</html>