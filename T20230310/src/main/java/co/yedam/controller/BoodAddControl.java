package co.yedam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.BookService;
import co.yedam.service.BookServiceMybatis;
import co.yedam.vo.BookVO;

public class BoodAddControl implements Command {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookCode = request.getParameter("bookCode");
		String bookTitle = request.getParameter("bookTitle");
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPress = request.getParameter("bookPress");
		String bookDesc = request.getParameter("bookDesc");
		int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
		
		
		
		BookVO vo = new BookVO();
		vo.setBookCode(bookCode);
		vo.setBookTitle(bookTitle);
		vo.setBookAuthor(bookAuthor);
		vo.setBookPress(bookPress);
		vo.setBookDesc(bookDesc);
		vo.setBookPrice(bookPrice);
		
		BookService service = new BookServiceMybatis();
		if(service.addBook(vo)) {
			response.sendRedirect("bookList.do");
		}
	}

}
