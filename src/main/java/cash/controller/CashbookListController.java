package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.vo.Cashbook;


@WebServlet("/cashbookListByTag")
public class CashbookListController extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // session 구현
      String memberId = "user";
      
      String word = request.getParameter("word");
      
      int currentPage = 1;
      int rowPerPage = 10;
      
      // 현재 페이지 파라미터 가져오기
      String pageParam = request.getParameter("page");
      if (pageParam != null) {
         currentPage = Integer.parseInt(pageParam);
      }
      
      // 페이징을 위한 시작 행 계산
      int beginRow = (currentPage - 1) * rowPerPage;
      
      CashbookDao cashbookDao = new CashbookDao();
      List<Cashbook> list = cashbookDao.selectCashbookListByTag(memberId, word, beginRow, rowPerPage);
      
      request.setAttribute("list", list);
      
      // 전체 데이터 개수 조회
      int totalRowCount = cashbookDao.getSearchCount(memberId, word);
      
      // 전체 페이지 개수 계산
      int totalPageCount = (int) Math.ceil((double) totalRowCount / rowPerPage);
      
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("totalPageCount", totalPageCount);
      
      request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
   }
}