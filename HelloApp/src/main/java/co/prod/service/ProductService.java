package co.prod.service;

import java.util.List;
import java.util.Map;

import co.prod.vo.ProductVO;
import co.prod.vo.ReplyVO;
import co.prod.vo.ScheduleVO;

public interface ProductService {
	//리스트 출력
	public List<ProductVO> products();
	//한건 조회
	public ProductVO getProduct (String code); 
	//상품에 대한 댓글 목록
	public List<ReplyVO> replyList(String code);
	//댓글 삭제
	public boolean removeReply(int replyId);
	//댓글 등록
	public boolean addReply(ReplyVO vo);
	//상품 댓글 번호 조회
	public ReplyVO getReply(int replyId);
	//댓글 수정
	public boolean modifyReply(ReplyVO vo);
	
	//chart
	public List<Map<String, Object>> chartInfo();
	//schedule 목록 출력
	public List<ScheduleVO> secheduleList ();
	//schedule 추가
	public boolean addSchedule(ScheduleVO vo);
	//schedule 삭제
	public boolean removeSchedule(ScheduleVO vo);
}
