package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;

public class ProductInfoControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		String code = req.getParameter("code");
		ProductService service = new ProductServiceImpl();
		
		req.setAttribute("code", service.getProduct(code));
		
				
		return "product/product.tiles";
	}

}
