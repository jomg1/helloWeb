package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;

public class MapForm implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
		String lat = req.getParameter("lat");
		String lng = req.getParameter("lng");
		String name = req.getParameter("name");
		
		req.setAttribute("lat", lat);
		req.setAttribute("lng", lng);
		req.setAttribute("name", name);
		
		return "product/map.jsp";
	}

}
