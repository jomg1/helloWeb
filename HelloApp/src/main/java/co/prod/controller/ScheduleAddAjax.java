package co.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ScheduleVO;

public class ScheduleAddAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
		String title = req.getParameter("title");
		String startDate = req.getParameter("start");
		String endDate = req.getParameter("end");
		
		ScheduleVO vo = new ScheduleVO();
		vo.setTitle(title);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		
		
		ProductService service = new ProductServiceImpl();
		
		boolean result = service.addSchedule(vo);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(result) {
			resultMap.put("retCode", "Success");
			resultMap.put("schedule", vo);
		}else {
			resultMap.put("retCode", "Fail");
			resultMap.put("schedule", null);
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(resultMap);
		
		return json + ".ajax";
	}

}
