package co.dev.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.dev.common.DataSource;
import co.dev.mapper.NoticeMapper;
import co.dev.vo.NoticeVO;

public class NoticeServiceMybatis implements NoticeService{

	// jdbc: NoticeDAO dao; //DAO가 필요
	// mybatis: mapper; //mapper 파일을 가져옴
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
													//true로 되어야 자동 commit;
	NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
	@Override
	public List<NoticeVO> noticeList(int page) { // 목록
		//return mapper.noticeList();
		return mapper.noticeListWithPaging(page);
	}
	@Override
	public boolean addNotice(NoticeVO vo) { //등록 (매퍼에 등록되어있어야 함)
		int r = mapper.insertNotice(vo);
		//sqlSession.commit();// 다른세션에서 반영, openSession true이므로 커밋 하지 않아도 됨
		return r ==1;
		
		//return mapper.insertNotice(vo) == 1; //1true 0false
		//int r, return r 하지 않으면 윗 줄 하나면 됨.	
	}
	//단건 조회
	@Override
	public NoticeVO getNotice(int nid) {
		mapper.updateCount(nid);//조회수 증가
		return mapper.selectNotice(nid);
	}
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount(); //전체 건수 가져오는 메소드
	}
	@Override
	public boolean noiceModify(NoticeVO vo) {
		int r = mapper.updateNotice(vo); //수정
		return r == 1;
	}
	@Override
	public int noticeRemove(int nid) {
		return mapper.noticeRemove(nid);
	}
	
	
	

}
