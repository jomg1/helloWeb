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

public class NoticeListControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//noticeList 호출하려면 파라미터로 page 정보를 넘겨야 함
		String page = req.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		
		//글목록. mybatis 활용 목록 가져오기
		//mybatis-config.xml에 매퍼 파일 등록
		NoticeService service = new NoticeServiceMybatis();
		List<NoticeVO> list = service.noticeList(Integer.parseInt(page));// 공지사항 목록
		
		req.setAttribute("list", list); //list 객체의 정보를 "list"라는 이름으로 넘겨줌
		
		try {
			req.getRequestDispatcher("WEB-INF/notice/noticeList.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}//이동할 페이지+포워딩
	}

}
