package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {
   // 입력폼
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   // session 유효성 검사
      HttpSession session = request.getSession();
      if(session.getAttribute("loginMember") == null) {
         response.sendRedirect(request.getContextPath() + "/login");
         return;
      } 
	  
      Member member = (Member)session.getAttribute("loginMember");
      
      // request 매개값
      int targetYear = Integer.parseInt(request.getParameter("targetYear"));
      int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
      int targetDate = Integer.parseInt(request.getParameter("targetDate"));
      
      request.setAttribute("targetYear", targetYear);
      request.setAttribute("targetMonth", targetMonth);
      request.setAttribute("targetDate", targetDate);
      request.setAttribute("memberId", member.getMemberId());
      // 나머지 데이터는 입력폼에서 사용자가 입력
      
      request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   
	   // request 매개값
	   Cashbook cashbook = new Cashbook();
	   
	   // Cashbook 객체 채우기
	   cashbook.setMemberId(request.getParameter("memberId"));
	   cashbook.setCategory(request.getParameter("category"));
	   cashbook.setCashbookDate(request.getParameter("cashbookDate"));
	   cashbook.setPrice(Integer.parseInt(request.getParameter("price")));
	   cashbook.setMemo(request.getParameter("memo"));
	   
	   
	   CashbookDao cashbookDao = new CashbookDao();
	   int cashbookNo = cashbookDao.insertCashbook(cashbook); // 키값 반환
	   
	   int targetYear = Integer.parseInt(request.getParameter("targetYear"));
	   int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
	   int targetDate = Integer.parseInt(request.getParameter("targetDate"));
	   
	   // 입력실패시
	   if (cashbookNo == 0) {
	      System.out.println("입력실패");
	      response.sendRedirect(request.getContextPath() + "/addCashbook?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate);
	      return;
	   }
	   // 입력성공시 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
	   HashtagDao hashtagDao = new HashtagDao();
	   String memo = cashbook.getMemo();
	   String memo2 = memo.replace("#", " #"); //#구디#아카데미 -> #구디 #아카데미
	   String[] hashtags = memo2.trim().split(" ");
	   for (String ht : hashtags) {
	      String ht2 = ht.replace("#", "");
	      if (ht2.length() > 0) {
	         Hashtag hashtag = new Hashtag();
	         hashtag.setCashbookNo(cashbookNo);
	         hashtag.setWord(ht2);
	         try {
	            int row = hashtagDao.insertHashtag(hashtag);
	            if (row == 0) {
	               String msg = URLEncoder.encode("해시태그 입력 실패.", "UTF-8");
	               request.setAttribute("msg", msg);
	               request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
	               return;
	            } else if (row == 1) {
	               response.sendRedirect(request.getContextPath() + "/cashbook?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate);
	               return;
	            } else {
	               System.out.println("add hashtag error");
	            }
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	   }
	}

} 
