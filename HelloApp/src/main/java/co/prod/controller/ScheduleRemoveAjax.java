package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ScheduleVO;

public class ScheduleRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		
		ScheduleVO vo = new ScheduleVO();
		vo.setTitle(title);
		vo.setStartDate(start);
		vo.setEndDate(end);
		
		ProductService service = new ProductServiceImpl();
		
		boolean result = service.removeSchedule(vo);
		
		String json="";
		if(result) {
			json = "{\"retCode\": \"Success\"}";
		}else {
			json ="{\"retCode\": \"Fail\"}";
		}
		
		return json + ".ajax";
	}

}
