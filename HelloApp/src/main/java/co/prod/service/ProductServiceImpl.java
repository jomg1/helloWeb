package co.prod.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import co.prod.common.DataSource;
import co.prod.mapper.ProductMapper;
import co.prod.vo.MembersVO;
import co.prod.vo.ProductVO;
import co.prod.vo.ReplyVO;
import co.prod.vo.ScheduleVO;

public class ProductServiceImpl implements ProductService{
	
	SqlSession sqlSession = DataSource.getInstance().openSession(true); //sqlSessionFactory 객체 반환->openSession(true)이면 자동 커밋
	ProductMapper mapper = sqlSession.getMapper(ProductMapper.class) ;
	
	@Override
	public List<ProductVO> products() {
		return mapper.productList();
	}

	@Override
	public ProductVO getProduct(String code) {
		return mapper.selectProduct(code);
	}

	@Override
	public List<ReplyVO> replyList(String code) {
		return mapper.replyList(code);
	}

	@Override
	public boolean removeReply(int replyId) {
		return mapper.deleteReply(replyId) == 1;
	}

	@Override
	public boolean addReply(ReplyVO vo) {
		return mapper.insertReply(vo)==1;
	}

	@Override
	public ReplyVO getReply(int replyId) {
		return mapper.selectReply(replyId);
	}

	@Override
	public boolean modifyReply(ReplyVO vo) {
		return mapper.updateReply(vo)==1;
	}

	@Override
	public List<Map<String, Object>> chartInfo() {
		return mapper.chartInfo();
	}

	@Override
	public List<ScheduleVO> secheduleList() {
		return mapper.selectSchedule();
	}

	@Override
	public boolean addSchedule(ScheduleVO vo) {
		return mapper.insertSchedule(vo)==1;
	}

	@Override
	public boolean removeSchedule(ScheduleVO vo) {
		return mapper.deleteSchedule(vo)==1;
	}

	

	
	
	
	


	

}
