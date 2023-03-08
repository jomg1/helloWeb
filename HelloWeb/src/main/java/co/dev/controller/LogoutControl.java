package co.dev.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.dev.common.Control;

public class LogoutControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();// 요청정보에 있는 세션 객체를 가지고 오는 메소드
		session.invalidate(); // 세션 정보를 지우는 메소드
		
		//이동하고 싶은 페이지
		try {
			resp.sendRedirect("noticeList.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
