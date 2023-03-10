package co.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ProductVO;

public class ProductListControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// db 결과 -> attribute("list")
		
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.products();
		
		req.setAttribute("product", service.products());
		
		return "product/productList.tiles"; // 실행할 페이지
	}

}
