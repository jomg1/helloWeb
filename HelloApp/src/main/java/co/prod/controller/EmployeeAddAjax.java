package co.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.EmpVO;

public class EmployeeAddAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
//		private int employeeId;
//		private String firstName;
//		private String lastName;
//		private String email;
//		private int salary;
//		private String hireDate;
//		private String jobId;
		
//		int id = Integer.parseInt(req.getParameter("employeeId"));
		String fn = req.getParameter("firstName");
		String ln = req.getParameter("lastName");
		String em = req.getParameter("email");
		int sal = Integer.parseInt(req.getParameter("salary"));
		String hd = req.getParameter("hireDate");
//		String job = req.getParameter("jobId");
		
		EmpVO vo = new EmpVO();
//		vo.setEmployeeId(id);
		vo.setFirstName(fn);
		vo.setLastName(ln);
		vo.setEmail(em);
		vo.setSalary(sal);
		vo.setHireDate(hd);
		vo.setJobId("IT_PROG");
		
		MemberService service =  new MemberServiceMybatis();
		boolean result = service.addEmployee(vo);
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		
		if(result) {
			map.put("retCode", "Success");
			map.put("emp", vo);
		}else {
			map.put("retCode", "Fail");
			map.put("emp", null);
		}
		json = gson.toJson(map);
		
		return json + ".ajax";
	}

}
