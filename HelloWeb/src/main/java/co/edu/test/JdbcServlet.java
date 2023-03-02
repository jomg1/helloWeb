package co.edu.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.jdbc.EmpDAO;

@WebServlet("/getUserInfo")
public class JdbcServlet extends HttpServlet{
	
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get or post 요청을 구분해서 처리
		// get이면 조회. post이면 입력
		
		EmpDAO dao = new EmpDAO();
		Map<String, Object> result = dao.getEmpInfo(101);
		Set<String> set = result.keySet(); // 키부분만 set 컬렉션.
		for(String key : set) {
			System.out.println("key: "+ key+",val: "+result.get(key));
		}
		
		//페이지 출력. 사번/이름(이름+성씨)/급여/부서
		req.setCharacterEncoding("utf-8"); // 요청정보의 인코딩 방식 지정	
		resp.setCharacterEncoding("utf-8"); // 응답정보의 인코딩 방식 지정
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		
		//페이지 작성.
		// {키:값}, {키:값}, {키:값}, {키:값} => map.get("키") 값을 반환
		
		String id = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String salary = req.getParameter("salary");
		String departmentId = req.getParameter("departmentId");
		
		
//		out.print("사원번호 : "+result.get("id")+"<br>");
//		out.print("이름 : "+result.get("firstName")+" &nbsp");
//		out.print(result.get("lastName")+"<br>");
//		out.print("급여 : "+result.get("salary")+"<br>");
//		out.print("부서 : "+result.get("departmentId")+"<br>");
		
		String html = "<html><head><title>Header 정보</title></head>";
		html += "<body>";
		html += "<p>사원번호 : "+result.get("id")+"<br>";
		html += "이름 : "+result.get("firstName")+" &nbsp";
		html += result.get("lastName")+"<br>";
		html += "급여 : "+result.get("salary")+"<br>";
		html += "부서 : "+result.get("departmentId")+"<br>";
		
		html += "</body></html>";
		
		
		out.print(html);
		
		out.print("<a href='temp.html'>조회-등록으로 이동</a>");
		out.close();
		
		
	}
}
