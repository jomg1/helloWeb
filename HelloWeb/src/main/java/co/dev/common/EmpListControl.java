package co.dev.common;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.EmpServiceImpl;
import co.dev.vo.EmpVO;

public class EmpListControl implements Control {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 회원목록 보여주는 페이지 /WEB-INF/member/memberList.jsp
		Emp service = new EmpServiceImpl();
		List<EmpVO> list = service.getEmp();
		
		//요청 값이 담긴 곳 members
		req.setAttribute("members", list); // context : sc.setAttribute("param", map)
		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/member/memberList.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // 페이지 재지정
	}

}
