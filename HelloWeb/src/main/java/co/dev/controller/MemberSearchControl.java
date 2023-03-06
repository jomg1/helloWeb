package co.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.common.Control;
import co.dev.service.MemberService;
import co.dev.service.MemberServiceImpl;
import co.dev.vo.MemberVO;

public class MemberSearchControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//파라미터 읽어오기
		String id = req.getParameter("id"); //memberSearchForm.jsp의 id값이 파라미터, 해당 값을 넘김
		String job = req.getParameter("job"); //어떤 페이지에서 호출한 것인지 알기 위한 파라미터
		
		//id를 활용해서 MemberVO 객체를 반환하는 메소드(memberService, MemberDAO)
		 MemberService service = new MemberServiceImpl();
		 MemberVO result = service.getMember(id); //result라는 값을 memberSearchOutput.jsp에 넣어야함
		 
		 req.setAttribute("vo", result);
		 
		 String targetPage="";
		 //조회된 결과가 없을 경우: 조회, 수정, 삭제...
		 if(job.equals("search")) {
			 if (result==null) {//조회된 결과가 없을 경우
				 req.setAttribute("message", "조회된 결과가 없습니다");
				targetPage="WEB-INF/member/memberSearchForm.jsp";
			 }else {
				 targetPage="WEB-INF/member/memberSearchOutput.jsp";
			 }
		 }else if(job.equals("update")) {
			 targetPage="WEB-INF/member/memberUpdate.jsp";
		 }else if(job.equals("delete")) {
			 targetPage="WEB-INF/member/memberDelete.jsp";
		 }
		 
		 
		 
		 //페이지 지정.
		 try {
			req.getRequestDispatcher(targetPage).forward(req, resp);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		 
	}

}
