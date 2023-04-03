package co.prod.mapper;

import java.util.List;

import co.prod.vo.MemberVO;
import co.prod.vo.MembersVO;



public interface MemberMapper {
	// 매퍼(MemberMapper.xml)에서 실행할 메소드 정의
	public List<MemberVO> getMembers();
	
	// 로그인 용도
	public MemberVO login(MemberVO vo);
	
	// 회원 삭제.
	public int deleteMember(String id); // 삭제 된 건수 반환 할 목적으로 리턴 타입 int 사용
	
	// 회원등록
	public int insertMember(MemberVO vo);
	
	//매퍼 membersList
	public List<MembersVO> membersList();
	//members 추가
	public int insertMembers(MembersVO vo);
	//members 삭제
	public int deleteMembers(List<MembersVO> list);

}
