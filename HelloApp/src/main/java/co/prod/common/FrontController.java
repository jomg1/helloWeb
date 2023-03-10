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
import co.prod.controller.ProductInfoControl;
import co.prod.controller.ProductListControl;

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
		
		// 상품목록. (프로그램 만들 때 순서 목록, 등록, 수정 순서로 만들어보기)
		map.put("/productList.do", new ProductListControl());
		// 상품한건정보
		map.put("/productInfo.do", new ProductInfoControl());
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
		
		//viewPage에 command 값이 들어가면 FrontController에서 해당 값을 찾는다.
		//FrontController에 지정된 리턴값이 .tiles / .jsp에 따라 아래 조건문을 따른다. 
		
		if(viewPage.endsWith(".jsp")) { // memberList.do
			viewPage = "/WEB-INF/views/" + viewPage;
//		} else if (viewPage.endsWith(".titles")) {
//			viewPage = "/" + viewPage;
		}
		// 페이지를 재지정(이동시켜줌)
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp); // product/ProductList.tiles
	}
	
}
