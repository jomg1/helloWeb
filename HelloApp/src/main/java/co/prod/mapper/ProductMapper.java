package co.prod.mapper;

import java.util.List;
import java.util.Map;

import co.prod.vo.MembersVO;
import co.prod.vo.ProductVO;
import co.prod.vo.ReplyVO;
import co.prod.vo.ScheduleVO;

public interface ProductMapper {
	//목록
	public List<ProductVO> productList();
	//한건 조회
	public ProductVO selectProduct(String code);
	
	//댓글 목록
	public List<ReplyVO> replyList(String code);
	//댓글 삭제
	public int deleteReply(int replyId);
	//댓글 등록
	public int insertReply(ReplyVO vo);
	//댓글 조회
	public ReplyVO selectReply(int replyId);
	//댓글 수정 업데이트
	public int updateReply(ReplyVO vo);
	
	//chart 부서별 인원현황
	public List<Map<String, Object>> chartInfo();
	
	//캘린더- 스케줄 출력
	public List<ScheduleVO> selectSchedule();
	//캘린더 - 스케줄 추가
	public int insertSchedule(ScheduleVO vo);
	//캘린더 - 스케줄 삭제
	public int deleteSchedule(ScheduleVO vo);
	
	
}
