package co.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.EmpVO;

public class DataTableAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
//		{ "data": [ [ "Tiger Nixon", "System Architect", "Edinburgh", "5421", "2011/04/25", "$320,800" ],
//			            ["Garrett Winters", "Accountant", "Tokyo", "8422", "2011/07/25", "$170,750" ] 
//		}
		
		MemberService service = new MemberServiceMybatis();
		List<EmpVO> list = service.emplyeeList();
		
		//json 포맷으로 넘겨줘야함
		String json = "{\"data\":[";
		for(EmpVO vo : list) { // 데이터 건수 만큼 루핑 돌며 해당 형식으로 데이터를 가지고 온다
			json += "[\"" + vo.getEmployeeId() + "\", \"" 
						  + vo.getFirstName() + "\", \"" 
						  + vo.getLastName() + "\", \"" 
						  + vo.getEmail() + "\", \"" 
						  + vo.getHireDate().substring(0, 10) + "\", \"" //시분초 나오지 않게 하기 위해 substring
						  + vo.getSalary() + "\"],";
		}
		
		json = json.substring(0, json.length() -1);// -1인 이유는 마지막 쉼표를 빼기 위해서
		json += "]}";
		
		return json + ".ajax";
	}

}
