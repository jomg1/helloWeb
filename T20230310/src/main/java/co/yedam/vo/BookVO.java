package co.yedam.vo;

import lombok.Data;

@Data
public class BookVO {
	private String bookCode; // 도서코드
	private String bookTitle; // 도서명
	private String bookAuthor; // 저자
	private String bookPress; // 출판사
	private String bookDesc; // 서평
	private int  bookPrice; // 가격
}
