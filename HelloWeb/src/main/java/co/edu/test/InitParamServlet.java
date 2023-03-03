package co.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParamServlet extends HttpServlet{
	String id;
	String pw;
	
	public InitParamServlet() {
		System.out.println("InitParamServlet 생성자 호출.");
	}
	
	//init method
	@Override
	public void init(ServletConfig config) throws ServletException {
		//ServletConfig 객체 생성
		id = config.getInitParameter("id");
		pw = config.getInitParameter("password");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청, 응답 객체 생성
		resp.setContentType("text/plain;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.print("파라미터Id: " + id);
		out.print("파라미터pw: " + pw);
		out.close();
	}
}
