package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;


@WebServlet("/memberOne")
public class MemberOneController extends HttpServlet {
	
       

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		//모델 값 구하기(dao 매서드를 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		//member 출력하는(포워딩 대상)memberOne.jsp에도 공유되어야한다
		
		//request 정해진 api 매개값에 같이 넣어서 공유하여야 한다.
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		 
		Member memberOne = (Member)request.getAttribute("member");
	}

}
