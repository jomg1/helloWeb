package co.dev.vo;

import lombok.Data;

@Data
public class PageDTO {
	private int startPage; // 첫페이지
	private int endPage; // 마지막페이지
	private boolean prev, next; // 이전, 다음 페이지 존재 여부 체크
	private int page; // 현재 페이지
	
	public PageDTO(int page, int total) {//페이지 정보를 매개값으로 받아와 페이지 정보를 세팅해주는 생성자
		//total:235건 -> 24page
		this.page = page;
		this.endPage = (int) Math.ceil(page/10.0) * 10;//ceil로 올림 처리 1~10 => 10
		this.startPage= this.endPage - 9;
		
		int realEnd = (int) Math.ceil(total * 1.0 / 10);
		if(this.endPage > realEnd) {
			this.endPage = realEnd; // 22page 30->24page까지 나와야하므로 바꿔줌
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd; //
	}
}
