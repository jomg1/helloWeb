package co.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.common.Control;
import co.dev.service.NoticeService;
import co.dev.service.NoticeServiceMybatis;
import co.dev.vo.NoticeVO;

public class NoticeSearchControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String nid = req.getParameter("nid");
		
		NoticeService service = new NoticeServiceMybatis();
		NoticeVO vo = service.getNotice(Integer.parseInt(nid));
		
		//vo에 담긴 값을 WEB-INF/notice/notice.jsp에서 출력.
		System.out.println(vo); 
		req.setAttribute("notice", vo);//notice라는 atrribute에 참조값 vo를 담아서 넘긴다..
		
		try {
			req.getRequestDispatcher("WEB-INF/notice/notice.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
