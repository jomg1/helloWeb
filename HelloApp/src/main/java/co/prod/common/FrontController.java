package co.prod.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.controller.MemberListControl;
import co.prod.controller.MembersControl;

public class FrontController extends HttpServlet{

	private Map<String, Control> map;
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// url <-> control 객체 값을 넣어주는 기능 구현
		map.put("/memberList.do", new MemberListControl());
		map.put("/members.do", new MembersControl());
	}
	
	@Override
	//실제 실행되는 기능은 service에
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
//		System.out.println(uri); //헷갈리면 로그 찍어서 확인해보기
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println("do page: " + page); // 확인용
		
		Control command = map.get(page); // 커맨드 객체 , memberList.do를 치면
		String viewPage = command.exec(req, resp); //memberList.do의 exec값이 넘어오는 것.
												   //"member/member.jsp"
		
		if(viewPage.endsWith(".jsp")) {
			viewPage = "./" + viewPage;
//		} else if (viewPage.endsWith(".titles")) {
			
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}
	
}
