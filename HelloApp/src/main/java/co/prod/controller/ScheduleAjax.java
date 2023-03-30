package co.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ScheduleVO;

public class ScheduleAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		String title = req.getParameter("title");
		ProductService service = new ProductServiceImpl();
		
		List<ScheduleVO> list = service.secheduleList();
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list); 
		
		return json + ".ajax";
	}

}
