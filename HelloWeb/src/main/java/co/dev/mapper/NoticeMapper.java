package co.dev.mapper;

import java.util.List;

import co.dev.vo.NoticeVO;

public interface NoticeMapper {
	// 목록
	public List<NoticeVO> noticeList(); //컬렉션 타입으로 반환 => NoticeVO 생성하기
	public List<NoticeVO> noticeListWithPaging(int page);
	// 등록
	public int insertNotice(NoticeVO vo); //insert,update,delete는 반환값1이 될 수 있게 int타입
	public NoticeVO selectNotice(int nid);//단건조회
	public int updateCount(int nid); //조회수 증가
	public int getTotalCount();
	public int updateNotice(NoticeVO vo); //수정
	public int noticeRemove(int nid);//삭제
	
}
