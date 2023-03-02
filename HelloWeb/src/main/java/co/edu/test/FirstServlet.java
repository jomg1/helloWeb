package co.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 자바소스 : http 통신방법을 활용해서 request(요청 정보), response(응답 정보) 생성 처리
// HttpServlet 클래스를 상속받아서 사용
// 서블릿의 실행 : 개발자X, Tomcat 컨테이너가 제어(Inversion Of Control:제어의 역전)
// 순서 : 객체 생성 -> init메소드 실행 -> service 메소드 실행 -> destroy 메소드 실행
public class FirstServlet extends HttpServlet {
	
	public FirstServlet() {
		//생성자 호출은 서블릿이 호출될 때 한 번만 실행
		System.out.println("생성자 호출. FirstServlet");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//init 호출은 서블릿이 호출될 때 한 번만 실행
		System.out.println("init 호출. FirstServlet");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//service 호출은 서블릿이 호출될 때 마다 실행
		System.out.println("service 호출. FirstServlet");
		
		if (req.getMethod().equals("GET")) {
			doGet(req, resp);
		}else if (req.getMethod().equals("POST")) {
			doPost(req, resp);
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 호출. FirstServlet");
	}
	
	// 클라이언트 -> 서버요청 : get or post
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get : 주소창-url, a(tag) href="", <form method = "get">
		// 특징 : url 사용자가 입력값 노출, 입력 용량 제한
		//응답정보 -> 클라이언트측
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter(); // 출력스트림 생성
		out.print("<h3>Get방식요청</h3>");
		//사용자가 입력했던 값을 읽어오는 방법
		String id = req.getParameter("id"); // id=guest&pw=1234
		String pw = req.getParameter("pw");
		
		out.print("<p>입력id : " + id + ", 비번: " + pw + "</p>");
		
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter(); // 출력스트림 생성
		out.print("<h3>POST방식요청</h3>");
		
		String id = req.getParameter("id"); // id=guest&pw=1234
		String pw = req.getParameter("pw");
		
		out.print("<p>입력id : " + id + ", 비번: " + pw + "</p>");
		
		out.close();
		
	}
}
