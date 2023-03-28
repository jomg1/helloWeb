package co.dev.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.dev.common.Control;
import co.dev.service.MemberService;
import co.dev.service.MemberServiceMybatis;
import co.dev.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String id = req.getParameter("uid");
		String pw = req.getParameter("upw");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPasswd(pw);
		System.out.println("before: " + vo);
		
		MemberService service = new MemberServiceMybatis();
		vo = service.login(vo);
		System.out.println("after: " + vo);
		
		// session 객체는 웹브라우저별로 계속 유지되는 정보를 담아놓기 좋은 객체
		HttpSession session = req.getSession();//요청정보에 있는 세션정보가 있으면 호출 없으면 세션 객체 생성
							  //반환 타입이 HttpSession이므로 해당 세션 객체 생성함
		session.setAttribute("id", vo.getId()); //로그인 한 사람의 id값을 세션 객체에 담아둔다
		
		//로그인 처리가 되면 목록으로 이동
		try {
			resp.sendRedirect("noticeList.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
