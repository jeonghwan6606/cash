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

@WebServlet("/removeMember")
public class removeMemberController extends HttpServlet {
       
	
    // 비밀번호 입력 폼
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //session 유효성 검사(null)
	      HttpSession session = request.getSession();
	      if(session.getAttribute("loginMember") == null) {
	         response.sendRedirect(request.getContextPath() + "/login");
	         return;
	      }
		 
		 request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
		
	}

	// 탈퇴
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String msg = null;
		 if(request.getParameter("memberPw") == null
				 ||request.getParameter("memberPw").equals("")
				 ||request.getParameter("memberPwCk") == null
				 ||request.getParameter("memberPwCk").equals("")
				 ) { 
				System.out.println("비밀번호입력 실패");
				
				msg = URLEncoder.encode("비밀번호입력 실패.", "UTF-8");
				request.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath()+"/removeMember");
				return;
			}
		 
		 
		 
		String memberPw = request.getParameter("memberPw");
		String memberPwCk = request.getParameter("memberPwCk");
		
		 if(!memberPw.equals(memberPwCk)
				 ) { 
				System.out.println("비밀번호확인 실패");
				
				msg = URLEncoder.encode("비밀번호확인 실패.", "UTF-8");
				request.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath()+"/removeMember");
				return;
			}
		
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		String memberId = loginMember.getMemberId();
		
		Member member = new Member(memberId, memberPw, null, null);
			
	//회원탈퇴 DAO호출
	      MemberDao memberDao = new MemberDao();
	      try {
		      int row = memberDao.removeMember(member);
		      if (row == 0) { 
		    	msg = URLEncoder.encode("비밀번호를 확인해주세요.", "UTF-8");
				 request.setAttribute("msg", msg); 
				 
		         request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
		         return;
		      } else if (row == 1) {
		    	  // login.jsp view를 이동하는 controller를 리다이렉트
		    	  
			  		//탈퇴성공
			  	session.invalidate();
			  	msg = URLEncoder.encode("회원탈퇴에 성공하였습니다.", "UTF-8");
		         response.sendRedirect(request.getContextPath() + "/login&msg="+msg);
		         return;
		      } else {
		         System.out.println("remove member error");
		      }
	      }catch (Exception e) {
		    	    // 예외 처리
		    	    e.printStackTrace();		  
		  }
   			
	}

}
