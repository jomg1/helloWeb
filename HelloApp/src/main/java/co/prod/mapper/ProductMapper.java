package co.prod.mapper;

import java.util.List;

import co.prod.vo.ProductVO;
import co.prod.vo.ReplyVO;

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
}
