package cash.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사
			
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘날짜
		
		int targetYear= firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		firstDay.set(Calendar.DATE, 1);
		
		// 출력하고자하는 년도와 월이 매개값으로 넘어왔다면 바꾸어 준다
		if(request.getParameter("targetYear") != null 
				&& request.getParameter("targetMonth") != null) {
			 targetYear = Integer.parseInt(request.getParameter("targetYear"));
			 targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			firstDay.set(Calendar.YEAR, targetYear);
			firstDay.set(Calendar.MONTH, targetMonth);
		}
		
		
		
		// 달력출력시 시작 공백 개수 -> 1일날짜의 요일(일1, 월2,...토6) - 1
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(beginBlank+" <- beginBlank");
		
		// 출력되는 월의 마지막날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		System.out.println(lastDate+" <- lastDate");
		
		// 달력출력시 마지막 날짜 출력 후 공백 개수 -> 전체 출력 셀(totalCell)의 개수가 7로 나누어 떨어져야 한다
		int endBalnk = 0;
		if(((beginBlank+lastDate)%7) != 0) {
			endBalnk = 7 - ((beginBlank+lastDate)%7);
		}
		int totalCell = beginBlank+lastDate+endBalnk;
		System.out.println(totalCell+" <- totalCell");
		System.out.println(endBalnk+" <- endBalnk");
		
		//뷰에 값넘기기(request. 속성)
		request.setAttribute
		
		//
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}	
}
