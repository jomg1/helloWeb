package co.dev.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	// url 패턴과 실행 컨트롤러 등록 (하나의 클래스 안에서 관리하기 위함)
	// url - controller : map 등록
	Map<String, Control> map = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl());
		map.put("/login.do", new LoginControl());
		//회원목록
		map.put("/memberList.do", new MemberListControl());
		//사원목록
		//map.put("/empList.do", new EmpListControl()); //member/empList.jsp
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// url - control 매칭
		String uri = req.getRequestURI(); //http://localhost8080 "/HelloWeb/main.do" 여기를 가지고 옴
		String context = req.getContextPath(); // //HelloWeb
		String path = uri.substring(context.length()); // /main.do //마지막 페이지의 값을 읽어오기 위함
		System.out.println(path);
		
		Control sub = map.get(path); // Parent parent = new Child(); 가능
									 // 자식 class의 인스턴스는 부모 class에 할당 가능
		sub.execute(req, resp);
	}
}
