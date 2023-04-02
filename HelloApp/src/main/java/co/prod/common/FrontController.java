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

import co.prod.controller.ChartAjax;
import co.prod.controller.ChartControl;
import co.prod.controller.CovidForm;
import co.prod.controller.FullControl;
import co.prod.controller.MapForm;
import co.prod.controller.MemberAddAjax;
import co.prod.controller.MemberListAjax;
import co.prod.controller.MemberListControl;
import co.prod.controller.MembersControl;
import co.prod.controller.ProductInfoControl;
import co.prod.controller.ProductListControl;
import co.prod.controller.ReplyAddAjax;
import co.prod.controller.ReplyListAjax;
import co.prod.controller.ReplyModifyAjax;
import co.prod.controller.ReplyRemoveAjax;
import co.prod.controller.ReplySearchAjax;
import co.prod.controller.ScheduleAddAjax;
import co.prod.controller.ScheduleAjax;
import co.prod.controller.ScheduleRemoveAjax;
import co.prod.controller.memberRemoveAjax;

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
		// Ajax 호출(SPA 처리)
		map.put("/memberListAjax.do", new MemberListAjax());
		map.put("/memberRemoveAjax.do", new memberRemoveAjax());
		map.put("/memberAddAjax.do", new MemberAddAjax());
		
		// 상품목록. (프로그램 만들 때 순서 목록, 등록, 수정 순서로 만들어보기)
		map.put("/productList.do", new ProductListControl());
		// 상품한건정보
		map.put("/productInfo.do", new ProductInfoControl());
		
		// 상품 댓글 정보 목록
		map.put("/replyListAjax.do", new ReplyListAjax());
		// 상품 댓글 삭제
		map.put("/replyRemoveAjax.do", new ReplyRemoveAjax());
		// 상품 댓글 등록
		map.put("/replyAddAjax.do", new ReplyAddAjax());
		// 상품 댓글 번호 조회
		map.put("/replySearchAjax.do", new ReplySearchAjax());
		// 상품 댓글 수정
		map.put("/replyModifyAjax.do", new ReplyModifyAjax());
		//chart
		map.put("/chart.do", new ChartControl());
		// chart 데이터
		map.put("/chartAjax.do", new ChartAjax());
		// covid
		map.put("/covid19.do", new CovidForm());
		// map api
		map.put("/map.do", new MapForm());
		// fullcaleandar api
		map.put("/fullcalendar.do", new FullControl());
		// 일정 데이터
		map.put("/schedule.do", new ScheduleAjax());
		// 일정 추가
		map.put("/scheduleAdd.do", new ScheduleAddAjax());
		// 일정 삭제
		map.put("/scheduleRemove.do", new ScheduleRemoveAjax());
		
	}
	
	@Override
	//실제 실행되는 기능은 service에
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
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
		} else if(viewPage.endsWith(".ajax")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().append(viewPage.substring(0, viewPage.length()-5));
			return; // 해당 메소드를 끝냄.
		}
		// 페이지를 재지정(이동시켜줌)
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp); // product/ProductList.tiles
	}
	
}
