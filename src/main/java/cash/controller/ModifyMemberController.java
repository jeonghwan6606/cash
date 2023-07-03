package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/modifyMember")
public class ModifyMemberController extends HttpServlet {
       
	
    // 비밀번호 입력 폼
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //session 유효성 검사(null)
	      HttpSession session = request.getSession();
	      if(session.getAttribute("loginMember") == null) {
	         response.sendRedirect(request.getContextPath() + "/login");
	         return;
	      }
			//모델 값 구하기(dao 매서드를 호출)
	
			
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectMemberOne(loginMember.getMemberId());
			//member 출력하는(포워딩 대상)memberOne.jsp에도 공유되어야한다
			
			//request 정해진 api 매개값에 같이 넣어서 공유하여야 한다.
			request.setAttribute("member", member);
		 request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
		
	}

	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String newPassword = request.getParameter("newPassword");
	     String newPasswordCk = request.getParameter("newPasswordCk");
	     String memberPwCk = request.getParameter("memberPwCk");

	     HttpSession session = request.getSession();
	     
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectMemberOne(loginMember.getMemberId());
			//member 출력하는(포워딩 대상)memberOne.jsp에도 공유되어야한다
			
		
			
	     // 새 비밀번호와 비밀번호 확인이 일치하는지 확인
	     if (!newPassword.equals(newPasswordCk)) {
	         String msg = URLEncoder.encode("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.", "UTF-8");
	         request.setAttribute("msg", msg);
	         
	         
	         request.setAttribute("member", member);
	         
	         request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	         return;
	     }

	     // 현재 비밀번호 확인

	   
	     String memberId = loginMember.getMemberId();
	     

	     Member paramMember = new Member(memberId, memberPwCk, null, null);
	     Member selectedMember = memberDao.selectMemberById(paramMember);

	     // 현재 비밀번호가 일치하지 않는 경우
	     if (selectedMember == null) {
	         String msg = URLEncoder.encode("현재 비밀번호가 일치하지 않습니다.", "UTF-8");
	         request.setAttribute("msg", msg);
	         
	         request.setAttribute("member", member);
	         
	         request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	         return;
	     }

	     // 새 비밀번호로 회원 비밀번호 수정
	     try {
	         int updateRow = memberDao.updateMember(memberId, newPassword,memberPwCk);
	         if (updateRow == 1) {
	             String msg = URLEncoder.encode("회원 비밀번호가 성공적으로 수정되었습니다.", "UTF-8");
	             response.sendRedirect(request.getContextPath()+"/cashbook");
	             return;
	         } else {
	        	
	             System.out.println("update member password error");
	             
	             request.setAttribute("member", member);
	             request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
		         return;
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }
}
