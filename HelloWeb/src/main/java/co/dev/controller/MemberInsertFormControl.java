package co.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.common.Control;

public class MemberInsertFormControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//회원등록화면 호출
		//리퀘스트디스패쳐 사용
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/member/memberInsert.jsp");
		try {
			rd.forward(req, resp);//요청 재지정
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
